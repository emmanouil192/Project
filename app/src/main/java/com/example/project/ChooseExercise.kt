package com.example.project

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Scroller
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.exe_model.ExeAdapter
import kotlinx.android.synthetic.main.activity_exercise_preview.*

class ChooseExercise : AppCompatActivity(), ClickListener {
    lateinit var exe_spinner: Spinner
    private lateinit var exeAdapter: ExeAdapter
    private lateinit var exeView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_exercise)
        val chest_list = arrayOf("BenchPress", "Dumbel Press", "Incl Dumbel Press")
        val back_list = arrayOf("Pullup", "Rowing", "Chinup")
        val legs_list = arrayOf("Jump Squats", "Deadlift", "Box Squat")

        val ch_exe_list = listOf(listOf("BenchPress", "Dumbel Press", "Incl Dumbel Press"),
                                 listOf("Lateral Raise", "Overhead Press", "Dumbel Shoulder Push Press"),
                                 listOf("Plank", "Sit ups", "Butterfly Sit up"),
                                 listOf("Jump Squats", "Deadlift", "Box Squat"),
                                 listOf("Pullup", "Rowing", "Chinup"))


        exe_spinner = findViewById(R.id.exe_spinner_layout)
        exeAdapter = ExeAdapter(mutableListOf(), this)
        exeView = findViewById(R.id.choose_exercises)
        exeView.adapter = exeAdapter
        exeView.layoutManager = LinearLayoutManager(this)

        val scr: Scroller


        val adapter = ArrayAdapter.createFromResource(this, R.array.types_of_exercise, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
        exe_spinner.adapter = adapter
        exe_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                println("Hello this returned")
                println(p2)
                exeAdapter.clear()
                for (e in ch_exe_list[p2])
                        exeAdapter.addExercise(e)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {

            val returnIntent = Intent()
            returnIntent.putExtra("exercise_name", result.data?.getStringExtra("exercise_name"))
            returnIntent.putExtra("exercise_reps", result.data?.getStringExtra("exercise_reps"))
            returnIntent.putExtra("exercise_rest", result.data?.getStringExtra("exercise_rest"))
            println("All good result OK")
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }

    override fun onClick(exe_name: String) {
        val intent = Intent(this, ExercisePreviewActivity::class.java)
        intent.putExtra("exercise_name", exe_name)
        resultLauncher.launch(intent)
    }
}
