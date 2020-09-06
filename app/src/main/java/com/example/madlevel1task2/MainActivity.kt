package com.example.madlevel1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.madlevel1task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var editTexts: List<EditText>

    /**
     * I know that this way of hard coding it is not as nice as soft coding it to compare the T/F values on its own. But this should be fine
     * given that the app is very simple and definitive (not like a production app where it would matter more for future's sake).
     */
    val correctAnswers = listOf(
        true,
        false,
        false,
        false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener {
            onBtnSubmitClick()
        }
        editTexts = listOf(
            binding.et1,
            binding.et2,
            binding.et3,
            binding.et4
        )
    }

    private fun onBtnSubmitClick() {
        var score = 0

        // Loop through editText-inputs
        for(i in editTexts.indices) {
            // This bit is about translating user inputted "T" | "F" string values to booleans true | false
            val editTextValue = editTexts[i].text.toString()
            val translated = when (editTextValue) {
                getString(R.string.text_true) -> true
                getString(R.string.text_false) -> false
                else -> {
                    Toast
                        .makeText(this, getString(R.string.text_all_answers_required), Toast.LENGTH_LONG)
                        .show()
                    return
                }
            }

            // Increment score if user filled in correct answer
            if (translated == correctAnswers[i]) score++
        }

        Toast.makeText(this, getString(R.string.text_correct_answers_count, score), Toast.LENGTH_LONG).show()
    }
}