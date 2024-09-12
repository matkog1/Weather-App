package hr.algebra.myandroidapp.api

import android.content.Context
import android.util.Log
import hr.algebra.myandroidapp.MyAppReceiver
import hr.algebra.myandroidapp.api.data.ForecastModels.Forecast
import hr.algebra.myandroidapp.api.model.ForecastsModel
import hr.algebra.myandroidapp.framework.sendBroadcast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DailyForecastFetcher(private val context: Context) {

    private val forecastApi: DailyForecestApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(FORECAST_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        forecastApi = retrofit.create(DailyForecestApi::class.java)
    }

    fun fetchForecast(cityKey: String, callback: ForecastCallback) {
        val request = forecastApi.getDailyForecast(cityKey, "dS8FC96dlASLH1aPuEUujHm2ALSwHaE8")

        request.enqueue(object : Callback<Forecast> {
            override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {
                if (response.isSuccessful) {
                    val forecastResponse = response.body()
                    Log.d("API_RESPONSE", "Response: $forecastResponse")
                    if (forecastResponse != null) {
                        val forecastModel = populateForecast(forecastResponse)
                        callback.onSuccess(forecastModel)
                    } else {
                        callback.onFailure("Response body is null")
                    }
                } else {
                    callback.onFailure("Response code: ${response.code()} - ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Forecast>, t: Throwable) {
                callback.onFailure("Error: ${t.message}")
            }
        })
    }

    private fun populateForecast(forecasts: Forecast) : ForecastsModel {
        val forecastsModel = ForecastsModel(
            headline = forecasts.headline,
            dailyForecasts = forecasts.dailyForecasts
        )
        return forecastsModel
        //context.sendBroadcast<MyAppReceiver>()
    }
}
