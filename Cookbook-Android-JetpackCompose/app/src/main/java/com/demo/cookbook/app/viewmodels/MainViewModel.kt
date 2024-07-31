package com.demo.cookbook.app.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.demo.cookbook.app.R
import com.demo.cookbook.app.models.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {

    private val recipeList = mutableStateListOf(
        Recipe(
            imageResource = R.drawable.noodles,
            title = "Ramen",
            ingredients = listOf("Noodles", "Eggs", "Mushrooms", "Carrots", "Soy sauce"),
            description = "Japan’s famous noodles-and-broth dish. It’s meant to be slurped LOUDLY."
        ),
        Recipe(
            imageResource = R.drawable.croissant,
            title = "Croissant",
            ingredients = listOf("Butter", "More butter", "A touch of butter", "Flour"),
            description = "This French pastry is packed with butter and cholesterol, as the best foods are."
        ),
        Recipe(
            imageResource = R.drawable.pizza,
            title = "Pizza",
            ingredients = listOf("Pizza dough", "Tomatoes", "Cheese", "Spinach", "Love"),
            description = "The official food of late-night coding sessions. Millions of programmers can’t be wrong!"
        ),
        Recipe(
            imageResource = R.drawable.produce,
            title = "Veggie Medley",
            ingredients = listOf("Vegetables"),
            description = "We had to put something healthy on the menu..."
        ),
        Recipe(
            imageResource = R.drawable.salad_egg,
            title = "Egg Salad",
            ingredients = listOf("Eggs", "Mayonnaise", "Paprika", "Mustard"),
            description = "It’s really just eggs in tasty, creamy goo. The vegetables in the photo are just for show."
        ),
        Recipe(
            imageResource = R.drawable.smoothie,
            title = "Fruit Smoothie",
            ingredients = listOf("Banana", "Kiwi", "Milk", "Cream", "Ice", "Flax seed"),
            description = "The healthy version of a milkshake. We’ll have a REAL milkshake later."
        )
    )

    private val _recipeListFlow = MutableStateFlow(recipeList)
    val recipeListFlow: StateFlow<List<Recipe>> = _recipeListFlow

    fun addRecipe() {
        recipeList.add(
            Recipe(
                imageResource = R.drawable.fork_spoon,
                title = "Placeholder",
                ingredients = listOf("Ingredient 1", "Ingredient 2", "Ingredient 3"),
                description = "Lorem ipsum yummy yum!"
            )
        )
    }

    fun removeLastRecipe() {
        if (recipeList.isNotEmpty()) {
            recipeList.remove(recipeList.last())
        }
    }
}