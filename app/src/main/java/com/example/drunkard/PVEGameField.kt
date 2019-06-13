package com.example.drunkard

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.game_field.*
import java.io.InputStream
import android.R.anim


class PVEGameField : AppCompatActivity() {
    var game = Drunkgame()

    lateinit var player: Player
    lateinit var buttonClickPlayer: Player

    fun loadImg(view: View, path: String) {
        var ims = applicationContext.assets.open(path)
        var img = Drawable.createFromStream(ims, null)
        (view as ImageView).setImageDrawable(img)
        yourCardView.refreshDrawableState()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_field)

        var muted = intent.getBooleanExtra("Muted", false)
        player = Player(this, R.raw.game_theme)
        player.muted = muted
        player.play()

        buttonClickPlayer = Player(this, R.raw.click)
        buttonClickPlayer.muted = muted
        buttonClickPlayer.cancelLooping()

        /*
        val gifTable = GifDrawable(resources, R.drawable.anim)
        tableanim.setBackgroundResource()
        */

        loadImg(enemyDeck, "cards/back_turned.png")
        loadImg(yourDeck, "cards/back_turned.png")

        yourDeckSize.text = game.GetYourDeckSize().toString()
        enemyDeckSize.text = game.GetEnemyDeckSize().toString()

        turn.setOnClickListener(::OnClickStartTurn)
        surrender.setOnClickListener(::toGameEndMenu)
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

    fun OnClickStartTurn(view: View) {
        var ims: InputStream
        var card: Drawable

        buttonClickPlayer.play()

        yourDeckSize.text = game.GetYourDeckSize().toString()
        enemyDeckSize.text = game.GetEnemyDeckSize().toString()

        if (!game.Turn()) {
            var result = true
            if (game.GetYourDeckSize() <= 0) {
                result = false
            }

            player.stop()

            val moveIntent = Intent(this, GameResults::class.java)
            moveIntent.putExtra("Result", result)
            startActivity(moveIntent)
        }

        loadImg(yourCardView, "cards/${game.yourCard.GetCardType()}/${game.yourCard.GetCardName()}.png")
        loadImg(enemyCardView, "cards/${game.enemyCard.GetCardType()}/${game.enemyCard.GetCardName()}.png")

    }

    fun toGameEndMenu(view: View) {
        player.stop()
        buttonClickPlayer.play()

        val moveIntent = Intent(this, GameResults::class.java)
        moveIntent.putExtra("Result", "LOSE")
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
