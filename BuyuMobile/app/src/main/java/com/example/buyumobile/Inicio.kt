package com.example.buyumobile

import android.content.Context
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import com.example.buyumobile.model.EnderecoObtido
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
    fun Header(shoppingProximo: MutableList<Shopping>, erroApi: MutableState<String>) {
        val (cep, setCep) = remember { mutableStateOf("") }
        val api = RetrofitService.getApiUsuarios()
        val endereco = remember { mutableStateOf(null) }
        val rua = remember { mutableStateOf("")}

        val context = LocalContext.current

        val sharedPreferences = context.getSharedPreferences("storage", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("idUsuario", "ne")


        if (shoppingProximo.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {

                OutlinedTextField(
                    value = cep,
                    onValueChange = setCep,
                    label = { Text("Digite seu CEP", style = MaterialTheme.typography.bodyMedium) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .background(Color(0xFFF3F3F3))
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.CenterStart)
                        .height(60.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF692FA3),
                        unfocusedBorderColor = Color(0xFFF3F3F3)
                    ),
                )
                Button(
                    onClick = {
                        val cepUsuario = Endereco(
                            id = null,
                            cep = cep,
                        )
                    //    val userId = userId
                        val put = api.putEndereco(userId!!, cep, cepUsuario)
                        put.enqueue(object : Callback<EnderecoObtido> {
                            override fun onResponse(
                                call: Call<EnderecoObtido>,
                                response: Response<EnderecoObtido>
                            ) {
                                if (response.isSuccessful) {
                                    val enderecoObtido = response.body()
                                    val apiShoppings = RetrofitService.getApiShoppings()
                                    val getShoppingProximos =
                                        apiShoppings.getShoppingsProximos(userId)
                                    if(enderecoObtido != null){
                                        val ruaObtida = enderecoObtido.endereco?.rua ?: ""

                                        rua.value = ruaObtida
                                    }

                                    getShoppingProximos.enqueue(object : Callback<List<Shopping>> {
                                        override fun onResponse(
                                            call: Call<List<Shopping>>,
                                            response: Response<List<Shopping>>
                                        ) {
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

                                        override fun onFailure(
                                            call: Call<List<Shopping>>,
                                            t: Throwable
                                        ) {
                                            erroApi.value = t.message!!
                                            Log.d("ERROREQUESTE", t.message.toString(), t)
                                        }
                                    })
                                } else {
                                    Log.d("CEP", "Erro na resposta: " + response.message())
                                }
                            }

                            override fun onFailure(call: Call<EnderecoObtido>, t: Throwable) {
                                Log.d("ERRO", t.message.toString(), t)
                            }
                        })
                    },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFF3F3F3))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.lupa), // Substitua com seu ID de recurso de imagem
                        contentDescription = "tESTE"
                    )
                }
            }
        }else{
            Text(text = "${rua.value}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 36.dp, top = 60.dp, bottom = 25.dp),
                color = Color.Black
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
                Button(onClick = {
                    val intent = Intent(context, Inicio::class.java)
                    context.startActivity(intent)
                }) {
                    Text("Início")
                }
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

                AsyncImage(
                    model = "http://192.168.53.27:8080/api/midias/imagens/${shoppingsProximos.imagens[0]?.nomeArquivoSalvo}",
                    contentDescription ="Logo da Empresa",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = shoppingsProximos.nome,
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
                items(shoppingsProximos.lojas) {
                    Column (
                        modifier = Modifier
                            .padding(start = 0.dp)
                            .width(100.dp)
                    ){
                        if (it.imagens != null && it.imagens.isNotEmpty()) {
                            AsyncImage(
                                model = "http://192.168.53.27:8080/api/midias/imagens/${it.imagens[0].nomeArquivoSalvo}",
                                contentDescription = "Logo da Empresa",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White)
                            )
                        }
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
                    model = "http://192.168.53.27:8080/api/midias/imagens/${shopping.imagens[0]?.nomeArquivoSalvo}",
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
                       if (it.imagens != null && it.imagens.isNotEmpty()) {
                            AsyncImage(
                                model = "http://3.88.3.254:8080/api/midias/imagens/${it.imagens[0].nomeArquivoSalvo}",
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

        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 56.dp)) { // Padding to avoid overlap with footer

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)) {
                    Header(shoppingProximo = shoppingProximo, erroApi = erroApi)
                }

                if (shoppingProximo.isNotEmpty()){
                    Text(
                        text = "Shoppings próximos a você",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 36.dp, top = 60.dp, bottom = 25.dp)
                            .fillMaxWidth(),
                        color = Color.Black
                    )

                    LazyColumn (Modifier.height(300.dp)){
                        items(items=shoppingProximo, itemContent = {
                            ShoppingsProximos(
                                shoppingsProximos = it
                            )
                        })
                    }
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

                LazyColumn (Modifier.height(300.dp)){
                    items(items=shopping, itemContent = {
                        ListaShoppings(
                            shopping = it
                        )
                    })
                }
            }

            Box(modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)) {
                MenuFooter()
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