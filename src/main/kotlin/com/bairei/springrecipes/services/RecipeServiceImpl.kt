package com.bairei.springrecipes.services

import com.bairei.springrecipes.domain.Recipe
import com.bairei.springrecipes.repositories.RecipeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RecipeServiceImpl @Autowired constructor(private val recipeRepository: RecipeRepository) : RecipeService {

    override fun findAll(): HashSet<Recipe> {
        return recipeRepository.findAll().map { it }.toHashSet()
    }

}