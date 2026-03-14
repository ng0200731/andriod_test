# News Web App

A minimal black & white web application that displays news headlines from NewsAPI.

## Features

- ✅ Runs entirely in browser
- ✅ Works on any device (desktop, tablet, mobile)
- ✅ No installation needed
- ✅ Minimal black & white design
- ✅ Auto-refresh every 5 minutes
- ✅ Responsive layout

## Quick Start

### Option 1: Run Locally

1. **Get your API key**:
   - Go to https://newsapi.org/register
   - Sign up for free
   - Copy your API key

2. **Update API key**:
   - Open `app.js`
   - Replace `your_api_key_here` with your actual API key on line 2

3. **Run the app**:
   - Simply open `index.html` in your browser
   - Or use a local server (recommended):
     ```bash
     # Python 3
     py -m http.server 8000
     
     # Then open: http://localhost:8000
     ```

### Option 2: Deploy Online (Free)

#### A) Netlify (Easiest)

1. Go to https://app.netlify.com/drop
2. Drag and drop the `web_app` folder
3. Your app is live instantly!
4. Update API key in the deployed `app.js` via Netlify dashboard

#### B) GitHub Pages

1. Push to GitHub:
   ```bash
   git add web_app/
   git commit -m "Add web app"
   git push
   ```

2. Go to your repository settings
3. Navigate to "Pages"
4. Select branch: `main`, folder: `/web_app`
5. Save and wait a few minutes
6. Your app will be live at: `https://ng0200731.github.io/andriod_test/`

#### C) Vercel

1. Go to https://vercel.com
2. Sign up with GitHub
3. Import your repository
4. Set root directory to `web_app`
5. Deploy!

#### D) Replit

1. Go to https://replit.com
2. Create new Repl (HTML/CSS/JS)
3. Upload `index.html`, `style.css`, `app.js`
4. Click "Run"
5. Share the URL!

## File Structure

```
web_app/
├── index.html      # Main HTML structure
├── style.css       # Black & white styling
├── app.js          # NewsAPI integration
└── README.md       # This file
```

## How It Works

1. **Fetches news** from NewsAPI on page load
2. **Displays articles** with title, description, source, and date
3. **Auto-refreshes** every 5 minutes
4. **Responsive design** adapts to any screen size
5. **Error handling** shows helpful messages if API fails

## Customization

### Change Country

Edit `app.js` line 3:
```javascript
const API_URL = `https://newsapi.org/v2/top-headlines?country=gb&apiKey=${API_KEY}`;
// Options: us, gb, ca, au, in, etc.
```

### Change Category

Add category parameter:
```javascript
const API_URL = `https://newsapi.org/v2/top-headlines?country=us&category=technology&apiKey=${API_KEY}`;
// Options: business, entertainment, general, health, science, sports, technology
```

### Change Refresh Interval

Edit `app.js` last line:
```javascript
setInterval(fetchNews, 10 * 60 * 1000); // 10 minutes
```

## Troubleshooting

**"Error: 401" or "Invalid API key"**
- Check that you replaced `your_api_key_here` with your actual key
- Verify key is active at https://newsapi.org/account

**"Error: 429" or "Rate limit exceeded"**
- Free tier: 100 requests/day
- Wait 24 hours or upgrade plan

**CORS errors**
- Use a local server instead of opening HTML directly
- Or deploy to a hosting service (Netlify, Vercel, etc.)

**No news showing**
- Check browser console (F12) for errors
- Verify internet connection
- Check if NewsAPI is accessible

## Browser Support

- ✅ Chrome/Edge (latest)
- ✅ Firefox (latest)
- ✅ Safari (latest)
- ✅ Mobile browsers

## Next Steps

Want to enhance the app? Try adding:
- Search functionality
- Category filters
- Dark mode toggle
- Save favorite articles
- Share buttons
- Pagination

## License

Educational demonstration project.
