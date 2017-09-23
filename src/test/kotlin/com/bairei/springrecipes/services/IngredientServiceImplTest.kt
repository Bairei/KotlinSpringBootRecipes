package com.bairei.springrecipes.services

import com.bairei.springrecipes.commands.IngredientCommand
import com.bairei.springrecipes.converters.IngredientCommandToIngredient
import com.bairei.springrecipes.converters.IngredientToIngredientCommand
import com.bairei.springrecipes.converters.UnitOfMeasureCommandToUnitOfMeasure
import com.bairei.springrecipes.converters.UnitOfMeasureToUnitOfMeasureCommand
import com.bairei.springrecipes.domain.Ingredient
import com.bairei.springrecipes.domain.Recipe
import com.bairei.springrecipes.repositories.RecipeRepository
import com.bairei.springrecipes.repositories.UnitOfMeasureRepository
import com.nhaarman.mockito_kotlin.any
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.Optional

import org.junit.Assert.assertEquals
import org.mockito.Mockito.*


class IngredientServiceImplTest {

    @Mock
    lateinit var recipeRepository: RecipeRepository

    @Mock
    lateinit var unitOfMeasureRepository: UnitOfMeasureRepository

    @Mock
    lateinit var unitOfMeasureService: UnitOfMeasureService

    lateinit var ingredientService: IngredientService

    private val ingredientToIngredientCommand: IngredientToIngredientCommand

    private val ingredientCommandToIngredient : IngredientCommandToIngredient

    //init converters
    init {
        this.ingredientToIngredientCommand = IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand())
        this.ingredientCommandToIngredient = IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure())
    }

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        ingredientService = IngredientServiceImpl(ingredientToIngredientCommand, recipeRepository, ingredientCommandToIngredient, unitOfMeasureRepository)
    }

    @Test
    @Throws(Exception::class)
    fun findByRecipeIdAndId() {
    }

    @Test
    @Throws(Exception::class)
    fun findByRecipeIdAndIngredientIdHappyPath() {
        //given
        val recipe = Recipe()
        recipe.id = 1L

        val ingredient1 = Ingredient()
        ingredient1.id = 1L

        val ingredient2 = Ingredient()
        ingredient2.id = 1L

        val ingredient3 = Ingredient()
        ingredient3.id = 3L

        recipe.addIngredient(ingredient1)
        recipe.addIngredient(ingredient2)
        recipe.addIngredient(ingredient3)
        val recipeOptional = Optional.of(recipe)

        `when`(recipeRepository.findById(anyLong())).thenReturn(recipeOptional)

        //then
        val ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L)

        //when
        assertEquals(3L, ingredientCommand.id)
        assertEquals(1L, ingredientCommand.recipeId)
        verify<RecipeRepository>(recipeRepository, times(1)).findById(anyLong())
    }

    @Test
    @Throws(Exception::class)
    fun testSaveRecipeCommand() {
        //given
        val command = IngredientCommand()
        command.id = 3L
        command.recipeId = 2L

        val recipeOptional = Optional.of(Recipe())

        val savedRecipe = Recipe()
        savedRecipe.addIngredient(Ingredient())
        savedRecipe.ingredients.iterator().next().id = 3L

        `when`(recipeRepository.findById(anyLong())).thenReturn(recipeOptional)
        `when`<Recipe>(recipeRepository.save(com.nhaarman.mockito_kotlin.any())).thenReturn(savedRecipe)

        //when
        val savedCommand = ingredientService.saveIngredientCommand(command)

        //then
        assertEquals(java.lang.Long.valueOf(3L), savedCommand!!.id)
        verify(recipeRepository, times(1)).findById(anyLong())
        verify(recipeRepository, times(1)).save(any(Recipe::class.java))

    }

}