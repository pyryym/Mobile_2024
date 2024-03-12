package com.app.drawing_app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.app.drawing_app.MainActivity.Companion.paintBrush
import com.app.drawing_app.MainActivity.Companion.path
import android.os.Build
import androidx.activity.ComponentActivity

class Draw:View {

    // Layout params
    var params:ViewGroup.LayoutParams?=null

    // Object to hold paths, colors, the current brush color, and paint stroke thickness.
    companion object{
        var pathList= ArrayList<Path>()
        var colorList= ArrayList<Int>()
        var currentBrush=Color.BLACK
        var paintStroke:Float=5f
    }

    // Primary constructor
    constructor(context: Context) : this(context,null)
    {
        init()
    }

    // Secondary constructor
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)
    {
        init()
    }

    // Tertiary constructor
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    {
        init()
    }

    // Init paint brush setup
    private fun init(){

        paintBrush.isAntiAlias= true
        paintBrush.color= currentBrush
        paintBrush.style= Paint.Style.STROKE
        paintBrush.strokeJoin= Paint.Join.ROUND
        paintBrush.strokeWidth= paintStroke

        // Set the view layout parameters.
        params= ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)

    }

    // Handle touch screen events.
    override fun onTouchEvent(event: MotionEvent): Boolean {

        val x= event.x
        val y=event.y

        when(event.action){
            MotionEvent.ACTION_DOWN->{
                path.moveTo(x,y) // Moves to the coords where the touch event started.
                return true
            }
            MotionEvent.ACTION_MOVE->{
                path.lineTo(x,y) // Draws line to where the touch event has moved.
                pathList.add(path) // Add the current path to the path list.
                colorList.add(currentBrush) // Add the current color to the color list.
            }
            else->{
                return false
            }
        }
        postInvalidate()
        return false
    }

    // Function to draw the paths.
    override fun onDraw(canvas: Canvas) {
        for(i in pathList.indices){
            paintBrush.color = colorList[i]
            canvas.drawPath(pathList[i], paintBrush)
            invalidate()
        }
    }
}