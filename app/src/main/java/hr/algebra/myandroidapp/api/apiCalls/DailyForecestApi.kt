package hr.algebra.myandroidapp.api.apiCalls

import hr.algebra.myandroidapp.api.data.ForecastModels.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val FORECAST_API_URL = "https://dataservice.accuweather.com/"
interface DailyForecestApi {
    @GET("forecasts/v1/daily/1day/{cityKey}")
    fun getDailyForecast(
        @Path("cityKey") cityKey: String,
        @Query("apikey") apiKey: String,
        @Query("metric") metric: Boolean = true
    ): Call<Forecast>
}