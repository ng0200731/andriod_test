// Configuration
const API_KEY = '52692c2490044f5da607e13d07b062e1'; // Your NewsAPI key
const API_URL = `https://newsapi.org/v2/top-headlines?country=us&apiKey=${API_KEY}`;

// DOM elements
const newsContainer = document.getElementById('news-container');
const loadingElement = document.getElementById('loading');
const errorElement = document.getElementById('error');

// Fetch news from API
async function fetchNews() {
    try {
        loadingElement.style.display = 'block';
        errorElement.style.display = 'none';
        newsContainer.innerHTML = '';

        console.log('Fetching from:', API_URL);
        const response = await fetch(API_URL);

        console.log('Response status:', response.status);
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();
        console.log('Data received:', data);

        if (data.status === 'ok' && data.articles) {
            displayNews(data.articles);
        } else {
            throw new Error(data.message || 'Failed to fetch news');
        }

    } catch (error) {
        console.error('Fetch error:', error);
        showError('Failed to fetch. ' + error.message);
    } finally {
        loadingElement.style.display = 'none';
    }
}

// Display news articles
function displayNews(articles) {
    if (articles.length === 0) {
        newsContainer.innerHTML = '<div class="loading">No news articles found.</div>';
        return;
    }
    
    articles.forEach(article => {
        const newsItem = createNewsItem(article);
        newsContainer.appendChild(newsItem);
    });
}

// Create news item element
function createNewsItem(article) {
    const item = document.createElement('div');
    item.className = 'news-item';
    
    const title = document.createElement('div');
    title.className = 'news-title';
    title.textContent = article.title || 'No title';
    
    const description = document.createElement('div');
    description.className = 'news-description';
    description.textContent = article.description || 'No description available';
    
    const meta = document.createElement('div');
    meta.className = 'news-meta';
    
    const source = document.createElement('span');
    source.className = 'news-source';
    source.textContent = article.source?.name || 'Unknown source';
    
    const date = document.createElement('span');
    date.className = 'news-date';
    date.textContent = formatDate(article.publishedAt);
    
    const link = document.createElement('a');
    link.className = 'news-link';
    link.href = article.url;
    link.target = '_blank';
    link.textContent = 'Read more →';
    
    meta.appendChild(source);
    meta.appendChild(date);
    meta.appendChild(link);
    
    item.appendChild(title);
    item.appendChild(description);
    item.appendChild(meta);
    
    return item;
}

// Format date
function formatDate(dateString) {
    if (!dateString) return '';
    
    const date = new Date(dateString);
    const now = new Date();
    const diffMs = now - date;
    const diffMins = Math.floor(diffMs / 60000);
    const diffHours = Math.floor(diffMs / 3600000);
    const diffDays = Math.floor(diffMs / 86400000);
    
    if (diffMins < 60) {
        return `${diffMins} minutes ago`;
    } else if (diffHours < 24) {
        return `${diffHours} hours ago`;
    } else if (diffDays < 7) {
        return `${diffDays} days ago`;
    } else {
        return date.toLocaleDateString();
    }
}

// Show error message
function showError(message) {
    errorElement.textContent = `Error: ${message}. Please check your API key and try again.`;
    errorElement.style.display = 'block';
    newsContainer.innerHTML = '';
}

// Initialize app
fetchNews();

// Refresh every 5 minutes
setInterval(fetchNews, 5 * 60 * 1000);
