I have integrated movie db api which has movies.
For showing up movies in GridView I have used adapter with GridLayout Manager and span count(row count).
For Architecture pattern I have used MVVM.
For Navigation among fragments I have used Jetpack component of Navigation.
For Fetching Data I have used Retrofit Library along with Kotlin Flow.
For Passing an argument from one fragment to another I have used safe args of navigation component.
For Pagination I have used PagingV3 again a Jetpack Component.
For Dependency Injection I have used Hilt.
Network module sets up the networking dependencies required for making API requests in the application using Retrofit and provides implementations of repository interfaces for fetching movie data and movie details from the API. Dagger Hilt manages the instantiation and injection of these dependencies into other parts of the application.
MovieViewModel and MovieDetailsViewModel class manages the loading state and provides a function for fetching movie details asynchronously from the repository. It ensures that the UI can observe changes to the loading state and movie details using state flows.

Scope for Improvement:
Handling Network and IO exceptions via Interface.
Handling Loaders in an efficient way.
Better Scope for using Roboelectric or Mockito Framework for JUnit Test Cases.
![image](https://github.com/harsha4199/MovieDb/assets/71061989/1d0d4d81-fbf6-4828-9a19-60f26969bab4)
![Movie2](https://github.com/harsha4199/MovieDb/assets/71061989/4a1c4068-e446-44b7-b8d8-50235590d5ce)
