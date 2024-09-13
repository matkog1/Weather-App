package hr.algebra.myandroidapp.api.dataFetchers

import android.content.Context
import hr.algebra.myandroidapp.api.apiCalls.CITY_API_URL
import hr.algebra.myandroidapp.api.apiCalls.CityApi
import hr.algebra.myandroidapp.api.callbacks.CityKeyCallback
import hr.algebra.myandroidapp.api.data.CityModels.City
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CityKeyFetcher(private val context: Context) {

    private val cityApi: CityApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(CITY_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        cityApi = retrofit.create(CityApi::class.java)
    }

    fun fetchCityKey(city: String, callback: CityKeyCallback) {
        val request = cityApi.fetchCity(city)

        request.enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.isSuccessful && response.body() != null) {
                    val cityList = response.body()
                    if (!cityList.isNullOrEmpty()) {
                        val city = cityList[0]
                        val cityKey = city.Key
                        val cityName = city.localizedName
                        callback.onSuccess(cityKey, cityName)
                    } else {
                        callback.onFailure("No city found")
                    }
                } else {
                    callback.onFailure("API error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                callback.onFailure("Error: ${t.message}")
            }
        })
    }
}