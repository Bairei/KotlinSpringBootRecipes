package com.bairei.springrecipes.bootstrap

import com.bairei.springrecipes.domain.Category
import com.bairei.springrecipes.domain.UnitOfMeasure
import com.bairei.springrecipes.repositories.CategoryRepository
import com.bairei.springrecipes.repositories.UnitOfMeasureRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
@Profile("dev", "prod")
class BootStrapMySQL(private val categoryRepository: CategoryRepository,
                     private val unitOfMeasureRepository: UnitOfMeasureRepository) : ApplicationListener<ContextRefreshedEvent> {

    private val log : Logger = LoggerFactory.getLogger(this::class.java)

    override fun onApplicationEvent(contextRefreshedEvent: ContextRefreshedEvent) {

        if (categoryRepository.count() == 0L) {
            log.debug("Loading Categories")
            loadCategories()
        }

        if (unitOfMeasureRepository.count() == 0L) {
            log.debug("Loading UOMs")
            loadUom()
        }
    }

    private fun loadCategories() {
        val cat1 = Category()
        cat1.description = "American"
        categoryRepository.save(cat1)

        val cat2 = Category()
        cat2.description = "Italian"
        categoryRepository.save(cat2)

        val cat3 = Category()
        cat3.description = "Mexican"
        categoryRepository.save(cat3)

        val cat4 = Category()
        cat4.description = "Fast Food"
        categoryRepository.save(cat4)
    }

    private fun loadUom() {
        val uom1 = UnitOfMeasure()
        uom1.description = "Teaspoon"
        unitOfMeasureRepository.save(uom1)

        val uom2 = UnitOfMeasure()
        uom2.description = "Tablespoon"
        unitOfMeasureRepository.save(uom2)

        val uom3 = UnitOfMeasure()
        uom3.description = "Cup"
        unitOfMeasureRepository.save(uom3)

        val uom4 = UnitOfMeasure()
        uom4.description = "Pinch"
        unitOfMeasureRepository.save(uom4)

        val uom5 = UnitOfMeasure()
        uom5.description = "Ounce"
        unitOfMeasureRepository.save(uom5)

        val uom6 = UnitOfMeasure()
        uom6.description = "Each"
        unitOfMeasureRepository.save(uom6)

        val uom7 = UnitOfMeasure()
        uom7.description = "Pint"
        unitOfMeasureRepository.save(uom7)

        val uom8 = UnitOfMeasure()
        uom8.description = "Dash"
        unitOfMeasureRepository.save(uom8)
    }
}