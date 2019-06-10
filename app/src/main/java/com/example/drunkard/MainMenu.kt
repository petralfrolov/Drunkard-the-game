package com.example.drunkard

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.main_menu.*

class MainMenu : AppCompatActivity() {

    lateinit var player : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        player = MediaPlayer.create(this, R.raw.main_music)
        player.isLooping = true
        player.setVolume(0.5f, 0.5f)
        player.start()

        playButton.setOnClickListener(::playButtonListener)
    }


    override fun onResume() {
        super.onResume()
        playButton.setImageResource(R.drawable.play)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    fun playButtonListener(view: View){
        playButton.setImageResource(R.drawable.play_pressed)
        view.refreshDrawableState()

        val moveIntent = Intent (this, ModeSelect::class.java)
        player.stop()
        startActivity(moveIntent)
    }
}