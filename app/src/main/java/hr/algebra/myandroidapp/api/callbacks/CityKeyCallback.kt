package hr.algebra.myandroidapp.api.callbacks


interface CityKeyCallback {
    fun onSuccess(cityKey: String, localizedName: String)
    fun onFailure(error: String)
}
