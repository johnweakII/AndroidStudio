package com.example.recipebook


enum class Flavor {
    SAVORY, SWEET
}

data class Recipe(
    val title: String,
    val description: String,
    val flavor: Flavor
)

sealed class ListItem {
    data class Header(val flavor: Flavor) : ListItem()
    data class RecipeItem(val recipe: Recipe) : ListItem()
}
