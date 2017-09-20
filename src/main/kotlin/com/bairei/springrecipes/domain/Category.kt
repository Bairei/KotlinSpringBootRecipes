package com.bairei.springrecipes.domain

import javax.persistence.*

@Entity
data class Category (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
        var description: String = "",
        @ManyToMany(mappedBy = "categories")
        var recipes: MutableSet<Recipe> = emptySet<Recipe>().toMutableSet()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}