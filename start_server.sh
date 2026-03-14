#!/bin/bash
cd web_app
echo "Starting News App server..."
echo "Open in browser: http://localhost:8000"
echo "Press Ctrl+C to stop"
py -m http.server 8000
