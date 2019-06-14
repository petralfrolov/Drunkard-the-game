package com.example.drunkard

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import kotlinx.android.synthetic.main.pvp_game_field.*
import java.io.InputStream

class PVPGameField : AppCompatActivity() {
    var game = PVPDrunkGame()

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
        setContentView(R.layout.pvp_game_field)

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

        loadImg(Deck1, "cards/back.png")
        loadImg(Deck2, "cards/back.png")

        DeckSize1.text = game.GetDeckSize1().toString()
        DeckSize2.text = game.GetDeckSize2().toString()

        turn1.setOnClickListener(::OnClickStartTurn1)
        turn2.setOnClickListener(::OnClickStartTurn2)
        surrender1.setOnClickListener(::toGameEndMenu1)
        surrender2.setOnClickListener(::toGameEndMenu2)
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

    fun OnClickStartTurn1(view: View) {
        var ims: InputStream
        var card: Drawable

        buttonClickPlayer.play()

        /*
        if (game.GetCurDeckSize1() <= 0)
            CardView1.setAlpha(0)
        if (game.GetCurDeckSize2() <= 0)
            CardView2.setAlpha(0)
        */
        var pastDeck : Int = game.GetCurDeckSize1()
        game.StartTurn1()
        DeckSize1.text = game.GetDeckSize1().toString()
        DeckSize2.text = game.GetDeckSize2().toString()
        if (!game.EndTurn()) {
            var result = true
            if (game.GetDeckSize1() <= 0) {
                result = false
            }

            player.stop()

            val moveIntent = Intent(this, PVPGameResults::class.java)
            moveIntent.putExtra("Result", result)
            startActivity(moveIntent)
        }

        DeckSize1.text = game.GetDeckSize1().toString()
        DeckSize2.text = game.GetDeckSize2().toString()

        /*
        if (game.GetCurDeckSize1() > 0)
            CardView1.setAlpha(255)
        if (game.GetCurDeckSize2() > 0)
            CardView2.setAlpha(255)
        */

        loadImg(CardView1, "cards/${game.Card1.GetCardType()}/${game.Card1.GetCardName()}.png")
        if (pastDeck < game.GetCurDeckSize1()) {
            val animationMove = TranslateAnimation(
                -CardField1.width.toFloat() + CardView1.width,
                0f,
                CardField1.height.toFloat() / 2 - CardView1.height / 2,
                0f
            )
            animationMove.duration = 500
            animationMove.fillAfter = false
            CardView1.startAnimation(animationMove)

        }

        //loadImg(enemyCardView, "cards/${game.enemyCard.GetCardType()}/${game.enemyCard.GetCardName()}.png")

    }

    fun OnClickStartTurn2(view: View) {
        var ims: InputStream
        var card: Drawable
        buttonClickPlayer.play()

        /*
        if (game.GetCurDeckSize1() <= 0)
            CardView1.setAlpha(0)
        if (game.GetCurDeckSize2() <= 0)
            CardView2.setAlpha(0)
        */
        var pastDeck : Int = game.GetCurDeckSize2()
        game.StartTurn2()
        DeckSize1.text = game.GetDeckSize1().toString()
        DeckSize2.text = game.GetDeckSize2().toString()
        if (!game.EndTurn()) {
            var result = true
            if (game.GetDeckSize1() <= 0) {
                result = false
            }

            player.stop()

            val moveIntent = Intent(this, PVPGameResults::class.java)
            moveIntent.putExtra("Result", result)
            startActivity(moveIntent)
        }
        DeckSize1.text = game.GetDeckSize1().toString()
        DeckSize2.text = game.GetDeckSize2().toString()

        /*
        if (game.GetCurDeckSize1() > 0)
            CardView1.setAlpha(255)
        if (game.GetCurDeckSize2() > 0)
            CardView2.setAlpha(255)
        */

        //loadImg(CardView1, "cards/${game.Card1.GetCardType()}/${game.Card1.GetCardName()}.png")
        loadImg(CardView2, "cards/${game.Card2.GetCardType()}/${game.Card2.GetCardName()}.png")
        if (pastDeck < game.GetCurDeckSize2()) {
            val animationMove = TranslateAnimation(
                CardField1.width.toFloat() - CardView2.width,
                0f,
                CardField1.height.toFloat() / 2 - CardView2.height / 2,
                0f
            )
            animationMove.duration = 500
            animationMove.fillAfter = false

            CardView2.startAnimation(animationMove)
        }
    }

    fun toGameEndMenu1(view: View) {
        player.stop()
        buttonClickPlayer.play()

        val moveIntent = Intent(this, PVPGameResults::class.java)
        moveIntent.putExtra("Result", false)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    fun toGameEndMenu2(view: View) {
        player.stop()
        buttonClickPlayer.play()

        val moveIntent = Intent(this, PVPGameResults::class.java)
        moveIntent.putExtra("Result", true)
        moveIntent.putExtra("Muted", player.muted)
        startActivity(moveIntent)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

}
