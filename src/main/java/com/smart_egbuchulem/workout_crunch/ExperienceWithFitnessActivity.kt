package com.smart_egbuchulem.workout_crunch

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.smart_egbuchulem.workout_crunch.databinding.ActivityExperienceWithFitnessBinding

class ExperienceWithFitnessActivity : AppCompatActivity() {

    private lateinit var binding : ActivityExperienceWithFitnessBinding
    private lateinit var backButton: ImageView
    private lateinit var forwardButton: ImageView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioStruggleGainWeight: RadioButton
    private lateinit var radioGainLoseWeightEasily: RadioButton
    private lateinit var radioGainWeightSlowly: RadioButton
    private lateinit var notificationText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExperienceWithFitnessBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Make content appear behind the status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        // Optionally, set the status bar color to transparent for a seamless background
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }

        // Receive the intent data from MainGoalsActivity
        val userAgeRange = intent.getStringExtra("UserAgeRange")
        val selectedGoals = intent.getStringExtra("SelectedGoals")


        // Set up back button to go back
        binding.backButton.setOnClickListener { onBackPressed() }




        // Initialize Views
        backButton = binding.backButton
        forwardButton = binding.forwardButton
        radioStruggleGainWeight = binding.radioStruggleGainWeight
        radioGainLoseWeightEasily = binding.radioGainLoseWeightEasily
        radioGainWeightSlowly = binding.radioGainWeightSlowly
        notificationText = binding.notificationText


        // Handle Forward Button Click
        forwardButton.setOnClickListener {
            // Check selected radio button
            val selectedOption = when {
                radioStruggleGainWeight.isChecked -> "I struggle to gain weight or muscle"
                radioGainLoseWeightEasily.isChecked -> "I gain and lose weight easily"
                radioGainWeightSlowly.isChecked -> "I gain weight fast but lose it slowly"
                else -> null
            }

            if (selectedOption != null) {
                // Pass selected option to the next activity
                val intent = Intent(this, FitnessHinderanceActivity::class.java)
                intent.putExtra("SelectedExperience", selectedOption)
                startActivity(intent)
            }else{
                notificationText.text = "Select an option"

                Handler(Looper.getMainLooper()).postDelayed({
                    notificationText.text = ""
                }, 4000)
            }
        }
    }
}
