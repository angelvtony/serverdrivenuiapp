package com.example.uilayout.ui.dashboard.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.uilayout.data.model.LayoutItem

@Composable
fun RenderLayoutItem(item: LayoutItem) {
    when (item.type.lowercase()) {
        "text", "textview" -> {
            Text(
                text = item.text ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        "image", "imageview" -> {
            AsyncImage(
                model = item.url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(vertical = 8.dp)
            )
        }

        "card" -> {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(item.title ?: "", style = MaterialTheme.typography.titleMedium)
                    Text(item.value ?: "", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        "button" -> {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(item.text ?: "Button")
            }
        }

        "radio" -> {
            val options = item.options ?: emptyList()
            var selectedIndex by remember { mutableStateOf(item.selected ?: -1) }

            Column(modifier = Modifier.fillMaxWidth()) {
                options.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = option)
                    }
                }
            }
        }

        else -> {
            Text(
                text = "⚠️ Unsupported component type: ${item.type}",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}
