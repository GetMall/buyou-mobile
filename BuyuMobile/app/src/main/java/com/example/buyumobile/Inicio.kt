package com.example.buyumobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyumobile.model.ListaShopping
import com.example.buyumobile.network.ApiShoppings
import com.example.buyumobile.network.RetrofitService
import com.example.buyumobile.ui.theme.BuyuMobileTheme
import retrofit2.Call
import retrofit2.Callback
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
        val context = LocalContext.current
        Column (modifier= Modifier.verticalScroll(rememberScrollState())) {
            Header()
            Text(
                text = "Shoppings próximos a você",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 36.dp, top = 60.dp, bottom = 25.dp)
                    .fillMaxWidth(),
                color = Color.Black
            )
            ShoppingsProximos(
                nomeShoppingProximo = "Shopping", logoShoppingProximo = painterResource(
                    id = R.drawable.eldorado_logo
                )
            )
            Text(
                text = "Shoppings populares",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 36.dp, top = 60.dp, bottom = 25.dp)
                    .fillMaxWidth(),
                color = Color.Black
            )

            Button(onClick = {
                val intent = Intent(context, TelaProdutos::class.java)
                context.startActivity(intent)
            }) {
                Text("Ver todas as lojas")
            }

            val shopping = remember { mutableStateListOf<ListaShopping>() }

            val erroApi = remember {
                mutableStateOf("")
            }

            val apiShoppings = RetrofitService.getApiShoppings()

            val get = apiShoppings.getShoppings()

            get.enqueue(object : Callback<List<ListaShopping>>{
                override fun onResponse( call: Call<List<ListaShopping>>, response: Response<List<ListaShopping>>){
                    if(response.isSuccessful){
                        val lista = response.body()
                        Log.d("deu certo???", "Outra coisa")
                        if (lista != null){
                          shopping.clear()
                            shopping.addAll(lista)
                        }
                    }
                }
                override fun onFailure(call: Call<List<ListaShopping>>, t: Throwable) {
                    erroApi.value = t.message!!
                }
            })

            shopping.forEach {
                ListaShoppings(
                    nomeShopping = it.nome,
                    logoShopping = painterResource(id = R.drawable.eldorado_logo)
                )
            }

           // ListaShoppings(nomeShopping = "Shopping Cidade de São Paulo", logoShopping = painterResource(id = R.drawable.cidade_sp_log))
          //  ListaShoppings(nomeShopping = "Shopping Pátio Paulista", logoShopping = painterResource(id = R.drawable.patio_paulista_logo))
          //  ListaShoppings(nomeShopping = "Shopping Eldorado", logoShopping = painterResource(id = R.drawable.eldorado_logo))
            MenuFooter()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Header() {
        val (endereco, setEndereco) = remember {
            mutableStateOf("")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fundo_roxo),
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            OutlinedTextField(
                value = endereco,
                onValueChange = setEndereco,
                label = { Text("Digite seu CEP", style = MaterialTheme.typography.bodyMedium) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.Transparent, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .align(Alignment.CenterStart)
                    .height(60.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Yellow,
                    unfocusedBorderColor =  Color.Yellow,
                    focusedLabelColor =  Color.Yellow,
                    unfocusedLabelColor =  Color.Yellow,
                    focusedTextColor = Color.White,
            ),
            )
        }
    }
    @Composable
    fun MenuFooter() {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Início",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Button(onClick = {
                    val intent = Intent(context, TelaUltimosPedidos::class.java)
                    context.startActivity(intent)
                }) {
                    Text("Perfil")
                }
            }
        }
    }

    @Composable
    fun ShoppingsProximos(nomeShoppingProximo: String, logoShoppingProximo: Painter) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {

                Spacer(modifier = Modifier.width(18.dp))

                Image(
                    painter = logoShoppingProximo,
                    contentDescription = "Logo do Shopping",
                    modifier = Modifier
                        .size(35.dp),
                )

                Spacer(modifier = Modifier.width(0.dp))

                Image(
                    painter = logoShoppingProximo,
                    contentDescription = "Shopping Logo",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(start = 8.dp, end = 16.dp)
                )

                Text(
                    text = nomeShoppingProximo,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyRow (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 20.dp)
            ){
                items(2) {
                    Column (
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .width(100.dp)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_renner),
                            contentDescription = "Logo da Empresa",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                        )
                        Text(
                            text = "Renner",
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
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

                Text(
                    text = nomeShopping,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyRow (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 20.dp)
            ){
                items(5) {
                    Column (
                        modifier = Modifier
                            .padding(start = 0.dp)
                            .width(100.dp)
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_renner),
                            contentDescription = "Logo da Empresa",
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .clickable {

                                },
                        )
                        Text(
                            text = "Renner",
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    @Composable
    fun ListaLojas(nomeShopping: String, nomeLoja: String, logoLoja: Painter) {
        val context = LocalContext.current
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
                        .clickable(
                            onClick = {
                                val intent = Intent(context, TelaProdutos::class.java)
                                context.startActivity(intent)
                            }
                        )
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