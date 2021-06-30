package com.example.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService

class TiltView(context: Context?): View(context) {
    lateinit var vibrator : Vibrator

    private val greenPaint: Paint = Paint()
    private val blackPaint: Paint = Paint()

    private var cX:Float = 0f
    private var cY:Float = 0f

    private var xCoord:Float = 0f
    private var yCoord:Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w/2f
        cY = h/2f
    }

    init {
        greenPaint.color=Color.GREEN
        blackPaint.style=Paint.Style.STROKE
    }

    fun onSensorEvent(event:SensorEvent){
        yCoord = event.values[0]*20
        xCoord = event.values[1]*20

        invalidate()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas?) {
        vibrator = this.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect = VibrationEffect.createOneShot(3000,100)

        canvas?.drawCircle(cX,cY,100f,blackPaint)
        canvas?.drawCircle(cX+xCoord,cY+yCoord,100f,greenPaint)

        //canvas?.drawRect(cX-50f,cY-50f,cX+50f,cY+50f,blackPaint)
        //canvas?.drawRect(cX+xCoord-50f,cY+yCoord-50f,cX+xCoord+50f,cY+yCoord+50f,greenPaint)
        canvas?.drawLine(cX-20,cY,cX+20,cY,blackPaint)
        canvas?.drawLine(cX,cY-20,cX,cY+20,blackPaint)

        if(cX+xCoord>cX+100f){
            vibrator.vibrate(vibrationEffect);
        }else if(cY+yCoord>cY+100f){
            vibrator.vibrate(vibrationEffect);
        }


        /*--
        if(cX+xCoord-50f<cX-50f){
            vibrator.vibrate(vibrationEffect);
        }else if(cY+yCoord-50f>cY-50f){
            vibrator.vibrate(vibrationEffect);
        }else if(cX+xCoord+50f>cX+50f){
            vibrator.vibrate(vibrationEffect);
        }else if(cY+yCoord+50f<cY+50f){
            vibrator.vibrate(vibrationEffect);
        }

         */



    }


}