package com.smart_egbuchulem.workout_crunch

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smart_egbuchulem.workout_crunch.databinding.ActivityMainGoalsBinding

class MainGoalsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainGoalsBinding
    private val selectedGoals = mutableListOf<CheckBox>()
    private lateinit var userAgeRange: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainGoalsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Receive the UserAgeRange from the previous activity
        userAgeRange = intent.getStringExtra("UserAgeRange") ?: "Not Specified"

        // Set the initial limitText message
        binding.limitText.text = getString(R.string.select_up_to_3)

        // Make content appear behind the status bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        // Optionally, set the status bar color to transparent for a seamless background
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.TRANSPARENT
        }

        binding.backButton.setOnClickListener { onBackPressed() }

        // Set up click listeners for checkboxes with a limit of 3 selections
        setupCheckBoxListener(binding.goalBuildMuscle)
        setupCheckBoxListener(binding.goalLoseWeight)
        setupCheckBoxListener(binding.goalImproveMobility)
        setupCheckBoxListener(binding.goalDevelopFlexibility)
        setupCheckBoxListener(binding.goalImproveOverallFitness)
        setupCheckBoxListener(binding.goalTryNewWorkouts)
        setupCheckBoxListener(binding.goalIncreaseEndurance)

        // Handle image click to move to the next activity (BodyGoalsActivity)
        binding.forwardButton.setOnClickListener {
            if (selectedGoals.isNotEmpty()) {
                navigateToBodyGoalsActivity()
            } else {
                Toast.makeText(this, "Please select at least one goal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupCheckBoxListener(checkBox: CheckBox) {
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                if (selectedGoals.size < 3) {
                    selectedGoals.add(checkBox)
                } else {
                    checkBox.isChecked = false
                    Toast.makeText(this, "You can only select up to 3 goals", Toast.LENGTH_SHORT).show()
                }
            } else {
                selectedGoals.remove(checkBox)
            }
            updateLimitText()
        }
    }

    private fun updateLimitText() {
        val remaining = 3 - selectedGoals.size
        binding.limitText.text = if (remaining == 0) {
            "You've selected the maximum of 3"
        } else {
            "Select up to $remaining more"
        }
    }

    private fun navigateToBodyGoalsActivity() {
        // Prepare the intent for BodyGoalsActivity
        val intent = Intent(this, BodyGoalsActivity::class.java).apply {
            putExtra("UserAgeRange", userAgeRange) // Passing the received UserAgeRange
            putExtra("SelectedGoals", selectedGoals.joinToString { it.text }) // Pass selected goals as a comma-separated string
        }
        startActivity(intent)
    }
}
