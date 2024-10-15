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
import com.smart_egbuchulem.workout_crunch.databinding.ActivityFitnessHinderanceBinding

class FitnessHinderanceActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFitnessHinderanceBinding
    private lateinit var backButton: ImageView
    private lateinit var forwardButton: ImageView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioNoPlan : RadioButton
    private lateinit var radioLackOfMotivation: RadioButton
    private lateinit var radioBadCoaching: RadioButton
    private lateinit var radioTooHard: RadioButton
    private lateinit var notificationText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFitnessHinderanceBinding.inflate(layoutInflater)
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
        radioNoPlan= binding.noPlan
        radioLackOfMotivation = binding.lackOfMotivation
        radioBadCoaching = binding.badCoaching
        radioTooHard = binding.tooHard
        notificationText = binding.notificationText


        // Handle Forward Button Click
        forwardButton.setOnClickListener {
            // Check selected radio button
            val selectedOption = when {
                radioNoPlan.isChecked -> "I didn't have a clear plan"
                radioLackOfMotivation.isChecked -> "Lack of motivation"
                radioBadCoaching.isChecked -> "I had bad coaching"
                radioTooHard.isChecked -> "My workouts were too hard"
                else -> null
            }

            if (selectedOption != null) {
                // Pass selected option to the next activity
                val intent = Intent(this, SignUpActivity::class.java)
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