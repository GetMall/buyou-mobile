package com.example.buyumobile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyumobile.ui.theme.BuyuMobileTheme


@Composable
fun TelaProduto(name: String, modifier: Modifier = Modifier) {
    //val Beige = Color(0xFFD7BFA6)
    val Purple = Color(0xFF692FA3)

    Column(
        modifier = Modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Menu horizontal
        MainMenu(
            address = "Rua Principal, 123 - Cidade",
            profileIcon = Icons.Default.Person,
            cartIcon = Icons.Default.ShoppingCart,
            )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.camisetapolo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp) // Tamanho da imagem
                .padding(16.dp) // Espaçamento ao redor da imagem
        )

        Spacer(modifier = Modifier.height(16.dp))

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

        Spacer(modifier = Modifier.height(32.dp))

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

fun MainMenu(
    address: String,
    profileIcon: ImageVector,
    cartIcon: ImageVector,
) {

    val Purple = Color(0xFF692FA3)
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(color = Purple)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagem do canto esquerdo
            Image(
                painter = painterResource(id = R.drawable.logotipo_branco_buyu),
                contentDescription = null,
                modifier = Modifier

                    .size(50.dp),

                )

            // Endereço no meio
            Text(
                text = address,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                fontSize = 10.sp
            )

            // Ícones do canto direito
            Icon(
                imageVector = cartIcon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(end = 16.dp)
            )
            Icon(
                imageVector = profileIcon,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    BuyuMobileTheme {
        TelaProduto("Android")
    }
}

//comentario Teste
