package by.slizh.authorizationapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.slizh.authorizationapp.Country
import by.slizh.authorizationapp.R

@Composable
fun CountryListItem(country: Country, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = country.flagResId),
            contentDescription = stringResource(id = R.string.belarus_flag),
            modifier = Modifier
                .padding(start = 12.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
                .size(40.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = country.countryName.toString(),
                style = typography.bodyLarge,
            )

            Text(
                text = country.phoneCode.toString(),
                style = typography.bodyMedium,
            )
        }

        if (isSelected) {
            Image(
                painter = painterResource(id = R.drawable.check_icon),
                contentDescription = stringResource(id = R.string.selected_state),
                modifier = Modifier
                    .padding(top = 20.dp, end = 20.dp, bottom = 20.dp)
                    .size(24.dp)
            )
        }
    }
}