package com.example.drunkard

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
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
    fun OnClickStartTurn(view: View) {
        yourDeck.text = "Your deck: " + game.GetYourDeckSize().toString()
        enemyDeck.text = "Enemy's deck: " + game.GetEnemyDeckSize().toString()

        if (!game.Turn()) {
            if (game.GetYourDeckSize() <= 0) {
                val moveIntent = Intent (this, MainMenu::class.java)
                startActivity(moveIntent)
                val toast = Toast.makeText(this,"Game over",Toast.LENGTH_SHORT)
                toast.show()
            }
            else {
                val moveIntent = Intent (this, MainMenu::class.java)
                startActivity(moveIntent)
                val toast = Toast.makeText(this,"Game win",Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        //Log.d("path", "cards/${game.yourCard.GetCardType()}/${game.yourCard.GetCardName()}.png")
        var ims = applicationContext.assets.open("cards/${game.yourCard.GetCardType()}/${game.yourCard.GetCardName()}.png")
        var card = Drawable.createFromStream(ims, null)
        yourCardView.setImageDrawable(card)
        yourCardView.refreshDrawableState()



        ims = applicationContext.assets.open("cards/${game.enemyCard.GetCardType()}/${game.enemyCard.GetCardName()}.png")
        card = Drawable.createFromStream(ims, null)
        enemyCardView.setImageDrawable(card)
        enemyCardView.refreshDrawableState()



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_field)


        yourDeck.text = "Your deck: " + game.GetYourDeckSize().toString()
        enemyDeck.text = "Enemy's deck: " + game.GetEnemyDeckSize().toString()

        turn.setOnClickListener(::OnClickStartTurn)
        surrender.setOnClickListener(::returnToMenu)
    }

    fun returnToMenu(view: View){
        val moveIntent = Intent (this, MainMenu::class.java)
        startActivity(moveIntent)
        //val toast = Toast.makeText(this,"Game over\ngit gud",Toast.LENGTH_SHORT)
        //toast.show()
    }
}
