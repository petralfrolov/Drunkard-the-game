package com.example.drunkard

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.main_menu.*

class MainMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
    }

    fun move(view: View){
        imageView2.setImageResource(R.drawable.play_pressed)
        view.refreshDrawableState()
        val moveIntent = Intent (this, ModeSelect::class.java)
        startActivity(moveIntent)
        //imageView2.setImageResource(R.drawable.play)
    }
}