package com.example.buyumobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.buyumobile.model.Endereco
import com.example.buyumobile.model.Shopping
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Header() {
        val (cep, setCep) = remember { mutableStateOf("") }
        val api = RetrofitService.getApiUsuarios()
        val endereco = remember { mutableStateOf(null) }
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
                    value = cep,
                    onValueChange = setCep,
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
                Button(onClick = {
                    val cepUsuario = Endereco(
                        id = null,
                        cep = cep,
                    )
                    val userId = "059558e0-ed45-461a-8867-07cd6c80085d"
                    val put = api.putEndereco(userId, cep, cepUsuario)
                    put.enqueue(object : Callback<Endereco> {
                        override fun onResponse(call: Call<Endereco>, response: Response<Endereco>) {
                            if (response.isSuccessful) {
                                val endereco = response.body()
                                Log.d("CEP", endereco.toString())
                            } else {
                                Log.d("CEP", "Erro na resposta: " + response.message())
                            }
                        }
                        override fun onFailure(call: Call<Endereco>, t: Throwable) {
                            Log.d("ERRO", t.message.toString(), t)
                        }
                    })
                },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .height(60.dp)
                ) {
                    Text("Buscar", style = MaterialTheme.typography.bodyMedium)
                }
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
                Button(onClick = {
                    val intent = Intent(context, TelaUltimosPedidos::class.java)
                    context.startActivity(intent)
                }) {
                    Text("Pedidos")
                }
            }
        }
    }

    @Composable
    fun ShoppingsProximos(shoppingsProximos: Shopping) {
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
    fun ListaShoppings(shopping: Shopping ) {
        val context = LocalContext.current

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
            ) {

                Spacer(modifier = Modifier.width(18.dp))

                AsyncImage(
                    model = "http://100.27.232.35:8080/api/midias/imagens/${shopping.imagens[0]?.nomeArquivoSalvo}",
                    contentDescription ="Logo da Empresa",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .clickable(
                            onClick = {
                                val intent = Intent(context, TelaProdutos::class.java)
                                context.startActivity(intent)
                            }
                        )
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = shopping.nome,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            LazyRow (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth()
            ){
                items(shopping.lojas) {
                    Column (
                        modifier = Modifier
                            .padding(start = 0.dp)
                            .width(100.dp)
                    ){
//                        Image(
//                            painter = painterResource(id = R.drawable.logo_renner),
//                            contentDescription = "Logo da Empresa",
//                            modifier = Modifier
//                                .size(50.dp)
//                                .clip(RoundedCornerShape(10.dp))
//                                .background(Color.White)
//                                .clickable(
//                                    onClick = {
//                                        val intent = Intent(context, TelaProdutos::class.java)
//                                        context.startActivity(intent)
//                                    }
//                                )
//                        )

                       if (it.imagens != null && it.imagens.isNotEmpty()) {
                            AsyncImage(
                                model = "http://100.27.232.35:8080/api/midias/imagens/${it.imagens[0].nomeArquivoSalvo}",
                                contentDescription = "Logo da Empresa",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White)
                                    .clickable(
                                        onClick = {
                                            val intent = Intent(context, TelaProdutos::class.java)
                                            context.startActivity(intent)
                                        }
                                    )
                            )
                        }
//                        Image(
//                            painter = painterResource(id = R.drawable.logo_renner),
//                            contentDescription = "Logo da Empresa",
//                            modifier = Modifier
//                                .size(50.dp)
//                                .clip(RoundedCornerShape(10.dp))
//                                .background(Color.White)
//                                .clickable(
//                                    onClick = {
//                                        val intent = Intent(context, TelaProdutos::class.java)
//                                        context.startActivity(intent)
//                                    }
//                                )
//                        )
                        Text(
                            text = it.nome,
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
    fun TelaInicio(name: String, modifier: Modifier = Modifier) {
        val context = LocalContext.current
        val shopping = remember { mutableStateListOf<Shopping>() }
        val shoppingProximo = remember { mutableStateListOf<Shopping>() }

        val erroApi = remember {
            mutableStateOf("")
        }

        val apiShoppings = RetrofitService.getApiShoppings()

        val get = apiShoppings.getShoppings()
        val getShoppingProximos = apiShoppings.getShoppingsProximos("059558e0-ed45-461a-8867-07cd6c80085d")
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

            LaunchedEffect(key1 = true) {
                getShoppingProximos.enqueue(object : Callback<List<Shopping>> {
                    override fun onResponse(call: Call<List<Shopping>>, response: Response<List<Shopping>>) {
                        if (response.isSuccessful) {
                            val lista = response.body()
                            Log.d("ERRODEUCERTO", lista.toString())
                            if (lista != null) {
                                shoppingProximo.clear()
                                shoppingProximo.addAll(lista)
                                Log.d("teste", shoppingProximo.toString())
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Shopping>>, t: Throwable) {
                        erroApi.value = t.message!!
                        Log.d("ERROREQUESTE", t.message.toString(), t)
                    }
                })
            }

            LazyColumn (Modifier.height(300.dp)){
                items(items=shoppingProximo, itemContent = {
                    ShoppingsProximos(
                        shoppingsProximos = it
                    )
                })
            }


            Text(
                text = "Shoppings populares",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 36.dp, top = 60.dp, bottom = 25.dp)
                    .fillMaxWidth(),
                color = Color.Black
            )

            LaunchedEffect(key1 = true){
                get.enqueue(object : Callback<List<Shopping>>{
                    override fun onResponse(call: Call<List<Shopping>>, response: Response<List<Shopping>>){
                        if(response.isSuccessful){
                            val lista = response.body()
                            Log.d("ERRODEUCERTO", lista.toString())
                            if (lista != null){
                                shopping.clear()
                                shopping.addAll(lista)
                                Log.d("teste" , shopping.toString())
                            }
                        }
                    }
                    override fun onFailure(call: Call<List<Shopping>>, t: Throwable) {
                        erroApi.value = t.message!!
                        Log.d("ERROREQUESTE", t.message.toString(), t)
                    }
                })
            }

            LazyColumn (Modifier
                .height(300.dp)){
                items(items=shopping, itemContent = {
                    ListaShoppings(
                        shopping = it
                    )
                })
            }


            //      ListaShoppings(listaShopping = , logoShopping = painterResource(id = R.drawable.cidade_sp_log))
            //    ListaShoppings(nomeShopping = "Shopping Pátio Paulista", logoShopping = painterResource(id = R.drawable.patio_paulista_logo))
            //  ListaShoppings(nomeShopping = "Shopping Eldorado", logoShopping = painterResource(id = R.drawable.eldorado_logo))
            MenuFooter()
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