package com.bairei.springrecipes.domain

class Recipe (var id: String = "",
              var description: String = "",
              var prepTime: Int = 0,
              var cookTime: Int = 0,
              var servings: Int = 0,
              var source: String = "",
              var url: String = "",
              var directions: String = "",
              var difficulty: Difficulty = Difficulty.EASY,
              var ingredients: MutableSet<Ingredient> = emptySet<Ingredient>().toMutableSet(),
              var image: ByteArray = ByteArray(0),
              var notes: Notes = Notes(),
              var categories: MutableSet<Category> = emptySet<Category>().toMutableSet()
){
    fun addIngredient(ingredient: Ingredient): Recipe {
        ingredient.recipe = this
        this.ingredients.add(ingredient)
        return this
    }

//    override fun toString(): String {
//        return "[id=$id, description =$description, prepTime=$prepTime, cookTime=$cookTime, servings=$servings, source= $source," +
//                " "
//    }
}