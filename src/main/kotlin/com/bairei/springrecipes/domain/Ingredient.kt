package com.bairei.springrecipes.domain

import java.math.BigDecimal

class Ingredient (var id: String = "",
                  var description: String = "",
                  var amount: BigDecimal = BigDecimal.ZERO,
                  var uom: UnitOfMeasure = UnitOfMeasure(),
                  var recipe: Recipe? = null
)