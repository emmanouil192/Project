package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.project.exe_model.ExerciseModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val gson : Gson = Gson()
        val json = intent.getStringExtra("my_json")
        println("Inside second activity")
        println(json)
        val type = object: TypeToken<MutableList<ExerciseModel>>() {}.type
        val LoadList : MutableList<ExerciseModel> = gson.fromJson(json, type)

        var exenum = 0
        exe_name.text = LoadList[exenum].exercise_name
        exe_time.text = LoadList[exenum].exercise_count

        NextButton.setOnClickListener {
            exenum++
            exe_name.text = LoadList[exenum].exercise_name
            exe_time.text = LoadList[exenum].exercise_count
        }

        PrevButton.setOnClickListener {
            exenum--
            exe_name.text = LoadList[exenum].exercise_name
            exe_time.text = LoadList[exenum].exercise_count
        }
    }
}