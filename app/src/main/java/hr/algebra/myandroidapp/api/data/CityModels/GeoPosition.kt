package hr.algebra.myandroidapp.api.data.CityModels

import com.google.gson.annotations.SerializedName

data class GeoPosition(
    @SerializedName("Latitude") val latitude : Double,
    @SerializedName("Longitude") val longitude : Double,
    @SerializedName("Elevation") val elevation : Elevation
)
