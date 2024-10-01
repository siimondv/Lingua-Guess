package com.example.linguaguess.ui.naviagation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Store
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.linguaguess.R




const val ARG_ID = "id"
const val ARG_ID2 = "id2"
const val ARG_ID3 = "id3"

sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Unauthenticated(route = "unauthenticated")
        data object Login : Unauthenticated(route = "login")
        data object Register : Unauthenticated(route = "registration")
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Authenticated(route = "authenticated")

        sealed class BottomBar(
            val route: String, val title: Int = 0, val icon: ImageVector = Icons.Filled.Error,
        ) {
            data object Store :
                BottomBar(route = "store", title = R.string.store, icon = Icons.Filled.Store)

            data object Library : BottomBar(
                route = "library", title = R.string.library, icon = Icons.Filled.AllInbox
            )

            data object Settings :
                BottomBar(
                    route = "settings",
                    title = R.string.settings,
                    icon = Icons.Filled.Settings
                )
        }

        data object CollectionDetail : Authenticated(route = "CollectionDetail/{$ARG_ID}") {
            fun withCollectionId(id: String): String {
                return this.route.replace("{$ARG_ID}", id)
            }
        }

        data object ChaptersDetail : Authenticated(route = "ChaptersDetail/{$ARG_ID}") {
            fun withCollectionId(id: String): String {
                return this.route.replace("{$ARG_ID}", id)
            }
        }

        data object BlocksDetail :
            Authenticated(route = "BlocksDetail/{$ARG_ID}/{$ARG_ID2}/{$ARG_ID3}") {
            fun withIds(id: String, id2: String, id3: String): String {
                return this.route
                    .replace("{$ARG_ID}", id)
                    .replace("{$ARG_ID2}", id2)
                    .replace("{$ARG_ID3}", id3)
            }
        }

        data object Quiz : Authenticated(route = "Quiz/{$ARG_ID}/{$ARG_ID2}/{$ARG_ID3}") {
            fun withIds(id: String, id2: String, id3: String): String {
                return this.route
                    .replace("{$ARG_ID}", id)
                    .replace("{$ARG_ID2}", id2)
                    .replace("{$ARG_ID3}", id3)
            }
        }


    }

    //Bottom bar Screens

}