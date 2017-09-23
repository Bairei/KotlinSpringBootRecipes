package com.bairei.springrecipes.repositories

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * created by bairei on 9/21/17.
 */
@RunWith(SpringRunner::class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {
    @Autowired
    lateinit var unitOfMeasureRepository : UnitOfMeasureRepository

    @Before
    fun setUp(){

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