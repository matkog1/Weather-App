package hr.algebra.myandroidapp.api.model

data class HeadlineModel(
    val effectiveDate : String,
    val effectiveEpochDate : Int,
    val severity : Int,
    val text : String,
    val category : String,
    val endDate : String,
    val endEpochDate : Int,
    val mobileLink : String,
    val link : String
)
