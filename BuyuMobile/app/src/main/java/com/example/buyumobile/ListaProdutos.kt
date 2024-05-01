package com.example.buyumobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buyumobile.model.CadastroUsuario
import com.example.buyumobile.network.RetrofitService
import com.example.buyumobile.ui.theme.BuyuMobileTheme
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response



@Composable
fun ListaProdutos(name: String, modifier: Modifier = Modifier) {
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
    Row(
        modifier = Modifier
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
fun GreetingPreview3() {
    BuyuMobileTheme {
        ListaProdutos("Android")
    }
}



