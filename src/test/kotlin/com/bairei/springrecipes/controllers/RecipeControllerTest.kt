package com.bairei.springrecipes.controllers

import com.bairei.springrecipes.domain.Recipe
import com.bairei.springrecipes.services.RecipeService
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

/**
 * created by bairei on 21/09/17.
 */
class RecipeControllerTest {

    @Mock
    lateinit var recipeService: RecipeService

    lateinit var recipeController : RecipeController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        recipeController = RecipeController(recipeService)
    }

    @Test
    fun testGetRecipe() {
        val recipe = Recipe()
        recipe.id = 1L

        val mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build()

        `when`(recipeService.findById(anyLong())).thenReturn(recipe)

        mockMvc.perform(get("/recipe/show/1"))
                .andExpect(status().isOk)
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"))
    }
}