package com.example.prototype.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GreetingAndNamesScreen() {
    val persons = rememberSaveable { mutableStateListOf<Person>() }
    var input by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.weight(1f),
                label = { Text("Prénom") },
                singleLine = true
            )

            IconButton(
                onClick = {
                    val name = input.trim()
                    if (name.isNotEmpty()) {
                        persons.add(Person(name = name))
                        input = ""
                    }
                },
                enabled = input.isNotBlank(),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(imageVector = Icons.Default.PersonAdd, contentDescription = null)
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = persons, key = { it.name + persons.indexOf(it) }) { p ->
                Text("👤 ${p.name}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}