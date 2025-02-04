package com.jco.dogapi

import StoreDarkMode
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jco.dogapi.navigation.NavManager
import com.jco.dogapi.ui.theme.DogApiTheme
import com.jco.dogapi.viewModel.DogViewModel
import com.jco.dogapi.views.DogScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel : DogViewModel by viewModels()
        super.onCreate(savedInstanceState)
        setContent {
            val darkModeStore=StoreDarkMode(this)
            val darkMode= darkModeStore.getDarkMode.collectAsState(initial = false)
            DogApiTheme (
                darkTheme=darkMode.value
            ) {
                NavManager(viewModel = viewModel, darkModeStore, darkMode.value)
            }
        }
    }
}
