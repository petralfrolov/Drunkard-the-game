package com.example.drunkard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ModeSelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mode_select)
    }
    fun movepvp(view: View){
        val moveIntent = Intent (this, GameField::class.java)
        startActivity(moveIntent)
    }
}
