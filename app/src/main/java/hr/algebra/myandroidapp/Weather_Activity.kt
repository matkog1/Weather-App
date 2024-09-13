package hr.algebra.myandroidapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hr.algebra.myandroidapp.api.callbacks.CityKeyCallback
import hr.algebra.myandroidapp.api.dataFetchers.CityKeyFetcher
import hr.algebra.myandroidapp.api.dataFetchers.DailyForecastFetcher
import hr.algebra.myandroidapp.api.callbacks.ForecastCallback
import hr.algebra.myandroidapp.api.model.ForecastsModel
import hr.algebra.myandroidapp.databinding.ActivityWeatherBinding


class Weather_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupSearchView()
    }

    private fun setupSearchView() {
        binding.svSearchCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchCityKey(query)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun fetchCityKey(cityName: String) {
        val fetcher = CityKeyFetcher(this)
        fetcher.fetchCityKey(cityName, object : CityKeyCallback {
            override fun onSuccess(cityKey: String, localizedName: String) {
                binding.tvCity.text = localizedName
                fetchWeatherForecast(cityKey)
            }
            override fun onFailure(error: String) {
                Log.e("API_ERROR", error)
            }
        })
    }

    private fun fetchWeatherForecast(cityKey: String) {
        val fetcher = DailyForecastFetcher(this)
        fetcher.fetchForecast(cityKey, object : ForecastCallback {
            override fun onSuccess(forecast: ForecastsModel) {

                val dailyForecast = forecast.dailyForecasts.getOrNull(0) ?: return

                val temperature = dailyForecast.temperature.maximum.value.toString() ?: "N/A"
                val alert = forecast.headline?.text ?: "No Alert"
                val dayIconPhrase = dailyForecast.day.iconPhrase ?: "No Data"
                val nightIconPhrase = dailyForecast.night.iconPhrase ?: "No Data"

                updateTemperature(temperature)
                updateAlert(alert)
                updateDayIconPhrase(dayIconPhrase)
                updateNightIconPhrase(nightIconPhrase)
                updateWeatherImage(dailyForecast.day.iconPhrase)

                updatePrecipitation(
                    dailyForecast.day.hasPrecipitation,
                    dailyForecast.day.precipitationType ?: "No Data",
                    dailyForecast.day.precipitationIntensity ?: "0",
                    binding.tvDayPrecipitation
                )

                updatePrecipitation(
                    dailyForecast.night.hasPrecipitation,
                    dailyForecast.night.precipitationType ?: "No Data",
                    dailyForecast.night.precipitationIntensity ?: "0",
                    binding.tvNightPrecipitation
                )
            }

            override fun onFailure(error: String) {
                Log.e("API_ERROR", error)
            }
        })
    }

    private fun updateWeatherImage(condition: String) {
        val imageResource = when (condition) {
            "Sunny" -> R.drawable.sunny
            "Mostly sunny" -> R.drawable.mostly_sunny
            "Partly sunny" -> R.drawable.partly_sunny
            "Cloudy" -> R.drawable.cloudy
            "Mostly cloudy" -> R.drawable.mostly_cloudy
            "Mostly Cloudy w/ Showers" -> R.drawable.mostly_cloudy_with_showers
            "T-Storms" -> R.drawable.t_storms
            "Showers" -> R.drawable.showers
            "Windy" -> R.drawable.windy
            "Cold" -> R.drawable.cold
            "Hot" -> R.drawable.hot
            "Rain" -> R.drawable.rain
            "Snow" -> R.drawable.snow
            else -> R.drawable.weather_forecast_default
        }
        binding.ivWeatherIcon.setImageResource(imageResource)
    }

    private fun updateTemperature(temperature: String) {
        binding.tvTemperature.text = temperature+"°C"
    }

    private fun updateAlert(alert: String) {
        binding.tvAlert.text = alert
    }

    private fun updateDayIconPhrase(iconPhrase: String) {
        binding.tvDayIconPhrase.text = iconPhrase
    }

    private fun updateNightIconPhrase(iconPhrase: String) {
        binding.tvNightIconPhrase.text = iconPhrase
    }

    private fun updatePrecipitation(
        hasPrecipitation: Boolean,
        precipitationType: String,
        precipitationIntensity: String,
        view: TextView
    ) {
        view.text = if (hasPrecipitation) {
            "$precipitationType. $precipitationIntensity"
        } else {
            "No precipitation"
        }
    }
}

