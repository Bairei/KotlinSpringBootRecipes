package com.bairei.springrecipes.converters

import com.bairei.springrecipes.commands.IngredientCommand
import com.bairei.springrecipes.domain.Ingredient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component

@Component
class IngredientToIngredientCommand @Autowired constructor(private val uomConverter: UnitOfMeasureToUnitOfMeasureCommand) : Converter<Ingredient, IngredientCommand> {

    @Synchronized
    @Nullable
    override fun convert(source: Ingredient?): IngredientCommand? {
        if (source == null) return null

        val ingredientCommand = IngredientCommand()
        ingredientCommand.id = source.id
        ingredientCommand.amount = source.amount
        ingredientCommand.description = source.description
        ingredientCommand.unitOfMeasure = uomConverter.convert(source.uom)!!
        return ingredientCommand
    }
}