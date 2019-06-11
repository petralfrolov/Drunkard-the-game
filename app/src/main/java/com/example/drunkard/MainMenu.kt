package com.example.drunkard

import android.app.ActivityOptions
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.main_menu.*

class MainMenu : AppCompatActivity() {

    lateinit var player : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Create", "OnCreate occured")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        var muted = intent.getBooleanExtra("Muted", false)
        player = Player(this, R.raw.main_menu_theme)
        player.muted = muted
        if (!player.muted) {
            player.play()
        }
        else {
            muteButton.setImageResource(R.drawable.muted)
        }

        playButton.setOnClickListener(::playButtonListener)
        muteButton.setOnClickListener(::muteButtonListener)
    }

    override fun onStart() {
        Log.d("Create", "OnStart occured")
        super.onStart()

        if (!player.muted) {
            player.play()
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onResume() {
        Log.d("Create", "OnResume occured")
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        if (!player.muted) {
            player.play()
        }
        else {
            muteButton.setImageResource(R.drawable.muted)
        }

        playButton.setImageResource(R.drawable.play)

    }

    fun playButtonListener(view: View){
        playButton.setImageResource(R.drawable.play_pressed)
        view.refreshDrawableState()
        player.stop()

        val moveIntent = Intent (this, ModeSelect::class.java)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    fun muteButtonListener(view: View){
        if (!player.muted){
            muteButton.setImageResource(R.drawable.muted)
            view.refreshDrawableState()
            player.muted = true
            player.pause()
        }
        else{
            muteButton.setImageResource(R.drawable.unmuted)
            view.refreshDrawableState()
            player.muted = false
            player.play()
        }
    }
}