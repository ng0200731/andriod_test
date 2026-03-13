# Android Studio Installation Guide

## Step 1: Download Android Studio

1. Visit the official website: https://developer.android.com/studio
2. Click "Download Android Studio"
3. Accept the terms and conditions
4. The installer will download (approximately 1GB)

## Step 2: Install Android Studio

### Windows Installation:

1. **Run the installer** (`android-studio-xxxx.exe`)
2. Click "Next" on the welcome screen
3. **Choose components** (keep all selected):
   - Android Studio
   - Android Virtual Device (for emulator)
4. **Choose install location** (default is fine: `C:\Program Files\Android\Android Studio`)
5. Click "Install"
6. Wait for installation to complete (5-10 minutes)
7. Click "Finish" and check "Start Android Studio"

## Step 3: Android Studio Setup Wizard

When Android Studio launches for the first time:

1. **Import Settings**: Choose "Do not import settings" (if first time)
2. **Welcome Screen**: Click "Next"
3. **Install Type**: Choose "Standard" (recommended)
4. **Select UI Theme**: Choose your preference (Darcula or Light)
5. **Verify Settings**: Review and click "Next"
6. **Download Components**: 
   - Android SDK will be downloaded (2-3GB)
   - This takes 10-30 minutes depending on internet speed
   - Click "Finish" when done

## Step 4: Configure Android SDK

The SDK should be automatically installed at:
- Windows: `C:\Users\YourUsername\AppData\Local\Android\Sdk`

### Add Android SDK to System PATH:

1. Press `Win + X` and select "System"
2. Click "Advanced system settings"
3. Click "Environment Variables"
4. Under "User variables", find "Path" and click "Edit"
5. Click "New" and add these two paths:
   ```
   C:\Users\YourUsername\AppData\Local\Android\Sdk\platform-tools
   C:\Users\YourUsername\AppData\Local\Android\Sdk\tools
   ```
   (Replace `YourUsername` with your actual Windows username)
6. Click "OK" on all dialogs
7. **Restart your command prompt** for changes to take effect

## Step 5: Verify Installation

Open a new command prompt and run:
```bash
adb --version
```

You should see output like:
```
Android Debug Bridge version 1.0.41
```

## Step 6: Set Up Android Device or Emulator

### Option A: Use Physical Device (Recommended for testing)

1. **Enable Developer Options** on your Android phone:
   - Go to Settings > About Phone
   - Tap "Build Number" 7 times
   - You'll see "You are now a developer!"

2. **Enable USB Debugging**:
   - Go to Settings > Developer Options
   - Enable "USB Debugging"

3. **Connect via USB**:
   - Connect your phone to computer with USB cable
   - On phone, tap "Allow" when prompted for USB debugging
   - Run `adb devices` in command prompt to verify

### Option B: Use Android Emulator

1. In Android Studio, click "More Actions" > "Virtual Device Manager"
2. Click "Create Device"
3. Select a device (e.g., "Pixel 5")
4. Select a system image (e.g., "API 34" - latest Android)
5. Click "Download" if needed, then "Next"
6. Name your emulator and click "Finish"
7. Click the "Play" button to start the emulator

## Step 7: Open Your News App Project

1. In Android Studio, click "Open"
2. Navigate to: `d:\project\andriod_test\android_app`
3. Click "OK"
4. Wait for Gradle sync (first time takes 5-10 minutes)
5. If prompted to update Gradle or plugins, click "Update"

## Step 8: Update API Key

Before running the app, update your NewsAPI key:

1. Open `app/src/main/java/com/newsapp/MainActivity.java`
2. Find line 27:
   ```java
   private static final String API_KEY = "your_api_key_here";
   ```
3. Replace `your_api_key_here` with your actual API key from https://newsapi.org/register

## Step 9: Run the App

1. Make sure device/emulator is connected (check toolbar dropdown)
2. Click the green "Run" button (or press Shift+F10)
3. Select your device/emulator
4. Wait for build and installation
5. App will launch automatically!

## Troubleshooting

### "adb not found" after installation
- Make sure you added the correct paths to environment variables
- Restart your command prompt
- Verify the SDK path exists: `C:\Users\YourUsername\AppData\Local\Android\Sdk\platform-tools`

### Gradle sync failed
- Check your internet connection
- Click "File" > "Invalidate Caches" > "Invalidate and Restart"
- Try "File" > "Sync Project with Gradle Files"

### Emulator won't start
- Enable virtualization in BIOS (Intel VT-x or AMD-V)
- Install Intel HAXM (Android Studio will prompt if needed)
- Try creating a new virtual device with lower specs

### Build errors
- Make sure you have Java 8 or higher installed
- Update Android Studio to latest version
- Update Gradle plugin if prompted

### App crashes on launch
- Check if you updated the API key
- Check Logcat in Android Studio for error messages
- Verify internet permission in AndroidManifest.xml

## Quick Reference

**Android Studio Download**: https://developer.android.com/studio
**NewsAPI Registration**: https://newsapi.org/register
**Android Developer Docs**: https://developer.android.com/docs

## After Installation

Once Android Studio is set up, you can use the quick launcher:
```bash
cd d:\project\andriod_test
start.bat
```

This will automatically build and install the app on your connected device!
