package com.bairei.springrecipes.domain

class Notes(var id: String = "",
            var recipe: Recipe? = null,
            var recipeNotes: String = ""
)