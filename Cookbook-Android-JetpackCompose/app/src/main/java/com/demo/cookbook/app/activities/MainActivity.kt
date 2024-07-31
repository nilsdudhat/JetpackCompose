package com.demo.cookbook.app.activities

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.cookbook.app.models.Recipe
import com.demo.cookbook.app.ui.theme.CookbookAndroidJetpackComposeTheme
import com.demo.cookbook.app.viewmodels.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = MainViewModel()

            CookbookAndroidJetpackComposeTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Cookbook(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Surface(
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.secondary,
        ),
        shadowElevation = 2.dp,
        tonalElevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = recipe.imageResource),
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(144.dp)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth(0.30f)) {
                        for (ingredient in recipe.ingredients) {
                            Text(
                                "• $ingredient",
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    }
                    Text(
                        text = recipe.description,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {
            Text(
                text = "Cookbook",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.testTag("topAppBarText"),
            )
        },
        modifier = Modifier.testTag("topAppBar")
    )
}

@Composable
fun RecipeList(viewModel: MainViewModel) {
    LazyColumn(contentPadding = PaddingValues(bottom = 16.dp)) {
        items(viewModel.recipeListFlow.value) {
            RecipeCard(recipe = it)
        }
    }
}

@Composable
fun Cookbook(viewModel: MainViewModel) {
    Box {
        Column {
            AppBar()
            RecipeList(viewModel)
        }
        Buttons(
            viewModel,
            modifier = Modifier.align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun Buttons(viewModel: MainViewModel, modifier: Modifier) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.then(Modifier.padding(horizontal = 8.dp)),
    ) {
        Button(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
                .testTag("addRecipeButton"),
            onClick = { viewModel.addRecipe() },
        ) {
            Text(text = "Add Recipe")
        }
        Button(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
                .testTag("removeLastRecipeButton"),
            onClick = { viewModel.removeLastRecipe() },
        ) {
            Text(text = "Remove Recipe")
        }
    }
}

@Preview(
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
fun RecipeCardPreview() {
    val viewModel = MainViewModel()

    CookbookAndroidJetpackComposeTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Cookbook(viewModel = viewModel)
        }
    }
}