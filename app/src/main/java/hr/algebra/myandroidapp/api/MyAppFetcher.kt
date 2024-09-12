package hr.algebra.myandroidapp.api

import android.content.Context
import android.util.Log
import hr.algebra.myandroidapp.MyAppReceiver
import hr.algebra.myandroidapp.api.data.CityModels.City
import hr.algebra.myandroidapp.framework.sendBroadcast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import hr.algebra.myandroidapp.api.model.CityModel as CityModel


class MyAppFetcher(private val context: Context) {

    private val cityApi : CityApi
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(CITY_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        cityApi = retrofit.create(CityApi::class.java)
    }

    fun fetchItems(city:String){
        val request = cityApi.fetchItems(city)

        request.enqueue(object: Callback<List<City>>{
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        populateItems(it)
                    }
                } else {
                    Log.e("API_ERROR", "Response code: ${response.code()} - ${response.message()}")
                }
            }
            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                Log.e("API_ERROR", "Error: ${t.message}")
            }

        })

    }

    private fun populateItems(cities: List<City>) {

        val items = mutableListOf<CityModel>()

       cities.forEach{
          items.add(CityModel(
              it.version,
              it.Key,
              it.type,
              it.rank,
              it.localizedName,
              it.englishName,
              it.region,
              it.country
          ))
       }
        print(items)

        context.sendBroadcast<MyAppReceiver>()
    }
}