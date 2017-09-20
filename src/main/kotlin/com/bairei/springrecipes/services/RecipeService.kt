package com.bairei.springrecipes.services

import com.bairei.springrecipes.domain.Recipe
import java.util.*
import kotlin.collections.HashSet

interface RecipeService {
    fun findAll() : Set<Recipe>
}