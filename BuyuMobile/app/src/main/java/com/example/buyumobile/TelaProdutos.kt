package com.example.buyumobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyumobile.ui.theme.BuyuMobileTheme

class TelaProdutos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyuMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaProdutos("Android")
                }
            }
        }
    }
}

@Composable
fun TelaProdutos(name: String, modifier: Modifier = Modifier) {
    HeaderProdutos()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Título em negrito",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Outra linha de texto sem negrito",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Mais uma linha de texto sem negrito",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MenuItem(text = "Vestuário")
            MenuItem(text = "Calçados")
            MenuItem(text = "Acessórios")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ComboBox()
            SearchField()
        }
        Text(
            text = "Título Abaixo",
            modifier = Modifier.padding(top = 16.dp)
        )
        ProductBlock(
            productName = "Produto XYZ",
            productDescription = "Descrição do Produto XYZ",
            productPrice = "100,00",
            imageResource = R.drawable.camisetapolo
        )
        ProductBlock(
            productName = "Produto XYZ",
            productDescription = "Descrição do Produto XYZ",
            productPrice = "100,00",
            imageResource = R.drawable.camisetapolo
        )
        ProductBlock(
            productName = "Produto XYZ",
            productDescription = "Descrição do Produto XYZ",
            productPrice = "100,00",
            imageResource = R.drawable.camisetapolo
        )
    }
}

@Composable
fun HeaderProdutos() {
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

        Text(text = "R. Hadock Lobo",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 0.dp)
        )
    }
}

@Composable
fun MenuItem(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
fun ComboBox() {
    // Implemente a ComboBox aqui
    Text("ComboBox")
}

@Composable
fun SearchField() {
    // Implemente o campo de busca aqui
    Text("Campo de busca")
}

@Composable
fun ProductBlock(productName: String, productDescription: String, productPrice: String, imageResource: Int) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clickable {
                val intent = Intent(context, TelaPagamento::class.java)
                context.startActivity(intent)
            }
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Bloco de informações do produto
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = productName,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = productDescription,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "R$ $productPrice",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Imagem do produto
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Imagem do produto",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    BuyuMobileTheme {
        TelaProdutos("Android")
    }
}