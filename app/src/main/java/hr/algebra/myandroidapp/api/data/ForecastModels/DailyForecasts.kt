package hr.algebra.myandroidapp.api.data.ForecastModels

import com.google.gson.annotations.SerializedName


data class DailyForecasts(
    @SerializedName("Date") val date : String,
    @SerializedName("EpochDate") val epochDate : Int,
    @SerializedName("Temperature") val temperature : Temperature,
    @SerializedName("Day") val day : Day,
    @SerializedName("Night") val night : Night,
    @SerializedName("Sources") val sources : List<String>,
    @SerializedName("MobileLink") val mobileLink : String,
    @SerializedName("Link") val link : String
)
