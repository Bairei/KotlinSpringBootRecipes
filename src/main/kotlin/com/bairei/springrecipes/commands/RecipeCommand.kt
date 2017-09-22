package com.bairei.springrecipes.commands

import com.bairei.springrecipes.domain.Difficulty

class RecipeCommand (
        var id: Long = 0,
        var description: String = "",
        var prepTime: Int = 0,
        var cookTime: Int = 0,
        var servings: Int = 0,
        var source: String = "",
        var url: String = "",
        var directions: String = "",
        var ingredients: MutableSet<IngredientCommand> = emptySet<IngredientCommand>().toHashSet(),
        var difficulty: Difficulty = Difficulty.EASY,
        var notes : NotesCommand = NotesCommand(),
        var categories : MutableSet<CategoryCommand> = emptySet<CategoryCommand>().toHashSet()
)