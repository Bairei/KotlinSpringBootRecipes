package com.bairei.springrecipes.services

import com.bairei.springrecipes.domain.Recipe
import com.bairei.springrecipes.repositories.RecipeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RecipeServiceImpl @Autowired constructor(private val recipeRepository: RecipeRepository) : RecipeService {
    override fun findById(id: Long): Recipe {
        val recipeOptional = recipeRepository.findById(id)
        if (!recipeOptional.isPresent){
            throw RuntimeException("Recipe not found")
        }
        return recipeOptional.get()
    }

    override fun findAll(): Set<Recipe> {
        return recipeRepository.findAll().map { it }.toHashSet()
    }


}