package by.slizh.authorizationapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.slizh.authorizationapp.R
import by.slizh.authorizationapp.ui.theme.Black
import by.slizh.authorizationapp.ui.theme.Blue
import by.slizh.authorizationapp.ui.theme.DarkGray
import by.slizh.authorizationapp.ui.theme.LightGray
import by.slizh.authorizationapp.ui.theme.robotoFamily

@Composable
fun AuthorizationScreen(modifier: Modifier = Modifier) {

    var phoneNumber by remember { mutableStateOf("") }

    Column(modifier.padding(start = 16.dp, top = 22.dp, end = 16.dp)) {
        Text(
            text = stringResource(id = R.string.lets_get_started),
            fontSize = 32.sp,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Medium,
            color = Black
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.padding(end = 42.dp),
            text = stringResource(id = R.string.enter_your_phone),
            fontSize = 14.sp,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Normal,
            color = DarkGray
        )

        Spacer(modifier = Modifier.height(36.dp))

        Row {
            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .width(90.dp)
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = LightGray,
                    contentColor = Black
                ),
                border = BorderStroke(1.dp, Color.Transparent),
                contentPadding = PaddingValues(
                    start = 14.dp,
                    top = 12.dp,
                    end = 14.dp,
                    bottom = 12.dp
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.russia_flag),
                        contentDescription = stringResource(id = R.string.flag),
                        modifier = Modifier
                            .size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "+7",
                        fontSize = 16.sp,
                        color = Black,
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = phoneNumber,
                placeholder = { Text(text = stringResource(id = R.string.mobile_number), color = DarkGray) },
                onValueChange = { phoneNumber = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Normal,
                ),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Black,
                    unfocusedTextColor = DarkGray,
                    focusedContainerColor = LightGray,
                    unfocusedContainerColor = LightGray,
                    focusedBorderColor = LightGray,
                    unfocusedBorderColor = LightGray,
                    cursorColor = Black
                )
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = { },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue,
                contentColor = Black
            )
        ) {
            Text(
                text = stringResource(id = R.string.continue_text),
                fontSize = 16.sp,
                color = Color.White,
                fontFamily = robotoFamily,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AuthorizationScreenPreview() {
    AuthorizationScreen()
}