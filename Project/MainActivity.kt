package com.app.drawing_app

import android.os.Build
import androidx.activity.ComponentActivity
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.app.drawing_app.Draw.Companion.colorList
import com.app.drawing_app.Draw.Companion.paintStroke
import com.app.drawing_app.Draw.Companion.pathList


// Main entry point for the app.
class MainActivity : AppCompatActivity() {

    // Companion object to hold static references to the current Path and Paint objects.
    companion object{
        var path = Path() // Holds the current path drawn by the user.
        var paintBrush= Paint() // Holds the current paint brush settings.
    }

    // Vars for UI components.
    lateinit var colorRecyclerView: RecyclerView // Display Color options.
    lateinit var editFont:CardView // Thickness option.
    lateinit var colorArray:ArrayList<Int> // Arraylist for color options.
    lateinit var erasePaint:CardView // Eraser.

    // Called when the activity is starting.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set the content view to our layout.

        // Initialize UI components.
        colorRecyclerView= findViewById(R.id.color_recyclerView)
        erasePaint=findViewById(R.id.erase_paint)
        editFont=findViewById(R.id.edit_font)

        colorArray= ArrayList() // Init colorarray.


        // Populate colorArray with color resources.
        colorArray.add(R.color.white);
        colorArray.add(R.color.black);
        colorArray.add(R.color.pink);
        colorArray.add(R.color.red2);
        colorArray.add(R.color.orangeRed);
        colorArray.add(R.color.yellowOrange);
        colorArray.add(R.color.peach);
        colorArray.add(R.color.yellow2);
        colorArray.add(R.color.green);
        colorArray.add(R.color.green2);
        colorArray.add(R.color.cyan);
        colorArray.add(R.color.blue);
        colorArray.add(R.color.blue2);
        colorArray.add(R.color.violet2);
        colorArray.add(R.color.darkPurple);
        colorArray.add(R.color.purple);
        colorArray.add(R.color.purple2);

        // Set up RecyclerView with a LinearLayoutManager and an adapter.
        val adapter= ColorPicker(colorArray,this)
        val layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        colorRecyclerView.layoutManager=layoutManager
        colorRecyclerView.adapter=adapter

        // Set click listener for erasePaint, to clear the current drawing.
        erasePaint.setOnClickListener {
            pathList.clear()
            colorList.clear()
            path.reset()
        }

        // Set click listener for editFont, to open the settings for drawing thickness.
        editFont.setOnClickListener {

            val dialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.paint_stroke_layout)
            val first: CardView? = dialog.findViewById(R.id.first)
            val second: CardView? = dialog.findViewById(R.id.second)
            val third: CardView? = dialog.findViewById(R.id.third)
            val fourth: CardView? = dialog.findViewById(R.id.fourth)
            val fifth: CardView? = dialog.findViewById(R.id.fifth)


            dialog.show()

            // Set click listeners for thickness options.
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            first?.setOnClickListener {
                dialog.dismiss()
                paintStroke=5f
                paintBrush.strokeWidth= paintStroke
            }
            second?.setOnClickListener {
                dialog.dismiss()
                paintStroke=10f
                paintBrush.strokeWidth= paintStroke
            }
            third?.setOnClickListener {
                dialog.dismiss()
                paintStroke=15f
                paintBrush.strokeWidth= paintStroke
            }
            fourth?.setOnClickListener {
                dialog.dismiss()
                paintStroke=20f
                paintBrush.strokeWidth= paintStroke
            }
            fifth?.setOnClickListener {
                dialog.dismiss()
                paintStroke=25f
                paintBrush.strokeWidth= paintStroke

            }
        }
    }
}