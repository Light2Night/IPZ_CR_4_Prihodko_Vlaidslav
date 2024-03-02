package ua.edu.lntu.cw4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.edu.lntu.cw4.ui.theme.IPZ_CR_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ_CR_4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Body() {
    val navController = rememberNavController()
    val number = rememberSaveable { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "На головну сторінку") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("screen1")
                    }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "screen1",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("screen1") {
                MainPage(navController, number)
            }
            composable("screen2") {
                PageWithText(navController, number)
            }
        }
    }
}

@Composable
fun MainPage(navController: NavHostController, number: MutableState<Int>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        for (i in 1 until 11) {
            Button(onClick = {
                number.value = i
                navController.navigate("screen2")
            }) {
                Text("Перейти на сторінку ${i}")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PageWithText(navController: NavHostController, number: MutableState<Int>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = { navController.navigate("screen1") }) {
            Text("Повернутися")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Number is ${number.value}")
    }
}

@Preview(showBackground = true)
@Composable
fun BodyPreview() {
    Body()
}
