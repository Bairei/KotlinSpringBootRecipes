package com.bairei.springrecipes.services

import com.bairei.springrecipes.commands.RecipeCommand
import com.bairei.springrecipes.converters.RecipeCommandToRecipe
import com.bairei.springrecipes.converters.RecipeToRecipeCommand
import com.bairei.springrecipes.domain.Recipe
import com.bairei.springrecipes.repositories.RecipeRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

import org.junit.Assert.assertEquals


/**
 * Created by jt on 6/21/17.
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class RecipeServiceIT {

    @Autowired
    lateinit var recipeService: RecipeService

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    @Autowired
    lateinit var recipeCommandToRecipe: RecipeCommandToRecipe

    @Autowired
    lateinit var recipeToRecipeCommand: RecipeToRecipeCommand

    @Transactional
    @Test
    @Throws(Exception::class)
    fun testSaveOfDescription() {
        //given
        var recipes = recipeRepository.findAll()
        var testRecipe = recipes.iterator().next()
        var testRecipeCommand = recipeToRecipeCommand.convert(testRecipe)

        //when
        testRecipeCommand!!.description = NEW_DESCRIPTION
        var savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand)

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.description)
        assertEquals(testRecipe.id, savedRecipeCommand.id)
        assertEquals(testRecipe.categories.size.toLong(), savedRecipeCommand.categories.size.toLong())
        assertEquals(testRecipe.ingredients.size.toLong(), savedRecipeCommand.ingredients.size.toLong())
    }

    companion object {

        val NEW_DESCRIPTION = "New Description"
    }
}