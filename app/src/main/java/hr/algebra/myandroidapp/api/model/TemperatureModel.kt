package hr.algebra.myandroidapp.api.model

import com.google.gson.annotations.SerializedName
import hr.algebra.myandroidapp.api.data.ForecastModels.Maximum
import hr.algebra.myandroidapp.api.data.ForecastModels.Minimum

data class TemperatureModel(
    val minimum : MinimumModel,
    val maximum : MaximumModel
)
