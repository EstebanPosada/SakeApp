# SakeApp

Android application to explore and manage a list of sakes, developed with Kotlin, Jetpack Compose, Hilt, and Room.

## Features

- Sake list using Jetpack Compose.
- Detail screen with extended sake information, address, rating, and website link.
- Loading and error state handling.
- MVVM-based architecture.
- Dependency injection with Hilt.
- Local persistence with Room.
- Modern UI with Jetpack Compose.
- UI testing for HomeScreen and DetailScreen.

## Requirements

- **Android Studio** (latest version recommended)
- **Device or emulator** with Android 10 (API 29) or higher

## Development Process

- The project follows the MVVM pattern to separate UI and data logic.
- Hilt is used for dependency injection and easier testing.
- Room is used for local data persistence.
- Main screens (`HomeScreen` and `DetailScreen`) are implemented with Jetpack Compose.
- Instrumented tests are included to validate UI and app states.

### Architecture

- **UI Layer:** Built with Jetpack Compose, contains Composables for screens and UI components.
- **ViewModel Layer:** Handles UI state, business logic, and communicates with the domain/data layers.
- **Domain Layer:** Contains business models and use cases.
- **Data Layer:** Manages data sources, including local persistence with Room and dependency injection with Hilt.

## UI Tests

Instrumented tests cover:

- Loader visibility when the screen is loading.
- Error message display if a problem occurs.
- Empty message when there are no items.
- Correct display of sake details.
- Visibility of key elements (name, description, address, website button).

### Dependencies

- **Kotlin:** Main programming language.
- **Jetpack Compose:** Declarative UI framework.
- **Hilt:** Dependency injection for easier management of components and testing.
- **Room:** Local database for data persistence.
- **Coil:** Image loading in Compose.
- **JUnit & MockK:** Unit and UI testing.
- 
### Data Models

- Models are defined in the `domain/model` package.
- Example: `SakeUiModel` represents a sake item with fields like `name`, `description`, `imageUrl`, `rating`, `address`, `coordinates`, and `website`.
