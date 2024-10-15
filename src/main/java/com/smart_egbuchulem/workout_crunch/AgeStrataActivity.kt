package com.smart_egbuchulem.workout_crunch

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.smart_egbuchulem.workout_crunch.databinding.ActivityAgeStrataBinding

class AgeStrataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgeStrataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAgeStrataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make content appear behind the status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        // Optionally, set the status bar color to transparent for a seamless background
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }

        binding.ageStrataImg1.setOnClickListener { onImageClick(it) }
        binding.ageStrataImg2.setOnClickListener { onImageClick(it) }
        binding.ageStrataImg3.setOnClickListener { onImageClick(it) }
        binding.ageStrataImg4.setOnClickListener { onImageClick(it) }
    }

//    private fun onImageClick(view: View) {
//        when (view.id) {
//            R.id.ageStrataImg1 -> run {
//                val mainIntent = Intent(this, MainGoalsActivity::class.java).apply {
//                    putExtra("UserAgeRange", "18-29")
//                }
//                startActivity(mainIntent)
//            }
//            R.id.ageStrataImg2 -> run {
//                val mainIntent = Intent(this, MainGoalsActivity::class.java).apply {
//                    putExtra("UserAgeRange", "30-39")
//                }
//                startActivity(mainIntent)
//            }
//            R.id.ageStrataImg3 -> Toast.makeText(this, "Image 3 clicked", Toast.LENGTH_SHORT).show()
//            R.id.ageStrataImg4 -> Toast.makeText(this, "Image 4 clicked", Toast.LENGTH_SHORT).show()
//        }
//    }

    private fun onImageClick(view: View) {
        val userAgeRange: String = when (view.id) {
            R.id.ageStrataImg1 -> "18-29"
            R.id.ageStrataImg2 -> "30-39"
            else -> return
        }

        val mainIntent = Intent(this, MainGoalsActivity::class.java).apply {
            putExtra("UserAgeRange", userAgeRange)
        }

        startActivity(mainIntent)
    }


}

