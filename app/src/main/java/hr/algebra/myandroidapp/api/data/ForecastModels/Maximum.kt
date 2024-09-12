package hr.algebra.myandroidapp.api.data.ForecastModels
import com.google.gson.annotations.SerializedName
data class Maximum(
    @SerializedName("Value") val value : Double,
    @SerializedName("Unit") val unit : String,
    @SerializedName("UnitType") val unitType : Int
)
