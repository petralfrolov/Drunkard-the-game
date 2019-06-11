package com.example.drunkard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.game_resutls.*

class GameResults : AppCompatActivity() {
    lateinit var player : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_resutls)

        var muted = intent.getBooleanExtra("Muted", false)
        player = Player(this, R.raw.game_theme)
        player.muted = muted
        player.play()

        if (intent.hasExtra("Result"))
            gameEndTextView.text = "You " + intent.getStringExtra("Result")

        toMenuButton.setOnClickListener(::toMenu)
    }

    override fun onStart() {
        super.onStart()

        player.play()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onResume() {
        super.onResume()

        player.play()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onPause() {
        super.onPause()

        player.stop()
    }

    fun toMenu(view : View) {
        val moveIntent = Intent(this, MainMenu::class.java)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
