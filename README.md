# PlexMusic

A kid-friendly Android Plex music client for a Google Pixel 5.

## Current Scope

- Authenticate with Plex using the Plex PIN flow.
- Select and save one music library.
- Browse Artists, Albums, and Songs from an always-visible bottom navigation bar.
- Show Artists and Albums as alphabetical 3-column image grids.
- Show Songs as an alphabetical list with album art.
- Play an artist, album, or song and queue the following content.
- Show now-playing art, progress, shuffle, repeat, queue, and cast controls.
- Provide a hamburger menu with a single Sign out action.

## Requirements

This workspace is configured to use a portable local Android toolchain:

- JDK 17: `%LOCALAPPDATA%\AndroidToolchain\jdk-17.0.19+10`
- Gradle 8.9: `%LOCALAPPDATA%\AndroidToolchain\gradle-8.9`
- Android SDK: `%LOCALAPPDATA%\Android\Sdk`

The Android SDK includes platform 35, build tools 35.0.0, platform tools, and accepted SDK licenses. User-level `JAVA_HOME`, `ANDROID_HOME`, `ANDROID_SDK_ROOT`, and PATH entries have been set for new terminals.

## Run

Because this project is stored in a OneDrive-backed folder, build outputs are redirected to `%LOCALAPPDATA%\AndroidToolchain\plexmusic-build`.

Build the debug APK with:

```powershell
.\scripts\build-debug.ps1
```

The generated APK is written to:

```text
%LOCALAPPDATA%\AndroidToolchain\plexmusic-build\app\outputs\apk\debug\app-debug.apk
```

## Plex Notes

The app uses Plex PIN authentication. It discovers available servers, then displays music libraries for selection. For playback, the selected Plex server must expose a reachable connection URI from the phone network.

Casting uses the Google Cast Framework. Real cast discovery/playback requires Google Play services on the device and receiver compatibility with the served Plex media URLs.
