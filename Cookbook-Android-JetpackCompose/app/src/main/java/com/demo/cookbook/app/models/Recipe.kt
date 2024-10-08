package com.demo.cookbook.app.models

import androidx.annotation.DrawableRes

data class Recipe(
    @DrawableRes val imageResource: Int,
    val title: String,
    val ingredients: List<String>,
    val description: String,
)
