package com.bairei.springrecipes.controllers

import com.bairei.springrecipes.domain.Category
import com.bairei.springrecipes.domain.UnitOfMeasure
import com.bairei.springrecipes.repositories.CategoryRepository
import com.bairei.springrecipes.repositories.UnitOfMeasureRepository
import com.bairei.springrecipes.services.RecipeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
class IndexController @Autowired constructor(private val recipeService: RecipeService) {

    @RequestMapping("/","","/index")
    fun getIndexPage(model: Model): String {
        model.addAttribute("recipes", recipeService.findAll())
        return "index"
    }
}