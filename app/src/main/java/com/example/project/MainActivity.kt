package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.model.ExerciseAdapter
import com.example.project.model.ExerciseModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var exeAdapter: ExerciseAdapter
    private lateinit var exeView: RecyclerView
    private lateinit var button: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        exeAdapter = ExerciseAdapter(mutableListOf())
        exeView = findViewById(R.id.rvExercises)
        exeView.adapter = exeAdapter
        exeView.layoutManager = LinearLayoutManager(this)

        val exem = ExerciseModel("Exercise1","15")

        button = findViewById(R.id.addExercises)
        button.setOnClickListener{
            exeAdapter.addExercise(exem)
        }


    }
}