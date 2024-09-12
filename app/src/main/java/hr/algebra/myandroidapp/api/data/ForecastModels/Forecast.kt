package hr.algebra.myandroidapp.api.data.ForecastModels

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("Headline") val headline : Headline,
    @SerializedName("DailyForecasts") val dailyForecasts : List<DailyForecasts>
)
