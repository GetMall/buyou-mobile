package com.example.buyumobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyumobile.model.ListaShopping
import com.example.buyumobile.network.RetrofitService
import com.example.buyumobile.ui.theme.BuyuMobileTheme
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class Inicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyuMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaInicio("android")
                }
            }
        }
    }


    @Composable
    fun TelaInicio(name: String, modifier: Modifier = Modifier) {

        val errorApi = remember {
            mutableStateOf("")
        }

        val shoppings = remember {
            mutableStateOf<List<ListaShopping>>(emptyList())
        }

        val apiShoppings = RetrofitService.getApiShoppings()

        val get = apiShoppings.getShoppings()

        get.enqueue(object : Callback<List<ListaShopping>> {
            override fun onResponse(call: Call<List<ListaShopping>>, response: Response<List<ListaShopping>>) {
                if (response.isSuccessful) {
                    val shoppingList = response.body()
                    shoppingList?.let {
                        shoppings.value = it
                        println(shoppings.value)
                    }
                }
            }

            override fun onFailure(call: Call<List<ListaShopping>>, t: Throwable) {
                errorApi.value = t.message.toString()
            }
        })

        Column {
            Header()
            ShoppingsProximos()
            shoppings.value.forEach { shopping ->
                ListaShoppings(nomeShopping = shopping.nome, logoShopping = painterResource(R.drawable.eldorado_logo))
                ListaShoppings(nomeShopping = "Teste", logoShopping = painterResource(R.drawable.eldorado_logo))
            }
        }
    }

    @Composable
    fun Header() {

        val (endereco, setEndereco) = remember {
            mutableStateOf("")
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fundo_roxo),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logotipo_branco_buyu),
                        contentDescription = "Logo da Empresa",
                        modifier = Modifier.size(48.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.boneco_carrinho),
                        contentDescription = "Logo da Empresa",
                        modifier = Modifier.size(48.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                )
                {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(55.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                )
                    {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.width(16.dp))
                            TextField(value = endereco, onValueChange = setEndereco,
                                modifier = Modifier
                                    .height(70.dp),
                                placeholder = {Text("Digite o CEP")},
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White,
                                    unfocusedContainerColor = Color.White,
                                    focusedContainerColor = Color.White
                                ),
                            )

                            Spacer(modifier = Modifier.weight(1f))

                            Box(
                                modifier = Modifier
                                    .size(55.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.Black),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.lupa),
                                    contentDescription = "Logo da Empresa",
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }

    @Composable
    fun ShoppingsProximos() {
        Text(
            text = "Shoppings próximos a você",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 36.dp, top = 60.dp, bottom = 25.dp)
                .fillMaxWidth(),
            color = Color.Black
        )
    }

    @Composable
    fun ListaShoppings(nomeShopping: String, logoShopping: Painter) {
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {

                Spacer(modifier = Modifier.width(18.dp))

                Image(
                    painter = logoShopping,
                    contentDescription = "Logo do Shopping",
                    modifier = Modifier.size(35.dp)
                )

                Spacer(modifier = Modifier.width(0.dp))

                Image(
                    painter = logoShopping,
                    contentDescription = "Shopping Logo",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 8.dp, end = 16.dp)
                )

                Text(
                    text = nomeShopping,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                ListaLojas(
                    nomeShopping = "",
                    nomeLoja = "Riachuelo",
                    logoLoja = painterResource(id = R.drawable.logo_riachuelo)
                )
                ListaLojas(
                    nomeShopping = "",
                    nomeLoja = "  Renner",
                    logoLoja = painterResource(id = R.drawable.logo_renner)
                )
                ListaLojas(
                    nomeShopping = "",
                    nomeLoja = " Rihappy",
                    logoLoja = painterResource(id = R.drawable.logo_rihappy)
                )
                ListaLojas(
                    nomeShopping = "",
                    nomeLoja = "    C&A",
                    logoLoja = painterResource(id = R.drawable.logo_cea)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    @Composable
    fun ListaLojas(nomeShopping: String, nomeLoja: String, logoLoja: Painter) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Image(
                    painter = logoLoja,
                    contentDescription = "Logo da Loja",
                    modifier = Modifier
                        .size(45.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Transparent)
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = nomeLoja,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        BuyuMobileTheme {
            TelaInicio("Android")
        }
    }
}