package hr.algebra.myandroidapp.api.data.CityModels

import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("ID") val iD : String,
    @SerializedName("LocalizedName") val localizedName : String,
    @SerializedName("EnglishName") val englishName : String
)
