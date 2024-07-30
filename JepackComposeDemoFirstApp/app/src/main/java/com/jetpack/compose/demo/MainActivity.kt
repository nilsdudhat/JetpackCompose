package com.jetpack.compose.demo

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.compose.demo.ui.theme.JetpackComposeDemoTheme

data class Message(val image: Int, val author: String, val body: String)

val list = mutableListOf(
    Message(
        image = R.drawable.profile_picture,
        author = "Jetpack Compose",
        body = "The conversation is getting more interesting"
    ),
    Message(
        image = R.drawable.profile_picture,
        author = "Jetpack Compose",
        body = "It's time to play with animations!"
    ),
    Message(
        image = R.drawable.profile_picture,
        author = "Jetpack Compose",
        body = "You will add the ability to expand a message to show a longer one, animating both the content size and the background color."
    ),
    Message(
        image = R.drawable.profile_picture,
        author = "Jetpack Compose",
        body = "To store this local UI state, you need to keep track of whether a message has been expanded or not."
    ),
    Message(
        image = R.drawable.profile_picture,
        author = "Jetpack Compose",
        body = "To keep track of this state change, you have to use the functions remember and mutableStateOf."
    ),
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(messages = list)
                }
            }
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    Row(
        modifier = Modifier.padding(all = 8.dp),
        verticalAlignment = CenterVertically,
    ) {
        Image(
            painter = painterResource(id = message.image),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                ),
        )
        Spacer(modifier = Modifier.width(8.dp))

        // // We keep track if the message is expanded or not in this variable
        var isExpanded by remember {
            mutableStateOf(false)
        }

        val surfaceColor by animateColorAsState(
            if (isExpanded)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = message.author,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp),
            ) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message: Message ->
            run {
                MessageCard(message = message)
            }
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
fun PreviewConversation() {
    JetpackComposeDemoTheme {
        Surface {
            Conversation(messages = list)
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
fun PreviewMessageCard() {
    JetpackComposeDemoTheme {
        Surface {
            MessageCard(
                Message(
                    author = "JetPack Compose",
                    body = "Multiple TextViews overriding each other",
                    image = R.drawable.profile_picture
                )
            )
        }
    }
}