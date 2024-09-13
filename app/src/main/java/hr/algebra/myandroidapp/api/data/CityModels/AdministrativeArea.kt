package hr.algebra.myandroidapp.api.data.CityModels

import com.google.gson.annotations.SerializedName

data class AdministrativeArea(
    @SerializedName("ID") val iD : String,
    @SerializedName("LocalizedName") val localizedName : String,
    @SerializedName("EnglishName") val englishName : String,
    @SerializedName("Level") val level : Int,
    @SerializedName("LocalizedType") val localizedType : String,
    @SerializedName("EnglishType") val englishType : String,
    @SerializedName("CountryID") val countryID : String
)
