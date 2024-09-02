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
import com.example.linguaguess.ui.screens.unauthenticated.register.RegisterScreen


fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Register.route
    ) {

        // Login
        composable(route = NavigationRoutes.Unauthenticated.Login.route) {
            /*
            LoginScreen(
                onNavigateToRegistration = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.Register.route)
                },
                onNavigateToForgotPassword = {},
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
            )

             */
        }

        // Register
        composable(route = NavigationRoutes.Unauthenticated.Register.route) {
            RegisterScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
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
        startDestination = NavigationRoutes.Authenticated.BottomBar.Store.route
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
            val collectionId = from.arguments?.getLong(ARG_ID) ?: -1 //TODO handle better solution

            CollectionDetailScreen(
                onNavigateBack = {
                    navController.navigateUp()
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

        //Chapters CollectionDetail
        composable(
            route = NavigationRoutes.Authenticated.ChaptersDetail.route,
            arguments = listOf(
                navArgument(ARG_ID) {
                    type = NavType.LongType
                }
            ),
        ) { from ->
            val collectionId = from.arguments?.getLong(ARG_ID) ?: -1 //TODO handle better solution

            ChaptersDetailScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToBlocksDetail = {
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Authenticated.BlocksDetail.withCollectionId(
                            it
                        )
                    )
                },
                chapterId = collectionId
            )
        }

        //Blocks Detail
        composable(
            route = NavigationRoutes.Authenticated.BlocksDetail.route,
            arguments = listOf(
                navArgument(ARG_ID) {
                    type = NavType.LongType
                }
            ),
        ) { from ->
            val chapterId = from.arguments?.getLong(ARG_ID) ?: -1

            BlocksDetailScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToQuiz = {
                    navController.safeNavigate(
                        from = from,
                        route = NavigationRoutes.Authenticated.Quiz.withCollectionId(
                            it
                        )
                    )
                },
                chapterId = chapterId
            )
        }

        // Quiz
        composable(route = NavigationRoutes.Authenticated.Quiz.route) { navBackStackEntry ->

            val collectionId = navBackStackEntry.arguments?.getString(ARG_ID) ?: ""

            QuizScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                collectionId = collectionId
            )
        }

    }
}
