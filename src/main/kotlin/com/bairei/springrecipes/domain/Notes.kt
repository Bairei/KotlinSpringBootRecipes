package com.bairei.springrecipes.domain

import javax.persistence.*

@Entity
data class Notes(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
            @OneToOne var recipe: Recipe? = null,
            @Lob var recipeNotes: String = ""
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Notes

        if (id != other.id) return false
        if (recipeNotes != other.recipeNotes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + recipeNotes.hashCode()
        return result
    }
}