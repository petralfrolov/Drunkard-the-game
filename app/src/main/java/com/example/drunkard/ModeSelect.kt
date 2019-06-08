package com.example.drunkard

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.mode_select.*
import android.view.Display



class ModeSelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mode_select)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        /* Не пашет
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val x = size.x
        val y = size.y

        pvpButton.height = y / 2
        pvpButton.refreshDrawableState()
        pveButton.refreshDrawableState()
        */

        pveButton.setOnClickListener(::PvEgame)

    }
    fun PvEgame(view: View){
        val moveIntent = Intent (this, GameField::class.java)
        startActivity(moveIntent)
    }
}
