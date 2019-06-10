package com.example.drunkard

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Point
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.mode_select.*
import android.view.Display



class ModeSelect : AppCompatActivity() {

    lateinit var player : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mode_select)

        player = MediaPlayer.create(this, R.raw.main_music2)
        player.isLooping = true
        player.setVolume(0.5f, 0.5f)
        player.start()

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val x = size.x
        val y = size.y


        pveButton.setOnClickListener(::PvEgame)

    }

    override fun onResume() {
        super.onResume()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    fun PvEgame(view: View){
        val moveIntent = Intent (this, GameField::class.java)
        player.stop()
        startActivity(moveIntent)
    }
}
