# agent-uikit

Jetpack Compose UI kit for building conversational AI, voice agents, video assistants, and biomarker-driven Android experiences.

Inspired by the web-based [`AgoraIO-Conversational-AI/agent-uikit`](https://github.com/AgoraIO-Conversational-AI/agent-uikit), this project brings that same product direction to Android with reusable Compose components and a dedicated showcase app.

## Why People Will Use This

- Build voice and avatar-first AI interfaces faster in Jetpack Compose.
- Start from a cohesive dark visual system instead of assembling one-off screens.
- Reuse higher-level agent UI patterns, not just low-level buttons and cards.
- Explore every component in a live Android showcase before wiring it into your app.

## What You Get

`agent-uikit` is split into two modules:

- `uikit`: the reusable Android library
- `app`: a catalog-style showcase that exercises the library surface

The library includes components across these product areas:

| Area | Components |
| --- | --- |
| Voice | `MicButton`, `SimpleVisualizer`, `LiveWaveform`, `AudioVisualizer`, `MicSelector`, `MicButtonWithVisualizer`, `AgentVisualizer` |
| Chat | `Conversation`, `ConversationContent`, `ConversationEmptyState`, `ConversationScrollButton`, `Message`, `MessageContent`, `Response`, `ConvoTextStream` |
| Video | `Avatar`, `AgentAvatar`, `AvatarVideoDisplay`, `LocalVideoPreview`, `CameraSelector` |
| Layout | `VideoGrid`, `VideoGridWithControls`, `MobileTabs`, `ControlStrip` |
| Settings | `AgentSettings`, `AgentSettingsPanel`, `SettingsDialog` |
| Session | `SessionPanel` |
| Biomarkers | `ShenPanel` |
| Branding | `AgoraLogo` |
| Primitives | `Button`, `IconButton`, `Card`, `Chip`, `ValuePicker`, `DropdownMenu`, `Popover`, `Command` |

## Showcase Highlights

The demo app is no longer just a sample screen. It acts as a component catalog and now showcases:

- Mobile tabbed browsing for quick phone testing
- Full voice catalog including waveform, mic selector, and visualizers
- Full chat catalog including low-level message building blocks
- Both layout variants: `VideoGrid` and `VideoGridWithControls`
- Video primitives like avatar surfaces, camera selector, and local preview
- Primitive wrappers and utility surfaces like dropdowns, popovers, and commands
- Settings, session, branding, and Shen measurement panels

## Design Direction

This library is aimed at agentic experiences, not generic app chrome.

- Dark, token-driven palette
- Card-layered surfaces for live/real-time UI
- Video tile styling and avatar surfaces
- Reusable conversation, session, and settings shells
- Android-native Compose implementation rather than a web wrapper

## Quick Start

Inside this repository, the showcase app uses the library like this:

```kotlin
implementation(project(":uikit"))
```

Wrap your screen with the included theme:

```kotlin
AgoraAgentComposeUIKitTheme(darkTheme = true) {
    AgentUIKitShowcase()
}
```

Build the library and showcase:

```bash
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
./gradlew :uikit:assemble :app:assembleDebug
```

Install the showcase app:

```bash
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
./gradlew :app:installDebug
```

## Example Usage

### Voice

```kotlin
MicButtonWithVisualizer(isEnabled = true)

MicSelector(
    devices = listOf(
        SettingOption("default", "System Default"),
        SettingOption("usb", "USB Podcast Mic")
    ),
    value = "usb",
    muted = false,
    state = MicButtonState.Listening
)
```

### Chat

```kotlin
Conversation(
    messages = listOf(
        ConversationMessage(
            id = "1",
            sender = MessageSender.Assistant,
            author = "Agent",
            text = "Welcome back. What should we tackle first?"
        )
    )
)
```

### Video

```kotlin
VideoGridWithControls(
    chat = { Conversation(messages = sampleMessages) },
    avatar = {
        AvatarVideoDisplay(
            state = AvatarVideoState.Connected,
            modifier = Modifier.fillMaxWidth().height(220.dp)
        )
    },
    localVideo = {
        LocalVideoPreview(
            modifier = Modifier.fillMaxWidth().height(160.dp),
            showLabel = true
        )
    },
    controls = { ControlStrip() }
)
```

## Project Structure

```text
.
├── app/                     # Showcase app and component catalog
├── uikit/                   # Reusable Compose library
├── gradle/                  # Wrapper and version catalog
└── .github/                 # CI, issue templates, PR template
```

## Publishing Direction

The library module is already structured for publishing and includes:

- Maven publishing configuration
- Group: `io.github.anandwana001`
- Artifact: `agent-uikit`
- Versioned release metadata
- Sources jar for the release variant

## Current Status

This project is usable and build-verified, but still evolving.

- The library compiles successfully
- The showcase app compiles successfully
- The API surface is broad enough to start adoption and feedback
- The next improvements are visual polish, richer examples, and external package publishing

## Contributing

If you want to help shape the Android version of `agent-uikit`, contributions are welcome.

- Read [CONTRIBUTING.md](./CONTRIBUTING.md)
- Follow [CODE_OF_CONDUCT.md](./CODE_OF_CONDUCT.md)
- Report sensitive issues through [SECURITY.md](./SECURITY.md)

## Open Source Notes

- License: MIT
- Attribution: this project takes inspiration from Agora's web `agent-uikit`
- Additional attribution details: [NOTICE](./NOTICE)

If this library helps you ship agent experiences faster, starring the repo helps more Android teams discover it.
