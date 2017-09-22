package com.bairei.springrecipes.services

import com.bairei.springrecipes.commands.RecipeCommand
import com.bairei.springrecipes.domain.Recipe
import java.util.*
import kotlin.collections.HashSet

interface RecipeService {
    fun findAll() : Set<Recipe>

    fun findById(id: Long) : Recipe

    fun saveRecipeCommand(command: RecipeCommand?): RecipeCommand
    fun findCommandById(id: Long): RecipeCommand?
    fun deleteById(id:Long)
}