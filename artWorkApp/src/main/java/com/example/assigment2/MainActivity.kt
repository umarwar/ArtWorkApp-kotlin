package com.example.assigment2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assigment2.ui.theme.Assigment2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assigment2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtworkApp()
                }
            }
        }
    }
}

@Composable
fun ArtworkApp() {
    // Define a list of artworks
    val artworkList = listOf(
        Artwork(
            "Still life of Blue Rose and Other Flowers",
            "Owen Scott",
            2021, R.drawable.art
        ),
        Artwork(
            "Mona Lisa",
            "Leonardo da Vinci",
            1503, R.drawable.art1
        ),
        Artwork(
            "The Persistence of Memory",
            "Umar Waris",
            2023, R.drawable.art2
        )
    )
    // Keep track of the current artwork index
    val artworkIndex = remember { mutableStateOf(0) }
    // Get the current artwork based on the index
    val artworkState = artworkList[artworkIndex.value]

    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Column(
            modifier = Modifier
                .weight(3f)
                .fillMaxSize()
                .padding(top = 60.dp, start = 25.dp, end = 25.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Surface(
                color = Color.White,
                border = BorderStroke(1.dp, Color.LightGray),
                shadowElevation = 15.dp
            ) {
                // Display the image of the current artwork
                Image(
                    painter = painterResource(id = artworkState.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(30.dp)
                )
            }

        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(top = 60.dp, start = 25.dp, end = 25.dp, bottom = 20.dp)
                .background(Color(223, 225, 225, 255))
        )
        {
            Row(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxSize()
            )
            {
                // Display the title of the current artwork
                Text(
                    text = artworkState.title,
                    modifier = Modifier.padding(start = 10.dp, end = 25.dp),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Light
                )

            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
            {
                // Display the artist name and year of the current artwork
                Text(
                    text = artworkState.artist,
                    modifier = Modifier.padding(start = 10.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "(${artworkState.year})",
                    modifier = Modifier.padding(start = 5.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light
                )

            }
        }
        Column(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxSize()
        )
        {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                // Button to navigate to the previous artwork
                Button(
                    onClick = {
                        if (artworkIndex.value > 0) {
                            artworkIndex.value--
                        } else {
                            artworkIndex.value = artworkList.size - 1
                        }
                    },
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .width(120.dp)
                )
                {
                    Text(text = "Previous")

                }
                // Button to navigate to the next artwork
                Button(
                    onClick = {
                        if (artworkIndex.value < artworkList.size - 1) {
                            artworkIndex.value++
                        } else {
                            artworkIndex.value = 0
                        }
                    },
                    modifier = Modifier
                        .padding(end = 30.dp)
                        .width(120.dp)
                )
                {
                    Text(text = "Next")

                }

            }

        }

    }

}

data class Artwork(
    val title: String,
    val artist: String,
    val year: Int,
    val image: Int
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assigment2Theme {
        ArtworkApp()
    }
}