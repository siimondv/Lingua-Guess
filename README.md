# Lingua-Guess
This app is designed to help you learn Japanese. You can download collections from the store, each containing multiple chapters. Each chapter is divided into blocks of 30 words, where your goal is to learn and memorize the words, then aim to achieve the highest score possible. It’s a fun and effective way to build your Japanese vocabulary step by step!
<p float="left">
  <img src="https://github.com/user-attachments/assets/4888d2fb-f3d5-4c7b-a124-89fbaed895bd" width="200">
  <img src="https://github.com/user-attachments/assets/353ca91b-2aac-450a-9836-acaada752183" width="200">
  <img src="https://github.com/user-attachments/assets/07437df8-7df6-4bfc-bced-ecbd79d6d557" width="200">
  <img src="https://github.com/user-attachments/assets/d95d4cdc-e124-4595-a747-69339d5d5c7a" width="200">
</p>

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

<table>
  <tr>
    <th>Login Screen </th>
    <th>Register Screen</th>
    <th>Store Screen</th>
    <th>Library Screen</th>

  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/2921823e-319e-4327-9c74-aac3a80cc47a" width="350"></td>
    <td><img src="https://github.com/user-attachments/assets/4dc7fb8e-7f78-4e41-957c-e57685c1b397" width="350"></td>
    <td><img src="https://github.com/user-attachments/assets/6645fe8b-d74d-4b07-bea7-7304402e6386" width="350"></td>
    <td><img src="https://github.com/user-attachments/assets/60974850-4902-43f0-b8d2-7291fb35c6ce" width="350"></td>
  </tr>
  <tr>
    <th>Detail Screen</th>
    <th>Chapter Screen</th>
    <th>Block Screen</th>
    <th>Quiz Screen</th>
  </tr>
  <tr>
    <td><img src="https://github.com/user-attachments/assets/8d5a0922-d629-4b9a-8b31-4ac016c13f66" width="350"></td>
    <td><img src="https://github.com/user-attachments/assets/4813ead6-8000-48bd-8999-884016faa4cc" width="350"></td>
    <td><img src="https://github.com/user-attachments/assets/89c83054-8786-46f8-a011-bc375acdccb6" width="350"></td>
    <td><img src="https://github.com/user-attachments/assets/2ae584ad-c400-49f5-b14d-1dd155a85f42" width="350"></td>
  </tr>
</table>


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


# Token-Based Authentication App
It uses access and refresh tokens for user authentication, allowing secure API calls while managing session validity. Access tokens grant temporary access, while refresh tokens are used to obtain new access tokens without requiring the user to log in again

![image](https://github.com/user-attachments/assets/71b144a0-e803-489d-89e7-b3b324d35658)



















