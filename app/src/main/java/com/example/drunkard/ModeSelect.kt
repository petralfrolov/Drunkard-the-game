package com.example.drunkard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.mode_select.*


class ModeSelect : AppCompatActivity() {

    lateinit var player : Player
    lateinit var buttonClickPlayer : Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mode_select)

        var muted = intent.getBooleanExtra("Muted", false)
        player = Player(this, R.raw.prepare_music)
        player.muted = muted
        player.play()

        buttonClickPlayer = Player(this, R.raw.click)
        buttonClickPlayer.muted = muted
        buttonClickPlayer.cancelLooping()

        pveButton.setOnClickListener(::PvEgame)
        pvpButton.setOnClickListener(::PvPgame)
    }

    override fun onRestart() {
        super.onRestart()

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

    fun PvEgame(view: View) {
        player.stop()
        buttonClickPlayer.play()

        val moveIntent = Intent(this, PVEGameField::class.java)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    fun PvPgame(view: View) {
        player.stop()
        buttonClickPlayer.play()

        val moveIntent = Intent(this, PVPGameField::class.java)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
