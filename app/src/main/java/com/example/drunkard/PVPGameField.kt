package com.example.drunkard

import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import kotlinx.android.synthetic.main.pve_game_field.*
import kotlinx.android.synthetic.main.pvp_game_field.*
import java.io.InputStream

class PVPGameField : AppCompatActivity() {
    var game = PVPDrunkGame()

    lateinit var player: Player
    lateinit var buttonClickPlayer: Player
    lateinit var animator : com.example.drunkard.Animator

    var player1Move = false
    var player2Move = false
    var moveResult = 0

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

        animator = com.example.drunkard.Animator()

        loadImg(DeckView1, "cards/back.png")
        loadImg(DeckView2, "cards/back.png")

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
        if (player1Move) {
            return
        }

        var ims: InputStream

        buttonClickPlayer.play()

        game.StartTurn1()
        DeckSize1.text = game.GetDeckSize1().toString()
        DeckSize2.text = game.GetDeckSize2().toString()

        loadImg(CardView1, "cards/${game.Card1.GetCardType()}/${game.Card1.GetCardName()}.png")
        CardView1.visibility = ImageView.VISIBLE

        animator.animateToTable(CardView1, DeckView1, 0, 0)

        player1Move = true
        if (player2Move){
            EndTurn()
        }
    }

    fun OnClickStartTurn2(view: View) {
        if (player2Move) {
            return
        }

        var ims: InputStream

        buttonClickPlayer.play()

        var pastDeck : Int = game.GetCurDeckSize2()
        game.StartTurn2()
        DeckSize1.text = game.GetDeckSize1().toString()
        DeckSize2.text = game.GetDeckSize2().toString()

        loadImg(CardView2, "cards/${game.Card2.GetCardType()}/${game.Card2.GetCardName()}.png")
        CardView2.visibility = ImageView.VISIBLE

        animator.animateToTable(CardView2, DeckView2, 0, 0)

        player2Move = true
        if (player1Move){
            EndTurn()
        }
    }

    fun EndTurn(){
        moveResult = game.EndTurn()

        if (moveResult == -1) {
            var result = 1
            if (game.GetDeckSize1() <= 0) {
                result = 2
            }

            player.stop()

            val moveIntent = Intent(this, PVPGameResults::class.java)
            moveIntent.putExtra("Result", result)
            startActivity(moveIntent)
        }
        else{
            // todo animations
            //Thread.sleep(2000) ???????????????????????????????????????????????????????????????????????
            CardView1.visibility = ImageView.INVISIBLE
            CardView2.visibility = ImageView.INVISIBLE
        }
        player1Move = false
        player2Move = false
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
