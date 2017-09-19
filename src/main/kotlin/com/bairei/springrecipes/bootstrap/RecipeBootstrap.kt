package com.bairei.springrecipes.bootstrap

import com.bairei.springrecipes.domain.Difficulty
import com.bairei.springrecipes.domain.Ingredient
import com.bairei.springrecipes.domain.Notes
import com.bairei.springrecipes.domain.Recipe
import com.bairei.springrecipes.repositories.CategoryRepository
import com.bairei.springrecipes.repositories.RecipeRepository
import com.bairei.springrecipes.repositories.UnitOfMeasureRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class RecipeBootstrap @Autowired constructor(val recipeRepository: RecipeRepository,
                                             val categoryRepository: CategoryRepository,
                                             val unitOfMeasureRepository: UnitOfMeasureRepository
                                            ) : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent?) {
        val guacamole = Recipe()
        guacamole.cookTime = 10
        guacamole.difficulty = Difficulty.EASY
        guacamole.description = "The BEST guacamole! So easy to make with ripe avocados, salt," +
                " serrano chiles, cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips."
        guacamole.directions = "1 Cut avocado, remove flesh.\n2 Mash with a fork.\n3 Add salt, lime juice, and the rest" +
                "\n4 Cover with plastic and chill to store"
        val guacamoleNotes = Notes()
        guacamoleNotes.recipe = guacamole
        guacamoleNotes.recipeNotes = "Be careful handling chiles if using. Wash your hands thoroughly after handling and" +
                " do not touch your eyes or the area near your eyes with your hands for several hours."
        guacamole.notes = guacamoleNotes
        guacamole.url = "http://www.simplyrecipes.com/recipes/perfect_guacamole/"
        guacamole.source = "http://www.simplyrecipes.com/recipes/perfect_guacamole/"
        guacamole.servings = 4
        val mexican = categoryRepository.findByDescription("Mexican").get()
        mexican.recipes.add(guacamole)
        guacamole.categories.add(mexican)
        // Avocados
        val avocados = Ingredient()
        avocados.description = "Ripe avocados"
        avocados.amount = BigDecimal(2)
        avocados.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        avocados.recipe = guacamole
        // Kosher Salt
        val kosherSalt = Ingredient()
        kosherSalt.description = "Kosher Salt"
        kosherSalt.amount = BigDecimal(0.5)
        kosherSalt.uom = unitOfMeasureRepository.findByDescription("Teaspoon").get()
        kosherSalt.recipe = guacamole
        // Lemon or lime juice
        val lemonOrLimeJuice = Ingredient()
        lemonOrLimeJuice.amount = BigDecimal(1)
        lemonOrLimeJuice.uom = unitOfMeasureRepository.findByDescription("Tablespoon").get()
        lemonOrLimeJuice.recipe = guacamole
        lemonOrLimeJuice.description = "Fresh lime or lemon juice"
        // Onions
        val onions = Ingredient()
        onions.description = "minced red onion or thinly sliced green onion"
        onions.amount = BigDecimal(2)
        onions.uom = unitOfMeasureRepository.findByDescription("Tablespoon").get()
        onions.recipe = guacamole
        // serrano chiles
        val serranoChiles = Ingredient()
        serranoChiles.description = "serrano chiles, stems and seeds removed, minced"
        serranoChiles.amount = BigDecimal(2)
        serranoChiles.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        serranoChiles.recipe = guacamole
        // cilantro
        val cilantro = Ingredient()
        cilantro.description = "cilantro (leaves and tender stems), finely chopped"
        cilantro.amount = BigDecimal(2)
        cilantro.uom = unitOfMeasureRepository.findByDescription("Tablespoon").get()
        cilantro.recipe = guacamole
        // black pepper
        val pepper = Ingredient()
        pepper.recipe = guacamole
        pepper.uom = unitOfMeasureRepository.findByDescription("Dash").get()
        pepper.amount = BigDecimal(1)
        pepper.description = "freshly grated black pepper"
        // tomato
        val tomato = Ingredient()
        tomato.description = "tomato, seeds and pulp removed, chopped"
        tomato.amount = BigDecimal(0.5)
        tomato.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        tomato.recipe = guacamole

        // add and save
        guacamole.ingredients.add(avocados)
        guacamole.ingredients.add(kosherSalt)
        guacamole.ingredients.add(lemonOrLimeJuice)
        guacamole.ingredients.add(onions)
        guacamole.ingredients.add(serranoChiles)
        guacamole.ingredients.add(cilantro)
        guacamole.ingredients.add(pepper)
        guacamole.ingredients.add(tomato)
        recipeRepository.save(guacamole)

        // chicken tacos
        val chickenTacos = Recipe()
        chickenTacos.url = "http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/"
        chickenTacos.source="http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/"
        chickenTacos.directions = """1 Prepare a gas or charcoal grill for medium-high, direct heat.
2 Make the marinade and coat the chicken
3 Grill the chicken
4 Warm the tortillas
5 Assemble the tacos"""
        chickenTacos.difficulty = Difficulty.EASY
        chickenTacos.cookTime = 30
        val fastFood = categoryRepository.findByDescription("Fast Food").get()
        fastFood.recipes.add(chickenTacos)
        val tacosNotes = Notes()
        tacosNotes.recipe = chickenTacos
        tacosNotes.recipeNotes = "If you can't find ancho chili powder, you replace the ancho chili," +
                " the oregano, and the cumin with 2 1/2 tablespoons regular chili powder," +
                " though the flavor won't be quite the same."
        chickenTacos.notes = tacosNotes
        chickenTacos.servings = 6

        // chili powder
        val chiliPowder = Ingredient()
        chiliPowder.recipe = chickenTacos
        chiliPowder.amount = BigDecimal(2)
        chiliPowder.uom = unitOfMeasureRepository.findByDescription("Teaspoon").get()
        chiliPowder.description = "ancho chili powder"
        chickenTacos.ingredients.add(chiliPowder)
        // oregano
        val oregano = Ingredient()
        oregano.recipe = chickenTacos
        oregano.amount = BigDecimal(1)
        oregano.uom = unitOfMeasureRepository.findByDescription("Teaspoon").get()
        oregano.description = "dried oregano"
        chickenTacos.ingredients.add(oregano)
        // cumin
        val cumin = Ingredient()
        cumin.description = "dried cumin"
        cumin.recipe = chickenTacos
        cumin.amount = BigDecimal(1)
        cumin.uom = unitOfMeasureRepository.findByDescription("Teaspoon").get()
        chickenTacos.ingredients.add(cumin)
        // sugar
        val sugar = Ingredient()
        sugar.recipe = chickenTacos
        sugar.description = "Sugar"
        sugar.amount = BigDecimal(1)
        sugar.uom = unitOfMeasureRepository.findByDescription("Teaspoon").get()
        chickenTacos.ingredients.add(sugar)
        // salt
        val salt = Ingredient()
        salt.description = "Salt"
        salt.recipe = chickenTacos
        salt.amount = BigDecimal(0.5)
        salt.uom = unitOfMeasureRepository.findByDescription("Teaspoon").get()
        chickenTacos.ingredients.add(salt)
        // garlic
        val garlic = Ingredient()
        garlic.description = "clove garlic, finely chopped"
        garlic.recipe = chickenTacos
        garlic.amount = BigDecimal(1)
        garlic.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        chickenTacos.ingredients.add(garlic)
        // orange zest
        val orangeZest = Ingredient()
        orangeZest.description = "finely grated orange zest"
        orangeZest.amount = BigDecimal(1)
        orangeZest.uom = unitOfMeasureRepository.findByDescription("Tablespoon").get()
        orangeZest.recipe = chickenTacos
        chickenTacos.ingredients.add(orangeZest)
        // orange juice
        val orangeJuice = Ingredient()
        orangeJuice.description = "fresh-squeezed orange juice"
        orangeJuice.recipe = chickenTacos
        orangeJuice.uom = unitOfMeasureRepository.findByDescription("Tablespoon").get()
        orangeJuice.amount = BigDecimal(3)
        chickenTacos.ingredients.add(orangeJuice)
        // olive oil
        val oliveOil = Ingredient()
        oliveOil.amount = BigDecimal(2)
        oliveOil.uom = unitOfMeasureRepository.findByDescription("Tablespoon").get()
        oliveOil.recipe = chickenTacos
        oliveOil.description = "Olive oil"
        chickenTacos.ingredients.add(oliveOil)
        // chicken thighs
        val chickenThighs = Ingredient()
        chickenThighs.description = "skinless, boneless chicken thighs"
        chickenThighs.recipe = chickenTacos
        chickenThighs.amount = BigDecimal(1.25)
        chickenThighs.uom = unitOfMeasureRepository.findByDescription("Ounce").get()
        chickenTacos.ingredients.add(chickenThighs)
        // tortillas
        val tortillas = Ingredient()
        tortillas.recipe = chickenTacos
        tortillas.description = "small corn tortillas"
        tortillas.amount = BigDecimal(8)
        tortillas.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        chickenTacos.ingredients.add(tortillas)
        // baby arugula
        val arugula = Ingredient()
        arugula.description = "packed baby arugula (3 ounces)"
        arugula.recipe = chickenTacos
        arugula.amount = BigDecimal(3)
        arugula.uom = unitOfMeasureRepository.findByDescription("Cup").get()
        chickenTacos.ingredients.add(arugula)
        // avocados, the same as in guacamole recipe
        val avocados2 = Ingredient()
        avocados2.description = "Medium ripe avocados, sliced"
        avocados2.amount = BigDecimal(2)
        avocados2.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        avocados2.recipe = chickenTacos
        chickenTacos.ingredients.add(avocados2)
        // radishes
        val radish = Ingredient()
        radish.description = "radishes, thinly sliced"
        radish.recipe = chickenTacos
        radish.amount = BigDecimal(4)
        radish.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        chickenTacos.ingredients.add(radish)
        // cherry tomatoes
        val cherryTomatoes = Ingredient()
        cherryTomatoes.description = "cherry tomatoes, halved"
        cherryTomatoes.recipe = chickenTacos
        cherryTomatoes.amount = BigDecimal(0.5)
        cherryTomatoes.uom = unitOfMeasureRepository.findByDescription("Pinch").get()
        chickenTacos.ingredients.add(cherryTomatoes)
        // red onion
        val redOnion = Ingredient()
        redOnion.description = "onion, thinly sliced"
        redOnion.recipe = chickenTacos
        redOnion.amount = BigDecimal(0.25)
        redOnion.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        chickenTacos.ingredients.add(redOnion)
        // cilantro
        val cilantro2 = Ingredient()
        cilantro2.description = "Roughly chopped cilantro"
        cilantro2.amount = BigDecimal(1)
        cilantro2.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        cilantro2.recipe = chickenTacos
        chickenTacos.ingredients.add(cilantro2)
        // sour cream
        val sourCream = Ingredient()
        sourCream.recipe = chickenTacos
        sourCream.description = "sour cream thinned with 1/4 cup milk"
        sourCream.amount = BigDecimal(0.5)
        sourCream.uom = unitOfMeasureRepository.findByDescription("Cup").get()
        chickenTacos.ingredients.add(sourCream)
        // lime
        val lime = Ingredient()
        lime.description = "lime, cut into wedges"
        lime.amount = BigDecimal(1)
        lime.uom = unitOfMeasureRepository.findByDescription("Piece").get()
        lime.recipe = chickenTacos
        chickenTacos.ingredients.add(lime)
        // save tacos
        recipeRepository.save(chickenTacos)
    }



}