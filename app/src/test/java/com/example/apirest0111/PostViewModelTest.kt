
package com.example.apirest0111

import com.example.apirest0111.data.model.Post
import com.example.apirest0111.repository.PostRepository
import com.example.apirest0111.viewmodel.PostViewModel

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly

import io.mockk.coEvery
import io.mockk.mockk

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class PostViewModelTest : StringSpec({

    val dispatcher = StandardTestDispatcher()

    beforeSpec {
        Dispatchers.setMain(dispatcher)
    }

    afterSpec {
        Dispatchers.resetMain()
    }

    "postList debe contener los datos esperados después de fetchPosts()" {

        runTest(dispatcher) {

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

            val fakeRepository = mockk<PostRepository>()

            coEvery { fakeRepository.getPosts() } returns fakePosts

            val testViewModel = PostViewModel(fakeRepository)

            advanceUntilIdle()

            testViewModel.postList.value shouldContainExactly fakePosts
        }
    }
})
