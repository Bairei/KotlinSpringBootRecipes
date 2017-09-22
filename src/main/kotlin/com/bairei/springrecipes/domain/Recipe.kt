package com.bairei.springrecipes.domain

import javax.persistence.*

@Entity
data class Recipe (@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
              var description: String = "",
              var prepTime: Int = 0,
              var cookTime: Int = 0,
              var servings: Int = 0,
              var source: String = "",
              var url: String = "",
              var directions: String = "",
              @Enumerated(value = EnumType.STRING) var difficulty: Difficulty = Difficulty.EASY,
              @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "recipe")
              var ingredients: MutableSet<Ingredient> = emptySet<Ingredient>().toMutableSet(),
              @Lob var image: ByteArray = ByteArray(0),
              @OneToOne(cascade = arrayOf(CascadeType.ALL)) var notes: Notes = Notes(),
              @ManyToMany
              @JoinTable( name = "recipe_category", joinColumns = arrayOf(JoinColumn(name = "recipe_id")),
                                    inverseJoinColumns = arrayOf(JoinColumn(name = "category_id")))
              var categories: MutableSet<Category> = emptySet<Category>().toMutableSet()
)