package hr.algebra.myandroidapp.api.callbacks

import hr.algebra.myandroidapp.api.model.ForecastsModel

interface ForecastCallback {
    fun onSuccess(forecast: ForecastsModel)
    fun onFailure(error: String)
}
