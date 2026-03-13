@echo off
echo ========================================
echo News Android App Launcher
echo ========================================
echo.

REM Check if Android Studio is installed
where /q adb
if errorlevel 1 (
    echo ERROR: Android SDK not found in PATH
    echo.
    echo Please ensure Android Studio is installed and:
    echo 1. Add Android SDK platform-tools to your PATH
    echo 2. Or run this from Android Studio terminal
    echo.
    pause
    exit /b 1
)

echo [1/4] Checking project structure...
if not exist "android_app\app\src\main\java\com\newsapp\MainActivity.java" (
    echo ERROR: Android app files not found
    echo Please ensure you are in the correct directory
    pause
    exit /b 1
)
echo OK - Project structure verified

echo.
echo [2/4] Checking for connected devices...
adb devices
echo.

echo [3/4] Building and installing app...
cd android_app
call gradlew.bat assembleDebug
if errorlevel 1 (
    echo.
    echo ERROR: Build failed
    echo Please check the error messages above
    cd ..
    pause
    exit /b 1
)

echo.
echo [4/4] Installing on device...
call gradlew.bat installDebug
if errorlevel 1 (
    echo.
    echo ERROR: Installation failed
    echo Make sure a device is connected or emulator is running
    cd ..
    pause
    exit /b 1
)

cd ..

echo.
echo ========================================
echo SUCCESS - App installed successfully!
echo ========================================
echo.
echo The News App should now be on your device.
echo Launch it from your app drawer.
echo.
echo REMINDER: Update your API key in:
echo   android_app\app\src\main\java\com\newsapp\MainActivity.java
echo.
pause
