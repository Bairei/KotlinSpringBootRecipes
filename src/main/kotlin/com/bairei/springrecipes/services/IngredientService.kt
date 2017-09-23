package com.bairei.springrecipes.services

import com.bairei.springrecipes.commands.IngredientCommand

interface IngredientService {
    fun findByRecipeIdAndIngredientId(recipeId: Long, ingredientId: Long): IngredientCommand
    fun saveIngredientCommand(command: IngredientCommand): IngredientCommand?
}