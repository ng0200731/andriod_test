# Fetch News from NewsAPI

## Objective
Fetch top news headlines from NewsAPI.org and store them for use in the Android app.

## API Choice: NewsAPI
- **URL**: https://newsapi.org/
- **Free Tier**: 100 requests/day, developer key required
- **Endpoints**: `/v2/top-headlines` for latest news
- **Response**: JSON with articles containing title, description, url, urlToImage, publishedAt

## Required Inputs
- API key (stored in `.env` as `NEWSAPI_KEY`)
- Optional: country code (default: `us`)
- Optional: category (business, entertainment, general, health, science, sports, technology)

## Tool to Use
`tools/fetch_news.py`

## Expected Output
- JSON file saved to `.tmp/news_data.json`
- Contains array of articles with: title, description, url, image_url, published_at

## Edge Cases
- Rate limit exceeded: Cache results, retry after 24 hours
- Invalid API key: Check `.env` file
- Network errors: Retry with exponential backoff (3 attempts)
- Empty results: Log warning, return empty array

## Usage Example
```bash
py tools/fetch_news.py --country us --category technology
```
