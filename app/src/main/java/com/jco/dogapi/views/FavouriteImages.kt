package com.jco.dogapi.views

import StoreDarkMode
import android.annotation.SuppressLint
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.jco.dogapi.components.ButtonMode
import com.jco.dogapi.components.MainIconButton
import com.jco.dogapi.components.TitleBar
import com.jco.dogapi.room.DogCacheEntity
import com.jco.dogapi.viewModel.DogViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteImages(navController: NavController, viewModel: DogViewModel, darkModeStore: StoreDarkMode, darkMode:Boolean) {
    // Llamar a getAllFavoriteDogs para cargar las imágenes
    LaunchedEffect(Unit) {
        viewModel.getAllFavoriteDogs()
    }

    val favoriteDogs by viewModel.favoriteDogs.observeAsState(emptyList())
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleBar(name = "Random Dog Image", darkModeStore, darkMode) },
                navigationIcon = {
                    MainIconButton(icon = Icons.Filled.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp,30.dp, 0.dp, 0.dp)
        ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(favoriteDogs) { dog ->
                DogItem(dog,onDeleteClicked = { viewModel.deleteFavoriteDog(dog.url)})
            }
        }
    }
    }
}

@Composable
fun DogItem(dog: DogCacheEntity, onDeleteClicked: () -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberImagePainter(dog.url),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = dog.breed,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = onDeleteClicked) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
    }
}
