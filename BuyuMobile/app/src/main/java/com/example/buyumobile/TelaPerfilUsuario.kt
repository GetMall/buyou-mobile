//package com.example.buyumobile
//
//import android.os.Bundle
//import androidx.activity.compose.setContent
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.buyumobile.ui.theme.BuyuMobileTheme
//
//
//class TelaPerfilUsuario : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TelaPerfilUsuarioContent()
//        }
//    }
//}
//
//@Composable
//fun TelaPerfilUsuarioContent() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//        Headerr()
//        Spacer(modifier = Modifier.height(16.dp))
//        BodyContent()
//        Spacer(modifier = Modifier.weight(1f))
//        BotaoVoltar()
//    }
//}
//
//@Composable
//fun Headerr() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(80.dp)
//            .background(color = Color(0xFF692FA3))
//            .padding(horizontal = 16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.logotipo_branco_buyu),
//            contentDescription = null,
//            modifier = Modifier.size(60.dp)
//        )
//        Spacer(modifier = Modifier.width(36.dp))
//        Text(
//            text = "R. Haddock Lobo, 595",
//            color = Color.White
//        )
//        Spacer(modifier = Modifier.weight(1f))
//        Image(
//            painter = painterResource(id = R.drawable.icone_carrinho),
//            contentDescription = null,
//            modifier = Modifier.size(25.dp)
//        )
//        Spacer(modifier = Modifier.width(16.dp))
//        Image(
//            painter = painterResource(id = R.drawable.icone_perfil),
//            contentDescription = null,
//            modifier = Modifier.size(25.dp)
//        )
//    }
//}
//
//@Composable
//fun BodyContent() {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Box(
//            modifier = Modifier
//                .padding(16.dp)
//                .size(width = 325.dp, height = 170.dp)
//                .shadow(15.dp)
//                .background(Color.White)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.foto_de_perfil),
//                contentDescription = null,
//                modifier = Modifier
//                    .padding(start = 70.dp)
//                    .size(90.dp)
//                    .align(Alignment.CenterStart)
//            )
//            Text(
//                text = "Meu perfil",
//                color = Color(0xFF424242),
//                fontSize = 18.sp,
//                modifier = Modifier
//                    .padding(end = 70.dp, bottom = 75.dp)
//                    .align(Alignment.CenterEnd)
//            )
//            Text(
//                text = "George",
//                color = Color(0xFF692FA3),
//                fontSize = 22.sp,
//                modifier = Modifier
//                    .padding(end = 80.dp, bottom = 80.dp)
//                    .align(Alignment.BottomEnd)
//            )
//        }
//        Spacer(modifier = Modifier.height(2.dp))
//        RetanguloItem(
//            imagem = R.drawable.icone_config,
//            texto = "Configurações da minha conta",
//        )
//        RetanguloItem(
//            imagem = R.drawable.icone_dinheiro,
//            texto = "Créditos"
//        )
//        RetanguloItem(
//            imagem = R.drawable.icone_relogio,
//            texto = "Últimos pedidos"
//        )
//        RetanguloItem(
//            imagem = R.drawable.icone_suporte,
//            texto = "Central de Ajuda"
//        )
//        RetanguloItem(
//            imagem = R.drawable.icone_logout,
//            texto = "Sair"
//        )
//    }
//
//}
//
//@Composable
//fun RetanguloItem(imagem: Int, texto: String) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .width(325.dp)
//            .shadow(15.dp)
//            .background(color = Color.White)
//            .padding(horizontal = 16.dp, vertical = 8.dp)
//    ) {
//        Image(
//            painter = painterResource(id = imagem),
//            contentDescription = null,
//            modifier = Modifier.size(40.dp)
//        )
//        Text(
//            text = texto,
//            color = Color(0xFF000000),
//            modifier = Modifier.padding(start = 16.dp)
//        )
//    }
//    Spacer(modifier = Modifier.height(4.dp))
//}
//
//@Composable
//fun BotaoVoltar() {
//    Button(
//        onClick = {},
//        modifier = Modifier
//            .padding(start = 35.dp, bottom = 50.dp)
//            .width(320.dp)
//            .height(50.dp)
//            .background(color = Color(0xFF692FA3), shape = RoundedCornerShape(80.dp))
//    ) {
//        Text(text = "Voltar", color = Color.White)
//    }
//}
//
//
//@Preview
//@Composable
//fun PreviewTelaPerfilUsuario() {
//    BuyuMobileTheme {
//        TelaPerfilUsuarioContent()
//    }
//}
