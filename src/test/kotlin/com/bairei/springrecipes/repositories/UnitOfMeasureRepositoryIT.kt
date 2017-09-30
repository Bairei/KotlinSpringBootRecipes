package com.bairei.springrecipes.repositories

import com.bairei.springrecipes.bootstrap.RecipeBootstrap
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * created by bairei on 9/21/17.
 */
@RunWith(SpringRunner::class)
@DataMongoTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    lateinit var unitOfMeasureRepository : UnitOfMeasureRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var recipeRepository: RecipeRepository


    @Before
    fun setUp(){
        recipeRepository.deleteAll()
        unitOfMeasureRepository.deleteAll()
        categoryRepository.deleteAll()
        val recipeBootstrap = RecipeBootstrap(recipeRepository,categoryRepository,unitOfMeasureRepository)
        recipeBootstrap.onApplicationEvent(null)
    }

    @Test
    fun findByDescription(){
        val uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon")
        assertEquals("Teaspoon", uomOptional.get().description)
    }

    @Test
    fun findByDescriptionCup(){
        val uomOptional = unitOfMeasureRepository.findByDescription("Cup")
        assertEquals("Cup", uomOptional.get().description)
    }

}