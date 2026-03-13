# News Android App

A minimal black & white Android app that fetches and displays news articles from NewsAPI.

## Project Structure

```
project/
├── workflows/              # WAT framework workflows
│   └── fetch_news_api.md  # News API fetching workflow
├── tools/                  # Python tools for data fetching
│   └── fetch_news.py      # NewsAPI integration tool
├── android_app/            # Android application
│   ├── app/
│   │   ├── src/main/
│   │   │   ├── java/com/newsapp/
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── NewsAdapter.java
│   │   │   │   └── NewsArticle.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   └── item_news.xml
│   │   │   │   └── values/
│   │   │   │       └── styles.xml
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle
│   └── build.gradle
├── .tmp/                   # Temporary data files
└── .env                    # API keys (not committed)
```

## Setup Instructions

### 1. Get NewsAPI Key

1. Visit https://newsapi.org/register
2. Sign up for a free account
3. Copy your API key

### 2. Configure API Key

**For Python Tool:**
Edit `.env` file:
```
NEWSAPI_KEY=your_actual_api_key_here
```

**For Android App:**
Edit `android_app/app/src/main/java/com/newsapp/MainActivity.java`:
```java
private static final String API_KEY = "your_actual_api_key_here";
```

### 3. Test Python Tool (Optional)

Install dependencies:
```bash
pip install python-dotenv
```

Run the tool:
```bash
py tools/fetch_news.py --country us --category technology
```

This will save news data to `.tmp/news_data.json`

### 4. Build Android App

**Requirements:**
- Android Studio Arctic Fox or later
- Android SDK 24+ (Android 7.0)
- Java 8+

**Option A: Quick Start (Windows)**
1. Update API key in `android_app/app/src/main/java/com/newsapp/MainActivity.java`
2. Connect Android device or start emulator
3. Run `start.bat`

**Option B: Android Studio**
1. Open Android Studio
2. Select "Open an Existing Project"
3. Navigate to `android_app/` directory
4. Wait for Gradle sync to complete
5. Update API key in `MainActivity.java` (line 27)
6. Click "Run" or press Shift+F10

## Features

- Fetches top news headlines from NewsAPI
- Displays news in a clean, minimal black & white interface
- Shows article title, description, source, and publish date
- Network error handling with user feedback
- Async data loading to prevent UI blocking

## API Information

**NewsAPI Free Tier:**
- 100 requests per day
- Access to top headlines
- No credit card required
- Documentation: https://newsapi.org/docs

**Endpoints Used:**
- `/v2/top-headlines` - Get breaking news headlines

## Design Principles

Following the WAT framework:
- **Workflows**: Documentation in `workflows/` directory
- **Agents**: AI coordination (you!)
- **Tools**: Python scripts for deterministic execution

**UI Style:**
- Strict black & white palette
- No color accents
- Minimal, clean design
- High readability

## Troubleshooting

**"Invalid API key" error:**
- Check that you've replaced `your_api_key_here` with your actual key
- Verify the key is active at https://newsapi.org/account

**"Rate limit exceeded":**
- Free tier allows 100 requests/day
- Wait 24 hours or upgrade your plan

**Network errors:**
- Check internet connection
- Verify `INTERNET` permission in AndroidManifest.xml
- Check if NewsAPI is accessible from your location

**Build errors:**
- Ensure Android SDK 34 is installed
- Sync Gradle files
- Clean and rebuild project

## Next Steps

Potential enhancements:
- Add pull-to-refresh functionality
- Implement article detail view with WebView
- Add category filter buttons
- Cache articles for offline viewing
- Add search functionality
- Implement pagination for more articles

## License

This is a demonstration project for educational purposes.
