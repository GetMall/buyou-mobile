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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

class TelaPagamentoPedido : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyuMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaPagamentoPedidoContent("Android")
                }
            }
        }
    }
}

@Composable
fun TelaPagamentoPedidoContent(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Headerrrr()

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

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.qr_code),
            contentDescription = null,
            modifier = Modifier.size(210.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
        ) {
            Text(
                text = "O código Pix gerado tem validade de 10 minutos e a aprovação do pagamento é feita na hora.",
                modifier = Modifier.padding(8.dp),
                fontSize = 12.sp
            )
        }


        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            shape = RoundedCornerShape(6.dp),
        ) {
            Text(
                text = "Copiar código Pix",
                color = Color.White
            )
        }
    }
}

@Composable
fun Headerrrr() {
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
fun GreetingPreview3() {
    BuyuMobileTheme {
        TelaPagamentoPedidoContent("Android")
    }
}
