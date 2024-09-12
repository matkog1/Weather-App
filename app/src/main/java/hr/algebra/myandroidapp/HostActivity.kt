package hr.algebra.myandroidapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import hr.algebra.myandroidapp.api.DailyForecastFetcher
import hr.algebra.myandroidapp.api.ForecastCallback
import hr.algebra.myandroidapp.api.model.ForecastsModel
import hr.algebra.myandroidapp.databinding.ActivityHostBinding

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHostBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        enableEdgeToEdge()

        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initHamburgerMenu()
        initNavigation()
        fetchForecastData("168720")

    }

    private fun fetchForecastData(cityKey: String) {
        val fetcher = DailyForecastFetcher(this)
        fetcher.fetchForecast("168720", object : ForecastCallback {
            override fun onSuccess(forecast: ForecastsModel) {
                binding.tvTest.text = "Headline: ${forecast.headline.text}"
            }
            override fun onFailure(error: String) {
                Log.e("API_ERROR", error)
            }
        })
    }

    private fun initNavigation() {
        val navController = Navigation.findNavController(this, R.id.navController)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    private fun initHamburgerMenu() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_launcher_background)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.host_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                toggleDrawer()
                return true
            }
            R.id.menuExit->{
                ExitApp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun ExitApp() {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.exit)
            setMessage(getString(R.string.exit_application))
            setIcon(R.drawable.ic_launcher_background)
            setCancelable(true)
            setNegativeButton(getString(R.string.cancel), null)
            setPositiveButton("OK"){_,_-> finish()}
            show()
        }
    }

    private fun toggleDrawer() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawers()
        } else {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

}
