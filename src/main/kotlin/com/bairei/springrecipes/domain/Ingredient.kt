package com.bairei.springrecipes.domain

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Ingredient (@Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
                       var description: String = "",
                       var amount: BigDecimal = BigDecimal.ZERO,
                       @OneToOne (fetch = FetchType.EAGER) var uom: UnitOfMeasure = UnitOfMeasure(),
                       @ManyToOne var recipe: Recipe = Recipe()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Ingredient

        if (id != other.id) return false
        if (description != other.description) return false
        if (amount != other.amount) return false
        if (uom != other.uom) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + amount.hashCode()
        result = 31 * result + uom.hashCode()
        return result
    }
}