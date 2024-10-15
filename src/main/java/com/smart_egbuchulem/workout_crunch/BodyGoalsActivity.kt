//package com.smart_egbuchulem.workout_crunch
//
//
//import android.graphics.Color
//import android.os.Build
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.smart_egbuchulem.workout_crunch.databinding.ActivityBodyGoalsBinding
//
//class BodyGoalsActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityBodyGoalsBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityBodyGoalsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Make content appear behind the status bar
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

//        // Optionally, set the status bar color to transparent for a seamless background
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = Color.TRANSPARENT
//        }
//
//        // Receive the intent data from MainGoalsActivity
//        val userAgeRange = intent.getStringExtra("UserAgeRange")
//        val selectedGoals = intent.getStringExtra("SelectedGoals")
//
//        // Display the received data
//        Toast.makeText(this, "User Age Range: $userAgeRange", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, "Selected Goals: $selectedGoals", Toast.LENGTH_SHORT).show()
//
//        // You can use the received data to update your UI as needed
//        binding.ageRangeText.text = "Age Range: $userAgeRange"
//        binding.selectedGoalsText.text = "Selected Goals: $selectedGoals"
//    }
//}





package com.smart_egbuchulem.workout_crunch

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.smart_egbuchulem.workout_crunch.databinding.ActivityBodyGoalsBinding

class BodyGoalsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBodyGoalsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBodyGoalsBinding.inflate(layoutInflater)
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

        // Set up click listeners for each body goal radio button
        val radioButtonGroup = listOf(
            binding.radioButtonSmaller,
            binding.radioButtonAthletic,
            binding.radioButtonShredded,
            binding.radioButtonSwole
        )

        radioButtonGroup.forEach { button ->
            button.setOnClickListener { onRadioButtonClick(it) }
        }
    }

    private fun onRadioButtonClick(view: View) {
        // Deselect all buttons
        binding.radioButtonSmaller.isChecked = false
        binding.radioButtonAthletic.isChecked = false
        binding.radioButtonShredded.isChecked = false
        binding.radioButtonSwole.isChecked = false

        // Check the selected button
        (view as? RadioButton)?.isChecked = true

//        // Navigate to the next activity
//        val intent = Intent(this, ExperienceWithFitnessActivity::class.java)
//        startActivity(intent)

        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to the next activity
            val intent = Intent(this, ExperienceWithFitnessActivity::class.java)
            startActivity(intent)
        }, 1000)
    }
}
