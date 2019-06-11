package com.example.drunkard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.game_resutls.*

class GameResults : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_resutls)
        var endIntent = intent
        if (endIntent.hasExtra("data_id"))
            gameEndTextView.text = "You " + endIntent.getStringExtra("data_id")
        toMenuButton.setOnClickListener(::toMenu)
        surrenderGameEndButton.setOnClickListener(::toMenuSurrender)
    }

    fun toMenu(view : View) {
        val moveIntent = Intent(this, MainMenu::class.java)
        startActivity(moveIntent)
    }

    fun toMenuSurrender(view: View) {
        val moveIntent = Intent(this, MainMenu::class.java)
        startActivity(moveIntent)
        val toast = Toast.makeText(this, "YOU LOSE", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onStart() {
        super.onStart()

        /*if (!player.muted) {
            player.play()
        }*/

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }
}
