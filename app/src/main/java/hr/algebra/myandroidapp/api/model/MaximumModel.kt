package hr.algebra.myandroidapp.api.model

import com.google.gson.annotations.SerializedName

data class MaximumModel(
    val value : Double,
    val unit : String,
    val unitType : Int
)
