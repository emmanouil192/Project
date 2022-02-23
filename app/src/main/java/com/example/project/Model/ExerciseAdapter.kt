package com.example.project.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R

class ExerciseAdapter(
    private val exercises: MutableList<ExerciseModel>
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    class AdditionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        println("My view type is")
        println(viewType)
        return ExerciseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.exercise_layout,
                parent,
                false
            )
        )
    }

    fun addExercise(exe: ExerciseModel) {
        println("before")
        exercises.add(exe)
        println("after")
        notifyItemInserted(exercises.size - 1)
        println("after2")
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return exercises.size
    }
}