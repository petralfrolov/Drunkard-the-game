package com.example.drunkard

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.game_field.*
import java.io.InputStream

class GameField : AppCompatActivity() {
    var game = Drunkgame()
    lateinit var player : Player

    fun loadImg(view : View, path : String){
        var ims = applicationContext.assets.open(path)
        var img = Drawable.createFromStream(ims, null)
        (view as ImageView).setImageDrawable(img)
        yourCardView.refreshDrawableState()
    }

    fun OnClickStartTurn(view: View) {
        var ims: InputStream
        var card : Drawable


        yourDeckSize.text = game.GetYourDeckSize().toString()
        enemyDeckSize.text = game.GetEnemyDeckSize().toString()

        if (!game.Turn()) {
            if (game.GetYourDeckSize() <= 0) {
                val moveIntent = Intent(this, MainMenu::class.java)
                player.stop()
                startActivity(moveIntent)
                val toast = Toast.makeText(this, "Game over", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val moveIntent = Intent(this, MainMenu::class.java)
                player.stop()
                startActivity(moveIntent)
                val toast = Toast.makeText(this, "Game win", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        //Log.d("path", "cards/${game.yourCard.GetCardType()}/${game.yourCard.GetCardName()}.png")
        loadImg(yourCardView,"cards/${game.yourCard.GetCardType()}/${game.yourCard.GetCardName()}.png")

        loadImg(enemyCardView,"cards/${game.enemyCard.GetCardType()}/${game.enemyCard.GetCardName()}.png")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_field)

        var muted = intent.getBooleanExtra("Muted", false)
        player = Player(this, R.raw.game_theme)
        player.muted = muted
        if (!player.muted) {
            player.play()
        }
        loadImg(enemyDeck, "cards/back_turned.png")
        loadImg(yourDeck, "cards/back_turned.png")

        yourDeckSize.text = game.GetYourDeckSize().toString()
        enemyDeckSize.text = game.GetEnemyDeckSize().toString()

        turn.setOnClickListener(::OnClickStartTurn)
        surrender.setOnClickListener(::returnToMenu)
    }

    override fun onStart() {
        super.onStart()

        if (!player.muted) {
            player.play()
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onResume() {
        super.onResume()

        if (!player.muted) {
            player.play()
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onPause() {
        super.onPause()

        player.stop()
    }


    fun returnToMenu(view: View) {
        player.stop()

        val moveIntent = Intent(this, MainMenu::class.java)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }
}
