# MyCalculatorApp02

MyCalculatorApp02 is a simple calculator application developed in Kotlin for Android. It provides basic arithmetic operations such as addition, subtraction, multiplication, and division. The application also supports decimal numbers and has a user-friendly interface.

## Features

- Basic arithmetic operations: addition, subtraction, multiplication, and division.
- Supports decimal numbers.
- Clear and delete functions.
- Edge-to-edge display support.

## Setup

To run this project, you will need to have Android Studio installed. Follow these steps:

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Run the project on an emulator or a physical device.

## Project Structure

The project follows the standard Android project structure:

- `MainActivity.kt`: This is the main activity file where the logic of the calculator is implemented.
- `activity_main.xml`: This is the layout file for the main activity. It defines the UI for the calculator.
- `themes.xml`, `styles.xml`: These files define the themes and styles used in the application.
- `AndroidManifest.xml`: This is the manifest file that describes the essential information about the application to the Android build tools, the Android operating system, and Google Play.
- `build.gradle.kts (app)`: This is the build configuration file for the app module. Here we defined the `buildFeatures` to enable view binding.

## Dependencies

The project uses the following dependencies:

- AndroidX Core KTX
- AndroidX AppCompat
- Material Design
- AndroidX Activity
- AndroidX ConstraintLayout

## Build

The project uses Gradle for building. The `build.gradle.kts` file in the `app` directory contains the build configuration.

## Contributions

Contributions are welcome. Please open an issue before making a pull request.

## License

This project is licensed under the MIT License.