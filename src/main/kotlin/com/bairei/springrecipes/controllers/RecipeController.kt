package com.bairei.springrecipes.controllers

import com.bairei.springrecipes.services.RecipeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

/**
 * created by bairei on 21/09/17.
 */
@Controller
class RecipeController @Autowired constructor(val recipeService: RecipeService){

    @RequestMapping("/recipe/show/{id}")
    fun showById(@PathVariable id: Long, model:Model) : String{
        model.addAttribute("recipe", recipeService.findById(id))

        return "recipe/show"
    }
}