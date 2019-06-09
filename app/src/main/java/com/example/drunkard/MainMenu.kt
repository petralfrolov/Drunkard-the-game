package com.example.drunkard

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.main_menu.*

class MainMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        playButton.setOnClickListener(::playButtonListener)
    }

    override fun onResume() {
        super.onResume()
        playButton.setImageResource(R.drawable.play)
    }

    fun playButtonListener(view: View){
        playButton.setImageResource(R.drawable.play_pressed)
        view.refreshDrawableState()

        val moveIntent = Intent (this, ModeSelect::class.java)
        startActivity(moveIntent)
    }
}