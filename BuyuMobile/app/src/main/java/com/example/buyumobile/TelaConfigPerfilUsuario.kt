//package com.example.buyumobile
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.buyumobile.ui.theme.BuyuMobileTheme
//
//class TelaConfigPerfilUsuario : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//}
//
//@Composable
//fun Header() {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = Color(0xFF692FA3))
//            .padding(horizontal = 16.dp, vertical = 8.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.logotipo_branco_buyu),
//            contentDescription = null,
//            modifier = Modifier.size(60.dp)
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            text = "R. Haddock Lobo, 595",
//            color = Color.White
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Image(
//                painter = painterResource(id = R.drawable.icone_carrinho),
//                contentDescription = null,
//                modifier = Modifier.size(25.dp)
//            )
//
//            Spacer(modifier = Modifier.width(16.dp))
//
//            Image(
//                painter = painterResource(id = R.drawable.icone_perfil),
//                contentDescription = null,
//                modifier = Modifier.size(25.dp)
//            )
//        }
//    }
//}
//
//@Composable
//fun TelaConfigPerfilUsuarioContent() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Spacer(modifier = Modifier.height(50.dp))
//
//        Text(
//            text = "Configurações da minha conta",
//            color = Color(0xFF692FA3),
//            fontSize = 18.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        val fotoPerfil = painterResource(id = R.drawable.foto_de_perfil)
//        val iconeMais = painterResource(id = R.drawable.plus_circle)
//
//        Image(
//            painter = fotoPerfil,
//            contentDescription = null,
//            modifier = Modifier
//                .size(120.dp)
//                .padding(bottom = 8.dp),
//            contentScale = ContentScale.Crop
//        )
//
//        Image(
//            painter = iconeMais,
//            contentDescription = null,
//            modifier = Modifier
//                .size(24.dp)
//                .padding(bottom = 8.dp, end = 8.dp),
//            contentScale = ContentScale.Fit
//        )
//
//        Text(
//            text = "Editar foto",
//            color = Color(0xFF000000),
//            fontSize = 16.sp,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        TextField(
//            value = "",
//            onValueChange = {},
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp, horizontal = 16.dp)
//                .height(56.dp)
//                .background(color = Color(0xFF808080), shape = RoundedCornerShape(8.dp)),
//            textStyle = TextStyle(color = Color.Black),
//            placeholder = { Text(text = "Nome") }
//        )
//
//        TextField(
//            value = "",
//            onValueChange = {},
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp, horizontal = 16.dp)
//                .height(56.dp)
//                .background(color = Color(0xFFF3F3F3), shape = RoundedCornerShape(8.dp)),
//            textStyle = TextStyle(color = Color.Black),
//            placeholder = { Text(text = "Sobrenome") }
//        )
//
//        TextField(
//            value = "",
//            onValueChange = {},
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp, horizontal = 16.dp)
//                .height(56.dp)
//                .background(color = Color(0xFFF3F3F3), shape = RoundedCornerShape(8.dp)),
//            textStyle = TextStyle(color = Color.Black),
//            placeholder = { Text(text = "E-mail") }
//        )
//
//        TextField(
//            value = "",
//            onValueChange = {},
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp, horizontal = 16.dp)
//                .height(56.dp)
//                .background(color = Color(0xFFF3F3F3), shape = RoundedCornerShape(8.dp)),
//            textStyle = TextStyle(color = Color.Black),
//            placeholder = { Text(text = "Celular") }
//        )
//
//        Button(
//            onClick = { },
//            modifier = Modifier
//                .padding(top = 16.dp)
//                .height(48.dp)
//                .fillMaxWidth()
//                .background(Color(0xFF692FA3)),
//            shape = RoundedCornerShape(8.dp)
//        ) {
//            Text(
//                text = "Salvar alterações",
//                color = Color.White,
//                fontSize = 16.sp
//            )
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { },
//            modifier = Modifier
//                .height(48.dp)
//                .fillMaxWidth()
//                .background(Color(0xFF692FA3)),
//            shape = RoundedCornerShape(8.dp)
//        ) {
//            Text(
//                text = "Voltar",
//                color = Color.White,
//                fontSize = 16.sp
//            )
//        }
//    }
//}
//
//@Preview
//@Composable
//fun PreviewTelaConfigPerfilUsuario() {
//    BuyuMobileTheme {
//        TelaConfigPerfilUsuarioContent()
//    }
//}
