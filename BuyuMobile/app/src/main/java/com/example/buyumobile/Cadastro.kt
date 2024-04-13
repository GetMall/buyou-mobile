package com.example.buyumobile

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buyumobile.ui.theme.BuyuMobileTheme

class Cadastro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BuyuMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Cadastro("Android")
                }
            }
        }
    }
}

@Composable
fun Cadastro(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val (usuario, setUsuario) = remember {mutableStateOf("")}
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.logotipo_roxo),
            contentDescription = "Login Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(0.dp, 30.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = usuario,
                onValueChange = setUsuario,
                label = { Text("Usuário", style = TextStyle(color = Color(0xFF692FA3))) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF3F3F3)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { /* Handle next action */ }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF3F3F3),
                    unfocusedBorderColor = Color.White
                ),
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = setEmail,
                label = { Text("Email", style = TextStyle(color = Color(0xFF692FA3))) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF3F3F3)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { /* Handle next action */ }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF3F3F3),
                    unfocusedBorderColor = Color.White
                ),
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = setPassword,
                label = { Text("Senha", style = TextStyle(color = Color(0xFF692FA3))) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF3F3F3)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF3F3F3),
                    unfocusedBorderColor = Color.White
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = setPassword,
                label = { Text("Confirmar Senha", style = TextStyle(color = Color(0xFF692FA3))) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF3F3F3)),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF3F3F3),
                    unfocusedBorderColor = Color.White
                ),
            )

            Spacer(modifier = Modifier.height(64.dp))

            Button(
                onClick = {
                    val telaInicio = Intent(context, MainActivity2::class.java)
                    context.startActivity(telaInicio)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF692FA3))
            ) {
                Text(text = "Criar uma conta", color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Divider(modifier = Modifier.weight(1f), color = Color.Gray)
                Text(text = "ou", modifier = Modifier.padding(horizontal = 8.dp))
                Divider(modifier = Modifier.weight(1f), color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val telaCadastro = Intent(context, Cadastro::class.java)
                    context.startActivity(telaCadastro)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(text = "Entrar em minha conta", color = Color(0xFF692FA3))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    BuyuMobileTheme {
        Cadastro("Android")
    }
}