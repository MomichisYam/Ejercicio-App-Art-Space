package com.jeshuayam.galeriagatos

import android.R.attr.enabled
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeshuayam.galeriagatos.ui.theme.GaleriaGatosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GaleriaGatosTheme {
                CatGallery()
            }
        }
    }
}

data class Cat(
    val image1: Int,
    val image2: Int,
    val image3: Int,
    val image4: Int,
    val name: String,
    val description: String
)

fun createArrayOfObjectsCats(): Array<Cat> {
    return arrayOf(
        Cat(
            R.drawable.yeyo1,
            R.drawable.yeyo2,
            R.drawable.yeyo3,
            R.drawable.yeyo4,
            "Yeyo",
            "El gato más viejo y gordo de la casa"
        ),
        Cat(
            R.drawable.nena1,
            R.drawable.nena2,
            R.drawable.nena3,
            R.drawable.nena4,
            "Nena",
            "La abuelita de las nietas"
        ),
        Cat(
            R.drawable.galleta1,
            R.drawable.galleta2,
            R.drawable.galleta3,
            R.drawable.galleta4,
            "Galleta",
            "Comelona y juguetona"
        ),
        Cat(
            R.drawable.mariposa1,
            R.drawable.mariposa2,
            R.drawable.mariposa3,
            R.drawable.mariposa4,
            "Mariposa",
            "Puro cariñito hecho gato"
        )
    )
}

@Composable
fun CatGallery() {
    val cats = remember { createArrayOfObjectsCats() }
    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        PhotoFrame(
            cat = cats[currentIndex]
        )

        Spacer(modifier = Modifier.height(30.dp))

        NavigationButtons(
            currentIndex = currentIndex,
            totalCats = cats.size,
            onPrevious = {
                if (currentIndex > 0) {
                    currentIndex--
                }

            },
            onNext = {
                if (currentIndex < cats.size - 1) {
                    currentIndex++
                }
            }
        )
    }
}

@Composable
fun PhotoFrame(cat: Cat) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.size(400.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                PhotoCat(cat.image1, Modifier.weight(1f))
                PhotoCat(cat.image2, Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                PhotoCat(cat.image3, Modifier.weight(1f))
                PhotoCat(cat.image4, Modifier.weight(1f))
            }
        }

        TitleSubtitle(
            title = cat.name,
            subtitle = cat.description
        )
    }
}

@Composable
fun PhotoCat(id: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id),
        contentDescription = "Foto de gato",
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(35.dp))
            .padding(5.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TitleSubtitle(title: String, subtitle: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
fun NavigationButtons(
    currentIndex: Int,
    totalCats: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = onPrevious,
            enabled = currentIndex > 0
        ) {
            Text(text = "Anterior")
        }
        Button(
            onClick = onNext,
            enabled = currentIndex <  totalCats - 1
        ) {
            Text(text = "Siguiente")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GaleriaGatosTheme {
        CatGallery()
    }
}