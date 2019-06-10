package com.example.drunkard

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.main_menu.*

class MainMenu : AppCompatActivity() {

    lateinit var player : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        player = Player(this, R.raw.main_menu_theme)
        player.play()

        playButton.setOnClickListener(::playButtonListener)
    }


    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        player.play()

        playButton.setImageResource(R.drawable.play)
    }

    fun playButtonListener(view: View){
        playButton.setImageResource(R.drawable.play_pressed)
        view.refreshDrawableState()
        player.stop()

        val moveIntent = Intent (this, ModeSelect::class.java)
        //moveIntent.putExtra("Player", player)
        startActivity(moveIntent)
    }
}