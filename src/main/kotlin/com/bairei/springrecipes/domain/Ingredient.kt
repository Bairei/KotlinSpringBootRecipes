package com.bairei.springrecipes.domain

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Ingredient (@Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
                       var description: String = "",
                       var amount: BigDecimal = BigDecimal.ZERO,
                       @OneToOne (fetch = FetchType.EAGER) var uom: UnitOfMeasure = UnitOfMeasure(),
                       @ManyToOne var recipe: Recipe = Recipe()
)