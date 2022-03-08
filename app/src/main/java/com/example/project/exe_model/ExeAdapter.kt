package com.example.project.exe_model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project.ClickListener
import com.example.project.R
import kotlinx.android.synthetic.main.exercise.view.*
import kotlinx.android.synthetic.main.exercise_layout.view.*
import kotlinx.android.synthetic.main.exercise_layout.view.exename

class ExeAdapter(
    val exercises: MutableList<String>,
    val clickListener: ClickListener
) : RecyclerView.Adapter<ExeAdapter.ExerciseViewHolder>() {

    class ExerciseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.exercise,
                parent,
                false
            )
        )
    }

    fun addExercise(exe: String) {
        exercises.add(exe)
        notifyItemInserted(exercises.size - 1)
    }

    fun clear() {
        exercises.clear()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise: String = exercises[position]
        holder.itemView.exename.text = exercise
        holder.itemView.setOnClickListener {
            clickListener.onClick(exercise)
        }
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    fun retlist(): MutableList<String>{
        return exercises
    }
}


