package com.example.linguaguess.ui.naviagation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.linguaguess.ui.screens.authenticated.chaptersdetail.ChaptersDetailScreen
import com.example.linguaguess.ui.screens.authenticated.collectiondetail.CollectionDetailScreen
import com.example.linguaguess.ui.screens.authenticated.download.DownloadScreen
import com.example.linguaguess.ui.extensions.safeNavigate
import com.example.linguaguess.ui.screens.authenticated.blocksdetail.BlocksDetailScreen
import com.example.linguaguess.ui.screens.authenticated.library.LibraryScreen
import com.example.linguaguess.ui.screens.authenticated.quiz.QuizScreen
import com.example.linguaguess.ui.screens.authenticated.settings.SettingsScreen
import com.example.linguaguess.ui.screens.unauthenticated.login.LoginScreen
import com.example.linguaguess.ui.screens.unauthenticated.register.RegisterScreen


fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Login.route
    ) {

        // Login
        composable(route = NavigationRoutes.Unauthenticated.Login.route) {

            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.Register.route)
                },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
            )


        }

        // Register
        composable(route = NavigationRoutes.Unauthenticated.Register.route) { from ->
            RegisterScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToLogin = {
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Unauthenticated.Login.route
                    )
                }
            )
        }
    }
}

/**
 * Authenticated screens nav graph builder
 */
fun NavGraphBuilder.authenticatedGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Authenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Authenticated.BottomBar.Library.route
    ) {
        /*
        // Dashboard
        composable(route = NavigationRoutes.Authenticated.Dashboard.route) {
            DashboardScreen()
        }
         */


        // Store
        composable(route = NavigationRoutes.Authenticated.BottomBar.Store.route) { from ->
            DownloadScreen(
                onNavigateToCollectionDetail = {
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Authenticated.CollectionDetail.withCollectionId(
                            it
                        )
                    )

                },
                onNavigateToChaptersDetail = {
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Authenticated.ChaptersDetail.withCollectionId(
                            it
                        )
                    )
                }
            )
        }

        // Library

        composable(
            route = NavigationRoutes.Authenticated.BottomBar.Library.route,
        ) { from ->

            LibraryScreen(
                onNavigateToCollectionDetail = {
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Authenticated.CollectionDetail.withCollectionId(
                            it
                        )
                    )
                },
                onNavigateToChaptersDetail = {
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Authenticated.ChaptersDetail.withCollectionId(
                            it
                        )
                    )
                }
            )

        }

        // Settings
        composable(route = NavigationRoutes.Authenticated.BottomBar.Settings.route) {
            SettingsScreen()
        }

        //CollectionJ CollectionDetail
        composable(
            route = NavigationRoutes.Authenticated.CollectionDetail.route,
            arguments = listOf(
                navArgument(ARG_ID) {
                    type = NavType.LongType
                }
            ),
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() },
        ) { from ->
            val collectionId = from.arguments?.getLong(ARG_ID) ?: -1

            CollectionDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },

                onNavigateToChaptersDetail = {
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Authenticated.ChaptersDetail.withCollectionId(
                            it
                        )
                    )
                },
                collectionId = collectionId
            )
        }

        //Chapters
        composable(
            route = NavigationRoutes.Authenticated.ChaptersDetail.route,
            arguments = listOf(
                navArgument(ARG_ID) {
                    type = NavType.LongType
                }
            ),
        ) { from ->
            val collectionId = from.arguments?.getLong(ARG_ID) ?: -1

            ChaptersDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToBlocksDetail = { firstId, secondId, thirdId ->
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Authenticated.BlocksDetail.withIds(
                            firstId,
                            secondId,
                            thirdId
                        )
                    )
                },
                collectionId = collectionId
            )
        }

        //Blocks
        composable(
            route = NavigationRoutes.Authenticated.BlocksDetail.route,
            arguments = listOf(
                navArgument(ARG_ID) {
                    type = NavType.LongType
                },
                navArgument(ARG_ID2) {
                    type = NavType.LongType
                },
                navArgument(ARG_ID3) {
                    type = NavType.LongType
                }
            ),
        ) { from ->
            val collectionId = from.arguments?.getLong(ARG_ID) ?: -1
            val chapterId = from.arguments?.getLong(ARG_ID2) ?: -1
            val chapterNumber = from.arguments?.getLong(ARG_ID3) ?: -1

            BlocksDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToQuiz = { firstId, secondId, thirdId ->
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Authenticated.Quiz.withIds(
                            firstId, secondId, thirdId
                        )
                    )
                },
                collectionId = collectionId,
                chapterId = chapterId,
                chapterNumber = chapterNumber
            )
        }

        // Quiz
        composable(
            route = NavigationRoutes.Authenticated.Quiz.route,
            arguments = listOf(
                navArgument(ARG_ID) {
                    type = NavType.LongType
                },
                navArgument(ARG_ID2) {
                    type = NavType.LongType
                },
                navArgument(ARG_ID3) {
                    type = NavType.LongType
                }
            ),
        ) { from ->
            val collectionId = from.arguments?.getLong(ARG_ID) ?: -1
            val chapterId = from.arguments?.getLong(ARG_ID2) ?: -1
            val blockPosition = from.arguments?.getLong(ARG_ID3) ?: -2

            QuizScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                collectionId = collectionId,
                chapterId = chapterId,
                blockPosition = blockPosition
            )
        }

    }
}
