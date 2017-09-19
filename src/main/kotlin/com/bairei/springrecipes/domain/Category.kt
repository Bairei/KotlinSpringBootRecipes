package com.bairei.springrecipes.domain

import javax.persistence.*

@Entity
data class Category (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
        var description: String = "",
        @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
        var recipes: MutableSet<Recipe> = emptySet<Recipe>().toMutableSet()
)