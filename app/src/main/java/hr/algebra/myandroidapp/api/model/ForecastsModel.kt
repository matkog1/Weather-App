package hr.algebra.myandroidapp.api.model

import hr.algebra.myandroidapp.api.data.ForecastModels.DailyForecasts
import hr.algebra.myandroidapp.api.data.ForecastModels.Headline

data class ForecastsModel(
    val headline: Headline,
    var dailyForecasts: List<DailyForecasts>
)
