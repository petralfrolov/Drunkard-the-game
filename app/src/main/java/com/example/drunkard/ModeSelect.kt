package com.example.drunkard

import android.content.Intent
import android.graphics.Point
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.mode_select.*


class ModeSelect : AppCompatActivity() {

    lateinit var player : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mode_select)

        var muted = intent.getBooleanExtra("Muted", false)
        player = Player(this, R.raw.prepare_music)
        player.muted = muted
        if (!player.muted) {
            player.play()
        }

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val x = size.x
        val y = size.y


        pveButton.setOnClickListener(::PvEgame)

    }

    override fun onRestart() {
        super.onRestart()

        if (!player.muted) {
            player.play()
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onResume() {
        super.onResume()

        if (!player.muted) {
            player.play()
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }


    override fun onPause() {
        super.onPause()

        player.stop()
    }

    fun PvEgame(view: View){
        player.stop()

        val moveIntent = Intent (this, GameField::class.java)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }
}
