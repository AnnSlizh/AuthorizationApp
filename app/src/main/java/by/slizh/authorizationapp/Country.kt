package by.slizh.authorizationapp

import androidx.annotation.DrawableRes

enum class Country(
    val countryName: Int,
    val phoneCode: Int,
    @DrawableRes val flagResId: Int
) {
    BELARUS(R.string.belarus_name, R.string.belarus_phone_code, R.drawable.belarus_flag),
    RUSSIA(R.string.russia_name, R.string.russia_phone_code, R.drawable.russia_flag),
    USA(R.string.usa_name, R.string.usa_phone_code, R.drawable.usa_flag);
}