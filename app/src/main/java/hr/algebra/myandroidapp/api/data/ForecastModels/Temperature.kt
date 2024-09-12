package hr.algebra.myandroidapp.api.data.ForecastModels
import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("Minimum") val minimum : Minimum,
    @SerializedName("Maximum") val maximum : Maximum
)
