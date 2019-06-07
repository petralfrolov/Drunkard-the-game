package com.example.drunkard

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.game_field.*

class GameField : AppCompatActivity() {
    var game = Drunkgame()
    fun OnClickStartTurn(view: View) {
        /*
        Log.d("Check", game.yourCard.GetCardText())
        Log.d("Check", yourCard.text.toString())
        */



        //Thread.sleep(100)
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
        yourCard.text = "Your card: " + game.yourCard.GetCardText()
        yourDeck.text = "Your deck: " + game.GetYourDeckSize().toString()
        enemyCard.text = "Enemy's card: " + game.enemyCard.GetCardText()
        enemyDeck.text = "Enemy's deck: " + game.GetEnemyDeckSize().toString()

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
