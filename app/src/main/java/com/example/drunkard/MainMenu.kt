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
    lateinit var buttonClickPlayer : Player


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("Create", "OnCreate occured")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        playButton.setOnClickListener(::playButtonListener)
        muteButton.setOnClickListener(::muteButtonListener)
    }

    override fun onStart() {
        Log.d("Create", "OnStart occured")
        super.onStart()

        // Background music player create
        var muted = intent.getBooleanExtra("Muted", false)
        player = Player(this, R.raw.main_menu_theme)
        player.muted = muted
        player.play()

        // Button click tune player create
        buttonClickPlayer = Player(this, R.raw.click)
        buttonClickPlayer.muted = muted
        buttonClickPlayer.cancelLooping()

        if (player.muted){
            muteButton.setImageResource(R.drawable.muted)
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onResume() {
        Log.d("Create", "OnResume occured")
        super.onResume()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()

        playButton.setImageResource(R.drawable.play)
    }

    override fun onPause() {
        super.onPause()

        player.stop()
    }

    fun playButtonListener(view: View){
        playButton.setImageResource(R.drawable.play_pressed)
        view.refreshDrawableState()

        player.stop()
        buttonClickPlayer.play()

        val moveIntent = Intent (this, ModeSelect::class.java)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    fun muteButtonListener(view: View){
        if (!player.muted){
            muteButton.setImageResource(R.drawable.muted)
            view.refreshDrawableState()

            player.muted = true
            buttonClickPlayer.muted = true

            player.pause()
        }
        else{
            muteButton.setImageResource(R.drawable.unmuted)
            view.refreshDrawableState()

            player.muted = false
            buttonClickPlayer.muted = false

            buttonClickPlayer.play()
            player.play()
        }
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}