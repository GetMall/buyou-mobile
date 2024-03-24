package com.example.telaloginbuyu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telaloginbuyu.ui.theme.TelaLoginBuyuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelaLoginBuyuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Image
        Image(
            painter = painterResource(id = R.drawable.ellipse_48),
            contentDescription = "Login Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp) // Altura desejada da imagem
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.logotipo_roxo),
            contentDescription = "Company Logo",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email field
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Email")
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.icon_email),
                        contentDescription = "Email Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)), // Arredondando as bordas da caixa de entrada
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) })
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Password field
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Senha")
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.icon_senha),
                        contentDescription = "Lock Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)), // Arredondando as bordas da caixa de entrada
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF692FA3))
        ) {
            Text(text = "Entrar em minha conta", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Divider(modifier = Modifier.weight(1f), color = Color.Gray)
            Text(text = "ou", modifier = Modifier.padding(horizontal = 8.dp))
            Divider(modifier = Modifier.weight(1f), color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(text = "Criar uma conta", color = Color(0xFF692FA3))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    TelaLoginBuyuTheme {
        LoginScreen()
    }
}
