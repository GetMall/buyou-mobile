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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.buyumobile.model.Imagem
import com.example.buyumobile.model.Loja
import com.example.buyumobile.model.MyGlobals
import com.example.buyumobile.model.ProdutoManager
import com.example.buyumobile.model.Produtos
import com.example.buyumobile.network.RetrofitService
import com.example.buyumobile.ui.theme.BuyuMobileTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

class TelaDescProduto : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extras = intent.extras

        setContent {
            BuyuMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaDescProduto(extras)
                }
            }
        }
    }
}

@Composable
fun TelaDescProduto(extras: Bundle?, modifier: Modifier = Modifier) {
    // TODO PEGAR O ID DA LOJA E ENVIAR PRA TELA ANTERIOR
    val idLoja = extras?.getString("idLoja")

    val Purple = Color(0xFF692FA3)

    val contexto = LocalContext.current

    var produtoId = extras?.getString("idProduto")

    var precoProduto = extras?.getDouble("precoProduto", 0.0)
    var descricaoProduto = extras?.getString("descricaoProduto")
    var nomeProduto = extras?.getString("nomeProduto")
    var imagemProduto = extras?.getString("imagemProduto")

    if (produtoId == null) {
        produtoId = ""
    }
    if (descricaoProduto == null) {
        descricaoProduto = ""
    }

    if (precoProduto == null) {
        precoProduto = 0.0
    }

    if (nomeProduto == null) {
        nomeProduto = ""
    }

    if(imagemProduto == null) {
        imagemProduto = ""
    }



    // TODO APAGAR
//    if (produtoId == null) {
//        produtoId = ""
//    }
//
//    val erroApi = remember {
//        mutableStateOf("")
//    }
//
//    val apiProdutos = RetrofitService.getApiProdutos()
//    val produto = remember { mutableStateOf<Produtos?>(null) }
//    val get = apiProdutos.getProduto(produtoId)

    // TODO APAGAR DPS
//    LaunchedEffect(key1 = true){
//        get.enqueue(object : Callback<Produtos> {
//            override fun onResponse(call: Call<Produtos>, response: Response<Produtos>){
//                if(response.isSuccessful){
//                    // TODO
//                    produto.value = response.body()
//                    Log.d("ERRODEUCERTO", produto.toString())
//                    if (produto != null){
//                        // TODO tirar isso dai depois
////                        listaProdutos.clear()
////                        listaProdutos.addAll(loja.value?.produtos ?: listaProdutosVazio)
//
//                        Log.d("teste" , produto.toString())
//                    }
//                }
//            }
//            override fun onFailure(call: Call<Produtos>, t: Throwable) {
//                erroApi.value = t.message!!
//                Log.d("ERROREQUESTE", t.message.toString(), t)
//            }
//        })
//    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Menu horizontal
        HeaderDescProduto()

        Spacer(modifier = Modifier.height(16.dp))

        // Imagem do produto

        AsyncImage(
            model = "http://${MyGlobals.ipFixo}:8080/api/midias/imagens/${imagemProduto}",
            contentDescription = "Logo do Produto",
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
            text = nomeProduto ?: "" ,
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
            text = "R$ ${precoProduto ?: ""}",
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
            text = descricaoProduto ?: "",
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
                onClick = {
                    // TODO voltando para loja de produtos
                    val intent = Intent(contexto, TelaProdutos::class.java)

                    intent.putExtra("idLoja", idLoja)
                    contexto.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Purple)
            ) {
                Text(text = "Voltar", color = Color.White)
            }
        }

        Button(
            // TODO adicionar numa lista e ficar lançando essa lista pra todas as telas
            onClick = {
                val listaImagens: List<Imagem> = listOf(Imagem(UUID.randomUUID(), imagemProduto, imagemProduto))

                ProdutoManager.produtos.add(Produtos(UUID.fromString(produtoId), nomeProduto,
                    precoProduto, descricaoProduto,  listaImagens))

                val telaInico = Intent(contexto, Inicio::class.java)
                contexto.startActivity(telaInico)
            },
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
fun HeaderDescProduto() {
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

        Text(text = "R. Haddock Lobo, 595",
            fontSize = 16.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 0.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    BuyuMobileTheme {
        TelaDescProduto(extras = null)
    }
}