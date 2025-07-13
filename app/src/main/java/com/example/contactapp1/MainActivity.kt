package com.example.contactapp1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.contactapp1.presentation.ContactViewModel
import com.example.contactapp1.presentation.navigation.NavGraph
import com.example.contactapp1.ui.theme.ContactApp1Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel = hiltViewModel<ContactViewModel>()
            val navHostController = rememberNavController()
            ContactApp1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<ContactViewModel>()
    val navHostController = rememberNavController()

    val showSplash = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            showSplash.value = false
        }, 3000)
    }

    if (showSplash.value) {
        SplashScreen()
    } else {
        NavGraph(
            navHostController = navHostController,
            viewModel = viewModel
        )
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.phonepreview),
                contentDescription = "Splash Screen",
                modifier = Modifier
                    .size(700.dp)
                    .padding(15.dp)
                    .background(color = Color.Black)
            )
            BasicText(
                text = "Welcome to the Contact Saver",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color.White,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}
