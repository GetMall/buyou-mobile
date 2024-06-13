package com.example.buyumobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.buyumobile.model.Imagem
import com.example.buyumobile.model.LoginUsuario
import com.example.buyumobile.model.MyGlobals
import com.example.buyumobile.model.Pagamento
import com.example.buyumobile.model.PagamentoResultado
import com.example.buyumobile.model.ProdutoManager
import com.example.buyumobile.model.Produtos
import com.example.buyumobile.model.Usuario
import com.example.buyumobile.network.RetrofitService
import com.example.buyumobile.ui.theme.BuyuMobileTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

class TelaPagamento : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyuMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TelaPagamento("Android")
                }
            }
        }
    }
}

@Composable
fun TelaPagamento(name: String, modifier: Modifier = Modifier) {

    // TODO -----------------------------------------------------------------------------------------------------------
    // Mock data
//    val imagem = Imagem(
//        id = UUID.fromString("e985da64-1380-4982-8a6a-53bafe77dc28"),
//        nomeArquivoOriginal = "06vestidoFestaRoxo.jpg",
//        nomeArquivoSalvo = "06vestidoFestaRoxo.jpg"
//    )
//    val produto = Produtos(
//        id = UUID.fromString("d74aa21d-f0fd-4cec-9e35-cf1006832a1c"),
//        nome = "Vestido de Festa",
//        valorUnitario = 99.99,
//        descricao = "Roupa elegante",
//        imagens = listOf(imagem)
//    )

//    ProdutoManager.produtos.add(produto)
    // --------------------------------------------------------------------------------


    val listaProdutos = ProdutoManager.produtos

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Resumo()
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(listaProdutos) { produto ->
            ProdutoCarrinho(
                productName = produto.nome,
                productDescription = produto.descricao,
                productPrice = produto.valorUnitario.toString(),
                imageResource = produto.imagens[0].nomeArquivoSalvo,
            )
        }

        item {
            Text(
                text = "Forma de Pagamento",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(start = 36.dp, top = 30.dp, bottom = 25.dp)
                    .fillMaxWidth(),
                color = Color.Black
            )
            FormaPagamento()
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun Resumo() {
    val total = ProdutoManager.calcularTotal()
    Column {
        Text(
            text = "Resumo do Pedido",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 36.dp, top = 30.dp, bottom = 25.dp)
                .fillMaxWidth(),
            color = Color.Black
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xEEF0EFF0), shape = RoundedCornerShape(8.dp))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 36.dp, end = 36.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Subtotal", fontSize = 12.sp)
                    Text(text = "R$ ${total.replace(".", ",")}", fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 36.dp, end = 36.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Entrega", fontSize = 12.sp)
                    Text(text = "R$ 10,00", fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 36.dp, end = 36.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total", fontSize = 12.sp)
                    Text(
                        text = "R$ ${String.format("%.2f", total.toDouble() + 10.00)}",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun FormaPagamento() {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    val errorApi = remember { mutableStateOf("") }
    val api = RetrofitService.getApiPagamento()

    val (cpf, setCpf) = remember { mutableStateOf("") }
    val (nomeCompleto, setNomeCompleto) = remember { mutableStateOf("") }
    val pixCopiaECola = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 36.dp, end = 36.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = true, onClick = { /*TODO*/ }, modifier = Modifier
                    .height(24.dp)
                    .width(32.dp)
            )
            Text(text = "Pix", fontSize = 16.sp)
        }
        OutlinedTextField(
            value = cpf,
            onValueChange = setCpf,
            label = { Text("CPF") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = nomeCompleto,
            onValueChange = setNomeCompleto,
            label = { Text("Nome Completo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xEEF0EFF0), shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = "O código do Pix será exibido após a confirmação de compra e poderá ser " +
                        "copiado para pagamento no seu app do seu banco de preferência. " +
                        "O código gerado tem validade de 10 minutos e a aprovação do pagamento é feita na hora. ",
                fontSize = 10.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (pixCopiaECola.value.isEmpty()) {
            Button(
                onClick = {
                    val formaPagamento = Pagamento(cpf, nomeCompleto, "100.00")
                    val post = api.postPagamento(formaPagamento)
                    post.enqueue(object : Callback<PagamentoResultado> {
                        override fun onResponse(
                            call: Call<PagamentoResultado>,
                            response: Response<PagamentoResultado>
                        ) {
                            if (response.isSuccessful) {
                                Log.d("Pagamento realizado com sucesso", response.body().toString())
                                val pagamentoResultado = response.body()
                                if (pagamentoResultado != null) {
                                    pixCopiaECola.value = pagamentoResultado.pixCopiaECola!!
                                }
                            } else {
                                errorApi.value = "Erro ao fazer login"
                            }
                        }

                        override fun onFailure(call: Call<PagamentoResultado>, t: Throwable) {
                            errorApi.value = "Erro ao fazer login"
                            Log.d("Login realizado com falha", t.message.toString())
                        }
                    })
                },
                modifier = Modifier
                    .width(350.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF692FA3))
            ) {
                Text(text = "Finalizar o Pedido", color = Color.White)
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color(0xEEF0EFF0), shape = RoundedCornerShape(8.dp))
                    .clickable {
                        clipboardManager.setText(AnnotatedString(pixCopiaECola.value))
                        Toast
                            .makeText(
                                context,
                                "PIX copiado para a área de transferência!",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
            ) {
                Text(
                    text = pixCopiaECola.value,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ProdutoCarrinho(
    productName: String,
    productDescription: String,
    productPrice: String,
    imageResource: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),  // Adjust padding as needed
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = "http://${MyGlobals.ipFixo}:8080/api/midias/imagens/$imageResource",
            contentDescription = "Logo do Produto",
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
        )

        Spacer(modifier = Modifier.width(16.dp))  // Add space between image and text

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)  // Adjust padding as needed
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
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    BuyuMobileTheme {
        TelaPagamento("Android")
    }
}