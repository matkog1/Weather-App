package hr.algebra.myandroidapp.api.data.ForecastModels
import com.google.gson.annotations.SerializedName
data class Night(
    @SerializedName("Icon") val icon : Int,
    @SerializedName("IconPhrase") val iconPhrase : String,
    @SerializedName("HasPrecipitation") val hasPrecipitation : Boolean,
    @SerializedName("PrecipitationType") val precipitationType : String,
    @SerializedName("PrecipitationIntensity") val precipitationIntensity : String
)
