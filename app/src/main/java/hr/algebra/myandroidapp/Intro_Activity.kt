package hr.algebra.myandroidapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hr.algebra.myandroidapp.databinding.ActivityIntroBinding
import hr.algebra.myandroidapp.databinding.ActivityWeatherBinding
import android.content.Intent
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView


class Intro_Activity : AppCompatActivity() {

    private val delayMillis: Long = 1000 // 1 second
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        animate()
        redirect()
    }

    private fun animate() {
        val textView = findViewById<TextView>(R.id.tvStartupText)

        // Load and start the animation set
        val animationSet: Animation = AnimationUtils.loadAnimation(this, R.anim.blink)
        textView.startAnimation(animationSet)
    }

    private fun redirect() {
        Handler().postDelayed({
            val intent = Intent(this, Weather_Activity::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}