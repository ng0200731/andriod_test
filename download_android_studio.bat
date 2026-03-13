@echo off
echo ========================================
echo Android Studio Download Helper
echo ========================================
echo.
echo This will open the Android Studio download page in your browser.
echo.
echo After downloading:
echo 1. Run the installer
echo 2. Follow the setup wizard
echo 3. See SETUP_ANDROID_STUDIO.md for detailed instructions
echo.
pause

start https://developer.android.com/studio

echo.
echo Browser opened with Android Studio download page.
echo.
echo While it downloads, you can also register for NewsAPI:
start https://newsapi.org/register
echo.
echo NewsAPI registration page opened.
echo.
echo Next steps:
echo 1. Install Android Studio
echo 2. Get your NewsAPI key
echo 3. Read SETUP_ANDROID_STUDIO.md for complete setup guide
echo.
pause
