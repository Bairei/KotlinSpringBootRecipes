package com.bairei.springrecipes.controllers

import com.bairei.springrecipes.domain.Recipe
import com.bairei.springrecipes.services.RecipeService
import com.bairei.springrecipes.services.RecipeServiceImpl
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.springframework.ui.Model

class IndexControllerTest {

    lateinit var indexController: IndexController

    @Mock
    lateinit var recipeService: RecipeService

    @Mock
    lateinit var model: Model

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        indexController = IndexController(recipeService)
    }

    @Test
    fun getIndexPage() {
        assertEquals(indexController.getIndexPage(model), "index")
        verify(recipeService, times(1)).findAll()
        verify(model, times(1)).addAttribute(eq("recipes"), anySet<Any>())
    }

}
