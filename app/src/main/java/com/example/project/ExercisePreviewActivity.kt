package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_exercise_preview.*

class ExercisePreviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_preview)

        val exercise_name = intent.getStringExtra("exercise_name")
        exe_name.text = exercise_name

        SumbitButton.setOnClickListener {
            val returnIntent = Intent()

            returnIntent.putExtra("exercise_name", exercise_name)
            returnIntent.putExtra("exercise_reps", RepsInput.text.toString())
            returnIntent.putExtra("exercise_rest", RestInput.text.toString())
            println("I am passing")
            println(exercise_name)
            println(RepsInput.text.toString())
            println(RestInput.text.toString())
            if (RepsInput.text.toString() == "0")
                setResult(RESULT_CANCELED)
            else
                setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}