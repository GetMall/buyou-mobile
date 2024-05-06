package com.example.buyumobile

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyumobile.ui.theme.BuyuMobileTheme

class TelaResumoDoPedido : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyuMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaResumoDoPedidoContent("Android")
                }
            }
        }
    }
}

@Composable
fun TelaResumoDoPedidoContent(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderTelaResumoDoPedido()

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.logotipo_roxo),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Seu pedido foi cadastrado!",
            color = Color(0xFF692FA3),
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(35.dp))

        Row {
            Text(
                text = "Pedido: "
            )

            Text(
                text = "#1049",
                color = Color(0xFF692FA3),
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Valor da compra: R$ 20,00"
        )

        Spacer(modifier = Modifier.height(90.dp))

        // Adicionando o resumo do pedido
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Resumo do pedido",
                color = Color(0xFF692FA3),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(start = 5.dp, bottom = 8.dp)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(46.dp))


            ResumoItem("Subtotal", "R$20,00")
            ResumoItem("Cupom", "R$0,00")
            ResumoItem("Entrega", "R$0,00")
            ResumoItem("Total", "R$20,00")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Cartão de crédito **** 2014",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(6.dp),
        ) {
            Text(
                text = "Ir para meus pedidos",
                color = Color.White
            )
        }
    }
}

@Composable
fun ResumoItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value
        )
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.LightGray)
    )

    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun HeaderTelaResumoDoPedido() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color(0xFF692FA3))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_left),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Pedido",
            color = Color.White
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.logotipo_branco_buyu),
            contentDescription = null,
            modifier = Modifier.size(65.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    BuyuMobileTheme {
        TelaResumoDoPedidoContent("Android")
    }
}