# Lingua-Guess
This app is designed to help you learn Japanese. You can download collections from the store, each containing multiple chapters. Each chapter is divided into blocks of 30 words, where your goal is to learn and memorize the words, then aim to achieve the highest score possible. It’s a fun and effective way to build your Japanese vocabulary step by step!

![4](https://github.com/user-attachments/assets/8f0b71a6-e62f-4000-baed-b0f6cd53d6a5)

Notes:
- This app uses a custom local API that is not publicly available. If you would like to run the app with your own API, please create a suitable API and update the URL in the BuildConfig file.
- However, you can test the application with mocked data selecting the "mock" build variant.

# Clean Architecture & MVVM
This project follows Clean Architecture principles and implements the MVVM (Model-View-ViewModel) design pattern for organizing code and ensuring separation of concerns.

There are 3 main modules in the project:
- [data](data) This module handles data management, including repositories, data sources, and the logic for interacting with APIs and local databases.
- [domain](domain) Contains the core business logic and use cases that define how data is processed and accessed, serving as a bridge between the data and UI layers.
- [ui](ui) Responsible for the user interface, this module includes Activities, Fragments, ViewModels, and other components that present data to the user and handle user interactions.


![image](https://github.com/user-attachments/assets/6d785207-944d-4a9c-8900-621641d0e15e)

## ScreenShots
![5](https://github.com/user-attachments/assets/a4d7b8d5-cbe8-4ee5-95a0-fe18c24b7baa)
![6](https://github.com/user-attachments/assets/f47cee19-abe4-4885-b3d1-feb4985930fd)



# Features and technologies

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - To improve performance by doing I/O tasks out of main thread asynchronously.
- [Flow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [Jetpack Compose](https://developer.android.com/jetpack/compose?gclsrc=ds&gclsrc=ds) - Jetpack Compose is Android’s recommended modern toolkit for building native UI
  - [Navigation](https://developer.android.com/develop/ui/compose/navigation) - Handle in-app navigation in a declarative manner with Jetpack Compose Navigation to create seamless navigation between composables.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - A library designed to load and display large data sets in chunks, allowing for efficient scrolling and improved performance.
- [Retrofit](https://square.github.io/retrofit/) For Network requests to the API.
- [OkHttp3](https://square.github.io/okhttp/) - OkHttp is an HTTP client for Android that’s efficient by default.
  - **Authenticator** is used to modify requests and responsesI.
  - **Interceptor** is used to handle authentication challenges (e.g., refreshing tokens after a 401 Unauthorized response).
- [Coil](https://coil-kt.github.io/coil/compose/) - An image loading library for Android backed by Kotlin Coroutines.
- [Dagger-Hilt](https://dagger.dev/hilt/) For [Dependency injection (DI)](https://developer.android.com/training/dependency-injection)
- [Room database](https://developer.android.com/jetpack/androidx/releases/room) - Persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
- [Lottie](https://airbnb.design/lottie) - Lottie is an Android, iOS and React Native library that renders After Effects animations in real time.





















