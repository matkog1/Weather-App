package hr.algebra.myandroidapp.api.data.CityModels

import com.google.gson.annotations.SerializedName

data class TimeZone(

    @SerializedName("Code") val code : String,
    @SerializedName("Name") val name : String,
    @SerializedName("GmtOffset") val gmtOffset : Int,
    @SerializedName("IsDaylightSaving") val isDaylightSaving : Boolean,
    @SerializedName("NextOffsetChange") val nextOffsetChange : String
)
