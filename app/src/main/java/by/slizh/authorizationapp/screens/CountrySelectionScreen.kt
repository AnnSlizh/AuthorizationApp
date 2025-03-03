package by.slizh.authorizationapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.slizh.authorizationapp.Country
import by.slizh.authorizationapp.R
import by.slizh.authorizationapp.components.CountryListItem
import by.slizh.authorizationapp.ui.theme.LightGray

@Composable
fun CountrySelectionScreen(modifier: Modifier = Modifier) {
    var selectedCountry by remember { mutableStateOf<Country?>(null) }

    Column(modifier = modifier.padding(top = 26.dp)) {
        IconButton(modifier = Modifier
            .padding(start = 10.dp)
            .background(color = LightGray, shape = CircleShape)
            .size(32.dp),
            onClick = { }
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_icon),
                contentDescription = stringResource(id = R.string.return_back)
            )
        }
        Spacer(modifier = Modifier.height(14.dp))

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.country),
            style = typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(1.dp, color = LightGray, shape = RoundedCornerShape(10.dp))
                .background(Color.White)
        ) {
            items(Country.entries.toTypedArray()) { country ->
                CountryListItem(
                    country,
                    isSelected = selectedCountry == country,
                    onClick = { selectedCountry = country })
            }
        }
    }
}
