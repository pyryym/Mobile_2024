package com.app.drawing_app

import android.content.Context
import android.graphics.Path
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.drawing_app.MainActivity.Companion.paintBrush
import com.app.drawing_app.MainActivity.Companion.path
import com.app.drawing_app.Draw.Companion.currentBrush
import android.os.Build
import androidx.activity.ComponentActivity


// Class to handle the color selection for drawing
class ColorPicker(val list: ArrayList<Int>, val context: Context):RecyclerView.Adapter<ColorPicker.ViewHolder>() {

    // Class that holds the UI components
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val selectColorImage:ImageView=itemView.findViewById(R.id.select_color_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.select_color_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem= list[position]
        holder.selectColorImage.setImageResource(currentItem)
        holder.selectColorImage.setOnClickListener {
            paintBrush.color=context.getColor(currentItem)
            currentColor(paintBrush.color)
        }


    }

    // Size of color list.
    override fun getItemCount(): Int {
        return list.size
    }

    // Update the current pen color and reset path.
    private fun currentColor(color:Int){
        currentBrush=color
        path= Path()
    }
}