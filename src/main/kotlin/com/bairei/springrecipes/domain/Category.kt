package com.bairei.springrecipes.domain


class Category (
        var id: String = "",
        var description: String = "",
        var recipes: MutableSet<Recipe> = emptySet<Recipe>().toMutableSet()
)