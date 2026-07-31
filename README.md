# PlexMusic

PlexMusic is an Android client for listening to music from a personal Plex Media Server. It provides a focused library browser, full playback controls, persistent downloads, Google Cast support, and a Media3 playback service that can be used by Android Auto and other media controls.

## Features

- Sign in with the Plex PIN authentication flow.
- Discover Plex servers and choose a music library.
- Remember the selected library and refresh its server connection details.
- Browse artists, albums, songs, and audio playlists.
- Open artist, album, and playlist details and start playback from any track.
- Control playback with play/pause, previous/next, seeking, shuffle, repeat, and queue views.
- Download individual songs or complete albums, artists, and playlists for offline playback.
- Browse downloaded content by playlist, artist, album, or song and remove downloads by source or track.
- Stream through the best available Plex connection and retry through alternate endpoints when playback fails.
- Play on the phone or choose an available Google Cast route.
- Expose the same playback session to Android Auto and compatible system media controls.
- Export diagnostic logs from the app for troubleshooting.

## Requirements

### Development

The project uses:

- JDK 17
- Gradle 8.9
- Android SDK platform 35 and build tools 35.0.0
- Android Studio or a compatible Gradle/Android build environment

The included build script expects the portable toolchain used by this workspace:

```text
%LOCALAPPDATA%\AndroidToolchain\
```

It locates the JDK, Gradle, and Android SDK there and redirects build outputs outside the OneDrive-backed project directory.

### Runtime

- An accessible Plex Media Server with at least one music library.
- Network access from the Android device to a Plex server connection URI.
- Google Play services on the device for Google Cast discovery and playback.
- A Cast receiver that can reach the media URLs provided by the Plex server.

The app targets Android 15 and supports Android API 26 and newer. Android Auto availability depends on the device, vehicle, and Android Auto configuration.

## Build

From the project root, build the debug APK with:

```powershell
.\scripts\build-debug.ps1
```

The script prints the generated APK path. With the default workspace toolchain, the output is:

```text
%LOCALAPPDATA%\AndroidToolchain\plexmusic-build\app\outputs\apk\debug\app-debug.apk
```

To install a built APK on a connected device:

```powershell
adb install -r <path-to-app-debug.apk>
```

The app requests notification access so Android can display the media playback notification. It also uses network access, wake locks, and a foreground media playback service.

## How It Works

PlexMusic uses the Plex API to authenticate, discover servers, load music metadata, and resolve stream URLs. The selected library and a short-lived catalog cache are stored locally so the app can start browsing while metadata is refreshed.

Playback is owned by `PlexMusicMediaLibraryService`, which hosts a single Media3 player shared by the phone UI, Android Auto, and system media controls. Streaming data is kept in a bounded cache. Explicit downloads use a separate persistent cache and remain available until they are removed from the app.

## Troubleshooting

If a library cannot be loaded or a track will not play:

1. Confirm that the phone can reach the Plex server outside the app.
2. Check that the selected Plex server exposes a connection URI reachable from the current network.
3. For Cast playback, verify that both the Android device and receiver can reach the Plex media URL.
4. Use the app's **Export logs** action and include the exported log when reporting a problem.

## Project Structure

```text
app/src/main/java/com/dudemeister/plexmusic/
|-- cast/       Google Cast integration
|-- data/       Library state, persistence, and catalog operations
|-- model/      Plex and playback data models
|-- network/    Plex API client and XML response parsing
|-- playback/  Media3 service, queue handling, caching, and downloads
`-- MainActivity.kt
scripts/
`-- build-debug.ps1
```

## License

PlexMusic is licensed under the MIT License. The license applies to this project's original source code. AndroidX, Kotlin, Jetpack Compose, Google Cast, Coil, OkHttp, Media3, and other dependencies remain under their respective licenses.

PlexMusic is an independent client and is not affiliated with or endorsed by Plex, Inc. Plex trademarks, APIs, and media content are not covered by this license.
