# agent-uikit

Jetpack Compose UI kit for building conversational, voice, video, and biomarker AI interfaces on Android.

This project is an Android-native port inspired by the web-based [`agora-agent-uikit`](https://github.com/AgoraIO-Conversational-AI/agent-uikit). The repository contains:

- `uikit`: reusable Android library module
- `app`: showcase/demo app for previewing the components

## Status

Experimental but structured for open-source development. The library focuses on reusable Compose components first, with the showcase app acting as a consumer of the library.

## Included Component Areas

- Voice: `MicButton`, `SimpleVisualizer`, `LiveWaveform`, `AudioVisualizer`, `MicSelector`, `MicButtonWithVisualizer`, `AgentVisualizer`
- Chat: `Conversation`, `Message`, `MessageContent`, `Response`, `ConvoTextStream`
- Video: `Avatar`, `AvatarVideoDisplay`, `LocalVideoPreview`, `CameraSelector`
- Layout: `VideoGrid`, `VideoGridWithControls`, `MobileTabs`, `ControlStrip`
- Settings: `AgentSettings`, `AgentSettingsPanel`, `SettingsDialog`
- Session: `SessionPanel`
- Biomarkers and branding: `ShenPanel`, `AgoraLogo`
- Primitives: `Button`, `IconButton`, `Card`, `Chip`, `ValuePicker`, `DropdownMenu`, `Popover`, `Command`

## Project Structure

```text
.
├── app/                     # Showcase application
├── uikit/                   # Reusable Compose library
├── gradle/                  # Gradle wrapper and version catalog
└── .github/                 # Community and CI files
```

## Requirements

- Android Studio with embedded JDK or JDK 17+
- Android SDK / Gradle toolchain compatible with AGP 9.x
- Min SDK 24

## Build

If you want to use Android Studio's bundled JDK from the command line on macOS:

```bash
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
./gradlew :uikit:assemble :app:assembleDebug
```

## Run the Showcase

```bash
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
./gradlew :app:installDebug
```

## Using the Library Module

Inside this repository, the demo app depends on:

```kotlin
implementation(project(":uikit"))
```

For external publishing, the library is configured to be publishable as a Maven artifact and includes sources for the release variant.

## Open Source Notes

- License: MIT
- Attribution: portions of the component model and API surface were inspired by Agora's web `agent-uikit`

See [NOTICE](./NOTICE), [CONTRIBUTING.md](./CONTRIBUTING.md), [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md), and [SECURITY.md](./SECURITY.md).
