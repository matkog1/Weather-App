package hr.algebra.myandroidapp.api.model

import com.google.gson.annotations.SerializedName

data class DayModel(
    val icon : Int,
    val iconPhrase : String,
    val hasPrecipitation : Boolean
)
