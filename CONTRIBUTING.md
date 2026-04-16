# Contributing

Thanks for helping improve `agent-uikit`.

## Development Setup

1. Install Android Studio or a JDK compatible with the Gradle setup.
2. Export a JDK if needed:

```bash
export JAVA_HOME="/Applications/Android Studio.app/Contents/jbr/Contents/Home"
```

3. Build the library and demo app:

```bash
./gradlew :uikit:assemble :app:assembleDebug
```

## Guidelines

- Keep reusable UI in `uikit`
- Keep demo-only code in `app`
- Prefer small focused files over large monolithic component files
- Preserve API consistency with the web `agent-uikit` where it makes sense for Android
- Add or update docs when component APIs change

## Pull Requests

- Explain the user-facing change
- Mention any component API additions or breaking changes
- Include screenshots or a short demo video for visual changes when possible
- Keep unrelated cleanup out of the same PR
