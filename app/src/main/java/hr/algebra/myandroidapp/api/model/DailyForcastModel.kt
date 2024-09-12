package hr.algebra.myandroidapp.api.model


import hr.algebra.myandroidapp.api.data.ForecastModels.Day
import hr.algebra.myandroidapp.api.data.ForecastModels.Night
import hr.algebra.myandroidapp.api.data.ForecastModels.Temperature

data class DailyForcastModel(
    val date : String,
    val epochDate : Int,
    val temperature : TemperatureModel,
    val day : DayModel,
    val night : NightModel,
    val sources : List<String>,
    val mobileLink : String,
    val link : String
)
