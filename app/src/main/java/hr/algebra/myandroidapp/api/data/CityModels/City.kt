package hr.algebra.myandroidapp.api.data.CityModels

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("Key") val Key : String,
    @SerializedName("LocalizedName") val localizedName : String,
)
