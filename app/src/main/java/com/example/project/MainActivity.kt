package com.example.project

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.exe_model.ExerciseAdapter
import com.example.project.exe_model.ExerciseModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var exeAdapter: ExerciseAdapter
    private lateinit var exeView: RecyclerView
    private lateinit var button: FloatingActionButton

    fun Store() {
        val sharedPreferences :SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor :SharedPreferences.Editor = sharedPreferences.edit()
        val gson : Gson = Gson()
        val json : String = gson.toJson(exeAdapter.exercises)
        println("HI this is my json")
        println(json)
        editor.putString("exercise_list", json)
        editor.apply()
    }

    fun Load() {
        val sharedPreferences :SharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val gson :Gson = Gson()
        val json = sharedPreferences.getString("exercise_list",null)
        println("HI this is my json")
        println(json)
        val type = object: TypeToken<MutableList<ExerciseModel>>() {}.type
        val LoadList : MutableList<ExerciseModel> = gson.fromJson(json, type)
        exeAdapter.clear()
        for (exe in LoadList) {
            exeAdapter.addExercise(exe)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        exeAdapter = ExerciseAdapter(mutableListOf())
        exeView = findViewById(R.id.rvExercises)
        exeView.adapter = exeAdapter
        exeView.layoutManager = LinearLayoutManager(this)


        var count = 0


        StoreButton.setOnClickListener {
            Store()
        }

        LoadButton.setOnClickListener {
            Load()
        }

        var mresultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            println("We returned")
            println(result)
            if (result.resultCode == RESULT_OK) {
                println("All good result OK")
                println(result.data)
                //val exe = ExerciseModel("NAME", "15")
                var exe = ExerciseModel(result.data?.getStringExtra("exercise_name")!!, result.data?.getStringExtra("exercise_rest")!!)
                exeAdapter.addExercise(exe)
            } else {
                println("Result to main failed")
            }
        }
        button = findViewById(R.id.addExercises)
        button.setOnClickListener{
            val intent = Intent(this, ChooseExercise::class.java)
            mresultLauncher.launch(intent)

        }
        StartButton.setOnClickListener {
            val gson : Gson = Gson()
            val json : String = gson.toJson(exeAdapter.exercises)
            val intent = Intent(this, ExerciseActivity::class.java).apply {
                putExtra("my_json", json)
            }

            startActivity(intent)
        }

    }
}