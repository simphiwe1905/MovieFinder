# 🎬 Cinewatcher

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge\&logo=kotlin\&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge\&logo=android\&logoColor=white)
![Android Studio](https://img.shields.io/badge/Android%20Studio-3DDC84?style=for-the-badge\&logo=androidstudio\&logoColor=white)
![TMDB](https://img.shields.io/badge/TMDB-01B4E4?style=for-the-badge\&logo=themoviedatabase\&logoColor=white)

**Cinewatcer** is a modern Android movie discovery application developed using **Kotlin and Android Studio**. The application connects to the **TMDB (The Movie Database) API** to retrieve movie information and present it through a clean, user-friendly interface.

The application is designed to provide users with an easy way to discover movies while also providing account registration and login functionality.

---

## 📱 Application Overview

Cinewatcher combines a movie discovery platform with a simple user authentication system.

Users can:

* Create an account
* Log into their account
* Browse movie information
* View movie titles and posters
* Retrieve movie information from TMDB
* Search and discover movies
* Navigate through the application's different sections

The application uses a modern **dark cinematic design with blue neon accents** to create a movie-focused user experience.

---

## ✨ Features

### 🔐 User Authentication

Cinewatcher includes a basic local authentication system.

* User registration
* User login
* Email validation
* Password validation
* Saved user credentials
* Login session management
* Login error handling
* Registration navigation

User registration information is currently stored locally using Android's `SharedPreferences`.

> **Note:** This authentication system is intended for the application's current prototype/development stage. A production application would use a secure backend authentication system.

---

### 🎬 Movie Discovery

The application retrieves movie information through the TMDB API.

Movie information can include:

* Movie titles
* Movie posters
* Movie ratings
* Release information
* Movie descriptions
* Other movie-related information provided by TMDB

---

### 🌐 TMDB API Integration

Cinewatcher communicates with the TMDB API using **Retrofit**.

The application uses:

```text
TMDB API
     ↓
Retrofit
     ↓
Movie API Interface
     ↓
Repository / ViewModel
     ↓
Android UI
```

This architecture allows movie information to be retrieved from the internet and displayed dynamically inside the application.

---

## 🛠️ Technologies & Tools

### Programming Language

* **Kotlin**

### Development Environment

* **Android Studio**
* Android SDK
* Gradle

### Networking

* Retrofit
* Gson Converter

### Asynchronous Programming

* Kotlin Coroutines

### Architecture

* ViewModel
* Repository-based structure
* API service interface

### Image Loading

* Glide

### Local Storage

* SharedPreferences

### API

* TMDB API

---

## 🏗️ Project Structure

```text
MovieFinder/
│
├── app/
│   │
│   ├── src/
│   │   └── main/
│   │       │
│   │       ├── java/
│   │       │   └── com/
│   │       │       └── example/
│   │       │           └── moviefinder/
│   │       │               │
│   │       │               ├── MainActivity.kt
│   │       │               ├── activity_login.kt
│   │       │               ├── activity_register.kt
│   │       │               ├── MovieApi.kt
│   │       │               ├── MovieResponse.kt
│   │       │               ├── MovieViewModel.kt
│   │       │               └── RetrofitInstance.kt
│   │       │
│   │       └── res/
│   │           │
│   │           ├── drawable/
│   │           ├── layout/
│   │           ├── mipmap/
│   │           └── values/
│   │
│   └── build.gradle.kts
│
├── gradle/
│
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## 🔄 Application Flow

```text
                    ┌─────────────────┐
                    │   Cinewatcher   │
                    │      Start      │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   Login Screen  │
                    └────────┬────────┘
                             │
                 ┌───────────┴───────────┐
                 │                       │
                 ▼                       ▼
        ┌─────────────────┐     ┌─────────────────┐
        │ Create Account  │     │  Existing User  │
        └────────┬────────┘     └────────┬────────┘
                 │                       │
                 ▼                       │
        ┌─────────────────┐              │
        │ Register Screen │              │
        └────────┬────────┘              │
                 │                       │
                 └───────────┬───────────┘
                             ▼
                    ┌─────────────────┐
                    │  Main Movie UI  │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │   TMDB API      │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │ Movie Results   │
                    └─────────────────┘
```



## 🔌 API Architecture

Cinewatcher uses Retrofit to communicate with TMDB.

The base API URL is:

```text
https://api.themoviedb.org/3/
```

The general request flow is:

```text
Android Application
        │
        ▼
MovieViewModel
        │
        ▼
RetrofitInstance
        │
        ▼
MovieApi
        │
        ▼
TMDB API
        │
        ▼
MovieResponse
        │
        ▼
Movie UI
```



## 🔑 API Configuration

To run the application, a valid TMDB API credential is required.

The application should store the API credential securely rather than committing sensitive credentials directly to GitHub.

For development, the API configuration is used to authenticate requests made to TMDB.

> **Security:** Never upload a private API key, access token, password, or other secret credentials to a public GitHub repository.



## 🎨 User Interface

Cinewatcher follows a modern cinematic design approach.

### Design Characteristics

* Dark background
* Deep navy tones
* Electric blue highlights
* Movie-themed visuals
* Rounded UI components
* Clean typography
* Modern buttons
* Movie poster presentation
* Minimal and intuitive navigation

The application's visual design is intended to create a premium movie-streaming-inspired experience while maintaining a simple interface.



## 📋 Requirements

To build and run Cinewatcher, you will need:

* Android Studio
* Android SDK
* Kotlin support
* Gradle
* Internet connection
* TMDB API credentials
* Android emulator or physical Android device



## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/simphiwe1905/MovieFinder.git
```

### 2. Open the Project

Open the cloned project using **Android Studio**.

### 3. Allow Gradle Sync

Wait for Android Studio to complete the Gradle synchronisation.

### 4. Configure TMDB

Add your TMDB API credentials to the appropriate project configuration.

### 5. Connect a Device

Either:

* Start an Android Emulator, or
* Connect a physical Android device with USB debugging enabled.

### 6. Build the Application

Select:

```text
Build → Make Project
```

### 7. Run the Application

Select:

```text
Run → Run 'app'
```

The application should launch on the selected Android device or emulator.

---

## 🧪 Testing

Cinewatcher should be tested across the following areas:

### Authentication Testing

* Register with valid information
* Attempt registration with missing information
* Enter invalid login credentials
* Login with valid credentials
* Attempt login without an account
* Navigate between Login and Registration

### Movie Testing

* Confirm movies load from TMDB
* Confirm movie titles appear
* Confirm movie posters load correctly
* Confirm API requests work with an internet connection
* Test behaviour when the API is unavailable

### UI Testing

* Test different screen sizes
* Test portrait orientation
* Check button navigation
* Check text readability
* Check poster loading
* Confirm the background and UI elements display correctly

---

## 🗂️ Current Development Status

### Completed

* [x] Android project setup
* [x] Kotlin implementation
* [x] TMDB API integration
* [x] Retrofit configuration
* [x] Gson converter
* [x] Kotlin Coroutines
* [x] Movie API interface
* [x] Movie response models
* [x] ViewModel implementation
* [x] Movie data retrieval
* [x] Movie titles displayed
* [x] Movie poster loading
* [x] Login screen
* [x] Registration screen
* [x] Local user storage
* [x] Login validation
* [x] Registration navigation
* [x] Initial modern UI design

### In Progress

* [ ] Final UI polish
* [ ] Modern cinematic background
* [ ] Settings functionality
* [ ] Additional movie information
* [ ] Final application testing
* [ ] Final bug fixing

---

## 🔮 Future Improvements

Possible future improvements include:

* User profiles
* Favourite movies
* Watchlist functionality
* Movie categories
* Movie details screen
* Advanced movie search
* Genre filtering
* Recommended movies
* Persistent user accounts
* Secure backend authentication
* Cloud database integration
* Offline movie caching
* Improved error handling
* Push notifications
* Dark/light theme support



## ⚠️ Known Limitations

The current version is primarily designed as an Android development project and prototype.

The authentication system currently uses local `SharedPreferences`, meaning user information is stored on the device rather than on a secure remote server.

The application's movie information depends on the availability of the TMDB API and an active internet connection.



## 📚 Third-Party Services

### TMDB

Cinewatcher uses **The Movie Database (TMDB)** to retrieve movie-related information.

TMDB provides the movie data used by the application.

Cinewatcher is not affiliated with or endorsed by TMDB.



👨‍💻 Front-End Developer     
     Busisa Kabi [ST10454918]   

🧑‍💻 Back-End Developers     
     Simphiwe Malindi   [ST10446534]
     Oratile Ntebele    []


IT Software Development Students



📄 License

This project was developed for educational and development purposes.

Movie data and related assets retrieved through TMDB remain subject to their respective terms and policies.



🎬 Cinewatcher

Discover movies. Find your next favourite.


