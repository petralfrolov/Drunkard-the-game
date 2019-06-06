package com.example.drunkard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.drunkard.R

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }
    fun movemenu(view: View){
        val moveIntent = Intent (this, MainActivity::class.java)
        startActivity(moveIntent)
        val toast = Toast.makeText(this,"Игра окончена",Toast.LENGTH_SHORT)
        toast.show()
    }
}