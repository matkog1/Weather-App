package hr.algebra.myandroidapp.api.ApiCalls


import hr.algebra.myandroidapp.api.data.CityModels.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


const val CITY_API_URL = "https://dataservice.accuweather.com/locations/v1/cities/search/"
interface CityApi {
    @GET("?apikey=NtkGjjljnlvoCOXTGoybr5mT0YFcU7bv")
    fun fetchCity(@Query("q") cityName: String): Call<List<City>>
}