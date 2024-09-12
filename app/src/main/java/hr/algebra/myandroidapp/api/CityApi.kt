package hr.algebra.myandroidapp.api


import hr.algebra.myandroidapp.api.data.CityModels.City
import hr.algebra.myandroidapp.api.data.ForecastModels.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val CITY_API_URL = "https://dataservice.accuweather.com/locations/v1/cities/search/"
interface CityApi {
    @GET("?apikey=dS8FC96dlASLH1aPuEUujHm2ALSwHaE8")
    fun fetchItems(@Query("q") cityName: String): Call<List<City>>
}