
package com.example.apirest0111

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText

import com.example.apirest0111.data.model.Post
import com.example.apirest0111.repository.PostRepository
import com.example.apirest0111.ui.screens.PostScreen
import com.example.apirest0111.viewmodel.PostViewModel

import io.mockk.coEvery
import io.mockk.mockk

import org.junit.Rule
import org.junit.Test

class PostScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun el_titulo_de_post_debe_aparecer_en_pantalla() {

        // Datos simulados
        val fakePosts = listOf(
            Post(
                userId = 1,
                id = 1,
                title = "Título 1",
                body = "Contenido 1"
            ),
            Post(
                userId = 2,
                id = 2,
                title = "Título 2",
                body = "Contenido 2"
            )
        )

        // Simulamos el repositorio
        val fakeRepository = mockk<PostRepository>()

        coEvery {
            fakeRepository.getPosts()
        } returns fakePosts

        // Creamos el ViewModel con el repositorio simulado
        val fakeViewModel = PostViewModel(fakeRepository)

        // Renderizamos la interfaz
        composeRule.setContent {
            PostScreen(viewModel = fakeViewModel)
        }

        // Esperamos que aparezcan los títulos
        composeRule.waitUntil(timeoutMillis = 10000) {
            composeRule.onAllNodes(
                androidx.compose.ui.test.hasText("Título: Título 1")
            ).fetchSemanticsNodes().isNotEmpty()
        }

        // Verificamos los títulos
        composeRule.onNodeWithText("Título: Título 1")
            .assertIsDisplayed()

        composeRule.onNodeWithText("Título: Título 2")
            .assertIsDisplayed()
    }
}
