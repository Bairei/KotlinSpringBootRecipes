package com.bairei.springrecipes.services

import com.bairei.springrecipes.commands.IngredientCommand
import com.bairei.springrecipes.converters.IngredientCommandToIngredient
import com.bairei.springrecipes.converters.IngredientToIngredientCommand
import com.bairei.springrecipes.repositories.RecipeRepository
import com.bairei.springrecipes.repositories.UnitOfMeasureRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import com.bairei.springrecipes.domain.Recipe
import com.bairei.springrecipes.domain.Ingredient
import org.springframework.util.ClassUtils.isPresent

@Service
class IngredientServiceImpl (private val ingredientToIngredientCommand: IngredientToIngredientCommand,
                             private val recipeRepository: RecipeRepository,
                             private val ingredientCommandToIngredient: IngredientCommandToIngredient,
                             private val unitOfMeasureRepository: UnitOfMeasureRepository) : IngredientService {


    private val log : Logger = LoggerFactory.getLogger(this::class.java)

    override fun findByRecipeIdAndIngredientId(recipeId: Long, ingredientId: Long) : IngredientCommand {
        val recipeOptional = recipeRepository.findById(recipeId)

        if (!recipeOptional.isPresent){
            // todo implement error handling
            log.error("recipe id not found ID: " + recipeId)
        }

        val recipe = recipeOptional.get()

        val ingredientCommandOptional = recipe.ingredients.stream()
                .filter({ingredient -> ingredient.id.equals(ingredientId)})
                .map { ingredient -> ingredientToIngredientCommand.convert(ingredient)}.findFirst()

        if(!ingredientCommandOptional.isPresent){
            // todo implement error handling
            log.error("Ingredient id not found: " + ingredientId)
        }

        return ingredientCommandOptional.get()
    }

    @Transactional
    override fun saveIngredientCommand(command: IngredientCommand): IngredientCommand? {
        val recipeOptional = recipeRepository.findById(command.recipeId)

        if (!recipeOptional.isPresent) {

            //todo toss error if not found!
            log.error("Recipe not found for id: " + command.recipeId)
            return IngredientCommand()
        } else {
            val recipe = recipeOptional.get()

            val ingredientOptional = recipe
                    .ingredients
                    .stream()
                    .filter { (id) -> id.equals(command.id) }
                    .findFirst()

            if (ingredientOptional.isPresent) {
                val ingredientFound = ingredientOptional.get()
                ingredientFound.description = command.description
                ingredientFound.amount = command.amount
                ingredientFound.uom = unitOfMeasureRepository
                        .findById(command.uom.id)
                        .orElseThrow { RuntimeException("UOM NOT FOUND") } //todo address this
            } else {
                //add new Ingredient
                recipe.addIngredient(ingredientCommandToIngredient.convert(command)!!)
            }

            val savedRecipe = recipeRepository.save(recipe)

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedRecipe.ingredients.stream()
                    .filter { recipeIngredients -> recipeIngredients.id.equals(command.id) }
                    .findFirst()
                    .get())!!
        }

    }
}