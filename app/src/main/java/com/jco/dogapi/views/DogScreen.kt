package com.jco.dogapi.views

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.jco.dogapi.viewModel.DogViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DogScreen(viewModel: DogViewModel) {
    // Accede al valor de dogImage desde el ViewModel
    val dogImage by viewModel.dogImage.observeAsState()

    // Obtener el contexto para lanzar el intent
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Random Dog Image") })
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                dogImage?.let { imageUrl ->
                    // Mostrar la imagen del perro
                    Image(
                        painter = rememberImagePainter(imageUrl),
                        contentDescription = "Dog Image",
                        modifier = Modifier.fillMaxWidth().height(300.dp))

                    // Botón para abrir el navegador con el enlace de la imagen
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        // Al hacer clic, abre el navegador con la URL de la imagen
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imageUrl))
                        context.startActivity(intent)
                    }) {
                        Text("Ver Imagen en Navegador")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.fetchRandomDogImage() }) {
                    Text("Fetch New Image")
                }
            }
        }
    )
}
