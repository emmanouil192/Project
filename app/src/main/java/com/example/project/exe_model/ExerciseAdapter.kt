package com.example.project.exe_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.R
import kotlinx.android.synthetic.main.exercise_layout.view.*

class ExerciseAdapter(
    val exercises: MutableList<ExerciseModel>
) : RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.exercise_layout,
                parent,
                false
            )
        )
    }

    fun addExercise(exe: ExerciseModel) {
        exercises.add(exe)
        notifyItemInserted(exercises.size - 1)
    }

    fun clear() {
        exercises.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise: ExerciseModel = exercises[position]
        holder.itemView.exename.text = exercise.exercise_name
        holder.itemView.execount.text = exercise.exercise_count

    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    fun retlist(): MutableList<ExerciseModel>{
        return exercises
    }
}