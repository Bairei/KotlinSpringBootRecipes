package com.bairei.springrecipes.controllers

import com.bairei.springrecipes.domain.Category
import com.bairei.springrecipes.domain.UnitOfMeasure
import com.bairei.springrecipes.repositories.CategoryRepository
import com.bairei.springrecipes.repositories.UnitOfMeasureRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@Controller
class IndexController @Autowired constructor(private val categoryRepository: CategoryRepository, private val unitOfMeasureRepository: UnitOfMeasureRepository) {

    @RequestMapping("/","","/index")
    fun getIndexPage(): String {
        val optionalCategory: Optional<Category> = categoryRepository.findByDescription("American")
        val unitOfMeasureOptional: Optional<UnitOfMeasure> = unitOfMeasureRepository.findByDescription("Teaspoon")

        println("Cat Id is: ${optionalCategory.get().id}")
        println("UOM Id is: ${unitOfMeasureOptional.get().id}")
        return "index"
    }
}