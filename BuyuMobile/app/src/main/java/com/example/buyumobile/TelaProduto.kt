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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyumobile.ui.theme.BuyuMobileTheme


@Composable
fun TelaProduto(name: String, description: String, price: String, modifier: Modifier = Modifier) {

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
            text = price,
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
            text = description,
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
        TelaProduto(
            name = "Camisa Polo Clássica Masculina",
            description = "Esta camisa polo clássica é feita com tecido " +
                    "de alta qualidade e oferece conforto durante todo o dia.",
            price = "$39.99"
        )
    }
}

