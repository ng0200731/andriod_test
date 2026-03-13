#!/usr/bin/env python3
"""
Fetch news articles from NewsAPI.org
"""
import os
import json
import sys
import argparse
import time
from urllib.request import Request, urlopen
from urllib.error import HTTPError, URLError
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

API_KEY = os.getenv('NEWSAPI_KEY')
BASE_URL = 'https://newsapi.org/v2/top-headlines'

def fetch_news(country='us', category=None, max_retries=3):
    """Fetch news from NewsAPI with retry logic"""
    
    if not API_KEY:
        print("Error: NEWSAPI_KEY not found in .env file", file=sys.stderr)
        sys.exit(1)
    
    # Build URL
    url = f"{BASE_URL}?country={country}&apiKey={API_KEY}"
    if category:
        url += f"&category={category}"
    
    # Retry logic
    for attempt in range(max_retries):
        try:
            req = Request(url)
            req.add_header('User-Agent', 'NewsAndroidApp/1.0')
            
            with urlopen(req, timeout=10) as response:
                data = json.loads(response.read().decode('utf-8'))
                
                if data.get('status') == 'ok':
                    articles = data.get('articles', [])
                    
                    # Transform to simpler format
                    simplified = []
                    for article in articles:
                        simplified.append({
                            'title': article.get('title', ''),
                            'description': article.get('description', ''),
                            'url': article.get('url', ''),
                            'image_url': article.get('urlToImage', ''),
                            'published_at': article.get('publishedAt', ''),
                            'source': article.get('source', {}).get('name', '')
                        })
                    
                    return simplified
                else:
                    print(f"API Error: {data.get('message', 'Unknown error')}", file=sys.stderr)
                    return []
                    
        except HTTPError as e:
            if e.code == 429:  # Rate limit
                print(f"Rate limit exceeded. Try again later.", file=sys.stderr)
                return []
            elif e.code == 401:  # Unauthorized
                print(f"Invalid API key. Check your .env file.", file=sys.stderr)
                return []
            else:
                print(f"HTTP Error {e.code}: {e.reason}", file=sys.stderr)
                
        except URLError as e:
            print(f"Network error: {e.reason}", file=sys.stderr)
            
        except Exception as e:
            print(f"Unexpected error: {e}", file=sys.stderr)
        
        # Exponential backoff
        if attempt < max_retries - 1:
            wait_time = 2 ** attempt
            print(f"Retrying in {wait_time} seconds...", file=sys.stderr)
            time.sleep(wait_time)
    
    print("Failed after all retries", file=sys.stderr)
    return []

def main():
    parser = argparse.ArgumentParser(description='Fetch news from NewsAPI')
    parser.add_argument('--country', default='us', help='Country code (default: us)')
    parser.add_argument('--category', help='Category: business, entertainment, general, health, science, sports, technology')
    parser.add_argument('--output', default='.tmp/news_data.json', help='Output file path')
    
    args = parser.parse_args()
    
    print(f"Fetching news for country={args.country}, category={args.category or 'all'}")
    
    articles = fetch_news(args.country, args.category)
    
    # Ensure .tmp directory exists
    os.makedirs(os.path.dirname(args.output), exist_ok=True)
    
    # Save to file
    with open(args.output, 'w', encoding='utf-8') as f:
        json.dump(articles, f, indent=2, ensure_ascii=False)
    
    print(f"Saved {len(articles)} articles to {args.output}")

if __name__ == '__main__':
    main()
