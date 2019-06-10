package com.example.drunkard

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.AssetManager
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
    lateinit var player : MediaPlayer

    fun loadImg(view : View, path : String){
        var ims = applicationContext.assets.open(path)
        var img = Drawable.createFromStream(ims, null)
        (view as ImageView).setImageDrawable(img)
        yourCardView.refreshDrawableState()
    }

    fun OnClickStartTurn(view: View) {
        var ims: InputStream
        var card : Drawable


        yourDeckSize.text = "Your deck: " + game.GetYourDeckSize().toString()
        enemyDeckSize.text = "Enemy's deck: " + game.GetEnemyDeckSize().toString()

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

        player = MediaPlayer.create(this, R.raw.gayme)
        player.isLooping = true
        player.setVolume(0.5f, 0.5f)
        player.start()

        loadImg(enemyDeck1, "cards/back.png")
        loadImg(yourDeck1, "cards/back.png")

        yourDeckSize.text = "Your deck: " + game.GetYourDeckSize().toString()
        enemyDeckSize.text = "Enemy's deck: " + game.GetEnemyDeckSize().toString()

        turn.setOnClickListener(::OnClickStartTurn)
        surrender.setOnClickListener(::returnToMenu)
    }

    override fun onResume() {
        super.onResume()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }


    fun returnToMenu(view: View) {
        val moveIntent = Intent(this, MainMenu::class.java)
        player.stop()
        startActivity(moveIntent)
        //val toast = Toast.makeText(this,"Game over\ngit gud",Toast.LENGTH_SHORT)
        //toast.show()
    }
}
