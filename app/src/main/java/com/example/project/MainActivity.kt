package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.model.ExerciseAdapter
import com.example.project.model.ExerciseAdd
import com.example.project.model.ExerciseModel

class MainActivity : AppCompatActivity() {

    private lateinit var exeAdapter: ExerciseAdapter
    private lateinit var exeView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        exeAdapter = ExerciseAdapter(mutableListOf())
        exeView = findViewById(R.id.rvExercises)
        exeView.adapter = exeAdapter
        exeView.layoutManager = LinearLayoutManager(this)

        val exem = ExerciseModel("Hello","1234")
        val exadd = ExerciseAdd("Hello","1234")
        exeAdapter.addExercise(exem)
        exeAdapter.addExercise(exem)
        exeAdapter.addExercise(exem)
        exeAdapter.addExercise(exem)
        exeAdapter.addExercise(exadd)



    }
}