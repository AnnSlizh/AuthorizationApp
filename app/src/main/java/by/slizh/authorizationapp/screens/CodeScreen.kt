package by.slizh.authorizationapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.slizh.authorizationapp.R
import by.slizh.authorizationapp.components.CodeTextField
import by.slizh.authorizationapp.ui.theme.Blue

@Composable
fun CodeScreen(modifier: Modifier = Modifier) {
    val code = remember { mutableStateListOf("", "", "", "", "", "") }

    Column(
        modifier = modifier.padding(top = 26.dp)
    ) {
        IconButton(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(24.dp),
            onClick = { }
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = stringResource(id = R.string.return_back),
            )
        }
        Spacer(modifier = Modifier.height(18.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.code),
            style = typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.enter_the_code),
            style = typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(56.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                items(code.size, key = { it }) { index ->
                    CodeTextField(
                        value = code[index],
                        onValueChange = { newValue ->
                            if (newValue.length <= 1) {
                                code[index] = newValue
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable { },
                text = stringResource(id = R.string.no_code_received),
                color = Blue,
                style = typography.headlineSmall
            )
        }
    }
}
