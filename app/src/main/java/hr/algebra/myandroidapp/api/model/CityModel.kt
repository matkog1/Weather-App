package hr.algebra.myandroidapp.api.model

import hr.algebra.myandroidapp.api.data.CityModels.AdministrativeArea
import hr.algebra.myandroidapp.api.data.CityModels.Country
import hr.algebra.myandroidapp.api.data.CityModels.GeoPosition
import hr.algebra.myandroidapp.api.data.CityModels.Region
import hr.algebra.myandroidapp.api.data.CityModels.TimeZone

data class CityModel(
    val version : Int,
    val Key : Int,
    val type : String,
    val rank : Int,
    val localizedName : String,
    val englishName : String,
    val region : Region,
    val country : Country,
)
