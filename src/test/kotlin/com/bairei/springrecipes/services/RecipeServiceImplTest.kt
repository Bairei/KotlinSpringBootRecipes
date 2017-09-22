package com.bairei.springrecipes.services

import com.bairei.springrecipes.domain.Recipe
import com.bairei.springrecipes.repositories.RecipeRepository
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.*

class RecipeServiceImplTest {

    lateinit var recipeService: RecipeServiceImpl

    @Mock
    lateinit var recipeRepository: RecipeRepository

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        recipeService = RecipeServiceImpl(recipeRepository)
    }

    @Test
    @Throws(Exception::class)
    fun getRecipes() {

        var recipe : Recipe = Recipe()
        var recipesData = HashSet<Recipe>()
        recipesData.add(recipe)

        `when`(recipeService.findAll()).thenReturn(recipesData)

        val recipes: Set<Recipe> = recipeService.findAll()

        assertEquals(recipes.size, 1)
        verify(recipeRepository, times(1)).findAll()
    }

    @Test
    fun getRecipeByIdTest() {
        val recipe = Recipe()
        recipe.id = 1L
        val recipeOptional = Optional.of(recipe)


        `when`(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(recipeOptional)

        val recipeReturned = recipeService.findById(1L)

        assertNotNull("Null recipe returned", recipeReturned)
        verify(recipeRepository, times(1)).findById(anyLong())
        verify(recipeRepository, never()).findAll()
    }

}
