package com.example.buyumobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buyumobile.model.Pedidos
import com.example.buyumobile.model.Shopping
import com.example.buyumobile.network.RetrofitService
import com.example.buyumobile.ui.theme.BuyuMobileTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaUltimosPedidos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyuMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaUltimosPedidos("Android")
                }
            }
        }
    }
}

@Composable
fun TelaUltimosPedidos(name: String, modifier: Modifier = Modifier) {
    val pedidos = remember { mutableStateListOf<Pedidos>() }
    val api = RetrofitService.getApiPedidos()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("storage", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getString("idUsuario", "ne")

    val get = api.getPedidos(userId!!)
    val erroApi = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true){
        get.enqueue(object : Callback<List<Pedidos>> {
            override fun onResponse(call: Call<List<Pedidos>>, response: Response<List<Pedidos>>){
                if(response.isSuccessful){
                    val lista = response.body()
                    Log.d("ERRODEUCERTO", lista.toString())
                    if (lista != null){
                        pedidos.clear()
                        pedidos.addAll(lista)
                        Log.d("teste" , pedidos.toString())
                    }
                }
            }
            override fun onFailure(call: Call<List<Pedidos>>, t: Throwable) {
                erroApi.value = t.message!!
                Log.d("ERROREQUESTE", t.message.toString(), t)
            }
        })
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(46.dp))

        if(pedidos.isNotEmpty()){
            Text(
                text = "Últimos pedidos",
                color = Color(0xFF692FA3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(46.dp))
            LazyColumn(modifier.height(300.dp)) {
                items(items=pedidos, itemContent = {
                    CardPedido(
                        pedidos = it
                    )
                })
            }
        }else{
            Text(
                text = "Nenhum pedido encontrado",
                color = Color(0xFF692FA3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                val intent = Intent(context, Inicio::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .padding(bottom = 50.dp)
                .width(320.dp)
                .height(50.dp)
                .background(color = Color(0xFF692FA3), shape = RoundedCornerShape(15.dp))
        ) {
            Text(text = "Voltar", color = Color.White)
        }
    }
}

@Composable
fun CardPedido(pedidos: Pedidos) {
    Surface(
        modifier = Modifier.fillMaxWidth(0,9f),
        color = Color.White,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = pedidos.status ?: "N/A",
                fontSize = 11.sp,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = pedidos.valorTotal?.toString() ?: "N/A",
                fontSize = 11.sp,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = pedidos.dataPedido?.toString() ?: "N/A",
                fontSize = 9.sp,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = pedidos.formaPagamento ?: "N/A",
                fontSize = 11.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    BuyuMobileTheme {
        TelaUltimosPedidos("Android")
    }
}