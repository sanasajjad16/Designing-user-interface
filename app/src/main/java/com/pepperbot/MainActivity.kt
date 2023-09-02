package com.pepperbot

import android.graphics.Color
import android.graphics.drawable.ShapeDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.design.activity.RobotActivity

class MainActivity : RobotActivity(),RobotLifecycleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        QiSDK.register(this,this)


    }



    override fun onRobotFocusGained(qiContext: QiContext?) {
//        val say: Say = SayBuilder.with(qiContext) // Create the builder with the context.
//            .withText("Hello human!") // Set the text to say.
//            .build() // Build the say action.
//
//        // Execute the action.
//        say.run()
    }

    override fun onRobotFocusLost() {

    }

    override fun onRobotFocusRefused(reason: String?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        QiSDK.unregister(this)
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        val builder = AlertDialog.Builder(this)
        val shapeDrawable = ShapeDrawable()
        val paint = shapeDrawable.paint
        paint.setShadowLayer(10f, 10f, 10f, Color.GRAY)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.exit_custom_dialog, null)
        builder.setView(view)
        view.setBackgroundDrawable(shapeDrawable)
        val dialog: AlertDialog = builder.create()
        val yes : Button=view.findViewById(R.id.Yes)
        val cancel : Button=view.findViewById(R.id.Cancel)
        yes.setOnClickListener {
            finishAffinity()
        }
        cancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
        val window = dialog.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


    }
}