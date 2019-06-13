package com.example.drunkard

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import kotlinx.android.synthetic.main.pve_game_field.*
import java.io.InputStream
import android.view.animation.TranslateAnimation
import android.R.attr.y
import android.R.attr.x
import android.graphics.Point
import android.util.Log
import android.view.Display
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation


class PVEGameField : AppCompatActivity() {
    var game = Drunkgame()

    lateinit var player: Player
    lateinit var buttonClickPlayer: Player

    fun loadImg(view: View, path: String) {
        var ims = applicationContext.assets.open(path)
        var img = Drawable.createFromStream(ims, null)
        (view as ImageView).setImageDrawable(img)
        view.refreshDrawableState()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pve_game_field)

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


        yourDeckSize.text = game.GetYourDeckSize().toString()
        enemyDeckSize.text = game.GetEnemyDeckSize().toString()

        loadImg(yourDeckView, "cards/back.png")
        loadImg(enemyDeckView, "cards/back.png")

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

        /*
        if (game.GetYourCurDeckSize() <= 0)
            yourCardView.setAlpha(0)
        if (game.GetEnemyCurDeckSize() <= 0)
            enemyCardView.setAlpha(0)
        */

        if (!game.Turn()) {
            var result = true
            if (game.GetYourDeckSize() <= 0) {
                result = false
            }

            player.stop()

            val moveIntent = Intent(this, PVEGameResults::class.java)
            moveIntent.putExtra("Result", result)
            startActivity(moveIntent)
        }

        loadImg(yourCardView, "cards/${game.yourCard.GetCardType()}/${game.yourCard.GetCardName()}.png")
        loadImg(enemyCardView, "cards/${game.enemyCard.GetCardType()}/${game.enemyCard.GetCardName()}.png")


        val animationYourMove = TranslateAnimation(
            -yourCardField.width.toFloat() + yourCardView.width,
            0f,
            yourCardField.height.toFloat() / 2 - yourCardView.height / 2,
            0f
        )
        animationYourMove.duration = 500
        animationYourMove.fillAfter = false

        val animationEnemyMove = TranslateAnimation(
            yourCardField.width.toFloat() - enemyCardView.width,
            0f,
            yourCardField.height.toFloat() / 2 - enemyCardView.height / 2,
            0f
        )
        animationEnemyMove.duration = 500
        animationEnemyMove.fillAfter = false

        yourCardView.startAnimation(animationYourMove)
        enemyCardView.startAnimation(animationEnemyMove)
    }

    fun toGameEndMenu(view: View) {
        player.stop()
        buttonClickPlayer.play()

        val moveIntent = Intent(this, PVEGameResults::class.java)
        moveIntent.putExtra("Result", false)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}
