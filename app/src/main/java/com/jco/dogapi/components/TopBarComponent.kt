package com.jco.dogapi.components

import StoreDarkMode
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ButtonMode(darkModeStore: StoreDarkMode, darkMode:Boolean) {
    val scope = rememberCoroutineScope()
        Text("Modo oscuro/claro")
            Switch(checked = darkMode, onCheckedChange = {isChecked->
                scope.launch {
                    darkModeStore.saveDarkMode(isChecked)
                }
            })
}

@Composable
fun TitleBar(name: String){
    Text(text = name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun MainIconButton(icon: ImageVector, onClick:() -> Unit){
    IconButton(onClick = onClick) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

