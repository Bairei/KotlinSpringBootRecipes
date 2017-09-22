package com.bairei.springrecipes.commands

import java.math.BigDecimal


class IngredientCommand {
    var id: Long = 0
    var description: String = ""
    var amount: BigDecimal = BigDecimal.ZERO
    var uom: UnitOfMeasureCommand = UnitOfMeasureCommand()
}