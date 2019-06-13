package com.example.drunkard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.pve_game_resutls.*
import kotlinx.android.synthetic.main.pvp_game_results.*

class PVPGameResults : AppCompatActivity() {

    lateinit var player : Player
    lateinit var buttonClickPlayer : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pvp_game_results)
        var result = intent.getBooleanExtra("Result", false)
        var muted = intent.getBooleanExtra("Muted", false)

        var source : Int
        if (result) {
            // win case
            gameEndTextView1.setText("You win!")
            gameEndTextView2.setText("You lose")
            source = R.raw.win_theme
        }
        else{
            // lose case
            gameEndTextView1.setText("You lose")
            gameEndTextView2.setText("You win!")
            source = R.raw.defeat_theme
        }
        player = Player(this, source)
        player.muted = muted
        player.cancelLooping()
        player.play()

        buttonClickPlayer = Player(this, R.raw.click)
        buttonClickPlayer.muted = muted
        buttonClickPlayer.cancelLooping()

        toMenuButtonPVP.setOnClickListener(::toMenu)
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
        buttonClickPlayer.play()

        val moveIntent = Intent(this, MainMenu::class.java)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
