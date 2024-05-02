package com.example.buyumobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.buyumobile.ui.theme.BuyuMobileTheme

class TelaDescProduto : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyuMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaDescProduto("Android")
                }
            }
        }
    }
}

@Composable
fun TelaDescProduto(name: String, modifier: Modifier = Modifier) {
    val Purple = Color(0xFF692FA3)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Menu horizontal
        HeaderDescProduto()

        Spacer(modifier = Modifier.height(16.dp))

        // Imagem do produto
        Image(
            painter = painterResource(id = R.drawable.camisetapolo),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp) // Tamanho da imagem
                .padding(16.dp) // Espaçamento ao redor da imagem
        )

        // Nome do produto

        // Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "",
            fontSize = 20.sp,
            color = Purple,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(1.dp))

        // Preço do produto
        Text(
            text = "",
            fontSize = 20.sp,
            color = Purple,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "price",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Descrição do produto
        Text(
            text = "",
            fontSize = 20.sp,
            color = Purple,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "description",
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Row com os botões
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Botão Favoritar
            Button(
                onClick = { },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Purple)
            ) {
                Text(text = "Favoritar", color = Color.White)
            }
        }

        Button(
            onClick = { /* Ação do botão Adicionar ao Carrinho */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(Purple),

            ) {
            Text(text = "Comprar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun HeaderDescProduto() {
    val (endereco, setEndereco) = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundo_roxo),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(text = "R. Haddock Lobo, 595",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 0.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    BuyuMobileTheme {
        TelaDescProduto("Android")
    }
}