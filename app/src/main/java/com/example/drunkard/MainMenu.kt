package com.example.drunkard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainMenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)
    }

    fun move(view: View){
        val moveIntent = Intent (this, ModeSelect::class.java)
        startActivity(moveIntent)
    }
}