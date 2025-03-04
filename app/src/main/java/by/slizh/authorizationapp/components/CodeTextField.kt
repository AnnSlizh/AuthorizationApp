package by.slizh.authorizationapp.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import by.slizh.authorizationapp.ui.theme.Black
import by.slizh.authorizationapp.ui.theme.DarkGray
import by.slizh.authorizationapp.ui.theme.Gray
import by.slizh.authorizationapp.ui.theme.LightGray

@Composable
fun CodeTextField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(start = 8.dp)
            .width(44.dp)
            .height(50.dp),
        shape = RoundedCornerShape(10.dp),
        textStyle = typography.bodyLarge,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Black,
            unfocusedTextColor = DarkGray,
            focusedContainerColor = Gray,
            unfocusedContainerColor = LightGray,
            focusedBorderColor = Gray,
            unfocusedBorderColor = LightGray,
            cursorColor = Black
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        )
    )
}