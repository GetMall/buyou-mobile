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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.buyumobile.model.Loja
import com.example.buyumobile.model.Produtos
import com.example.buyumobile.network.RetrofitService
import com.example.buyumobile.ui.theme.BuyuMobileTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaProdutos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyuMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaProdutos("Android")
                }
            }
        }
    }
}

@Composable
fun TelaProdutos(name: String, modifier: Modifier = Modifier) {

    val lojaIdMock = remember { mutableStateOf("9dbdc138-98d2-4643-8e7d-7ea2a771bcf5") }

    val erroApi = remember {
        mutableStateOf("")
    }

    val apiLojas = RetrofitService.getApiLojas()
    // TODO verificar depois
    val listaProdutos = remember { mutableStateListOf<Produtos>() }
    val listaProdutosVazio = remember { mutableStateListOf<Produtos>() }
    val loja = remember { mutableStateOf<Loja?>(null) }
    val get = apiLojas.getLoja(lojaIdMock.value)

    LaunchedEffect(key1 = true){
        get.enqueue(object : Callback<Loja> {
            override fun onResponse(call: Call<Loja>, response: Response<Loja>){
                if(response.isSuccessful){
                    // TODO
                    loja.value = response.body()
                    Log.d("ERRODEUCERTO", loja.toString())
                    if (loja != null){
                        // TODO tirar isso dai depois
                        listaProdutos.clear()
                        listaProdutos.addAll(loja.value?.produtos ?: listaProdutosVazio)

                        Log.d("teste" , loja.toString())
                    }
                }
            }
            override fun onFailure(call: Call<Loja>, t: Throwable) {
                erroApi.value = t.message!!
                Log.d("ERROREQUESTE", t.message.toString(), t)
            }
        })
    }

    HeaderProdutos()
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

        LazyColumn {
            items(listaProdutos) { produto ->
                ProductBlock(
                    productName = produto.nome,
                    productDescription = produto.descricao,
                    productPrice = produto.valorUnitario.toString(),
                    imageResource = produto.imagens.get(0).nomeArquivoSalvo
                )
            }
        }
    }
}

@Composable
fun HeaderProdutos() {
    val (endereco, setEndereco) = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundo_roxo),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Text(text = "R. Hadock Lobo",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 0.dp)
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
fun ProductBlock(productName: String, productDescription: String, productPrice: String, imageResource: String) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .clickable {
                val intent = Intent(context, TelaPagamento::class.java)
                context.startActivity(intent)
            }
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
        // TODO arrumar imagem
        AsyncImage(
            model = "http://10.18.32.103:8080/api/midias/imagens/${imageResource}",
            contentDescription = "Logo do Produto",
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .clickable(
                    onClick = {
//                        val intent = Intent(context, TelaProdutos::class.java)
//                        context.startActivity(intent)
                    }
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    BuyuMobileTheme {
        TelaProdutos("Android")
    }
}