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

                val dailyForecast = forecast.dailyForecasts[0]

                updateTemperature(dailyForecast.temperature.maximum.value.toString())
                updateAlert(forecast.headline.text)
                updateDayIconPhrase(dailyForecast.day.iconPhrase)
                updateNightIconPhrase(dailyForecast.night.iconPhrase)

                updatePrecipitation(
                    dailyForecast.day.hasPrecipitation,
                    dailyForecast.day.precipitationType,
                    dailyForecast.day.precipitationIntensity.toString(),
                    binding.tvDayPrecipitation
                )

                updatePrecipitation(
                    dailyForecast.night.hasPrecipitation,
                    dailyForecast.night.precipitationType,
                    dailyForecast.night.precipitationIntensity.toString(),
                    binding.tvNightPrecipitation
                )
            }

            override fun onFailure(error: String) {
                Log.e("API_ERROR", error)
            }
        })
    }

    private fun updateTemperature(temperature: String) {
        binding.tvTemperature.text = temperature
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

