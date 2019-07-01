package com.example.drunkard

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.pve_game_field.*

class Animator(){
    final var COEF : Float = 75/100.toFloat()
    fun animateToTable(CardView : ImageView, DeckView : ImageView, xTo : Int, yTo : Int){
        CardView.scaleX = COEF.toFloat()
        CardView.scaleY = COEF.toFloat()

        CardView.x = DeckView.x
        CardView.y = DeckView.y

        CardView.animate()
            .translationX(xTo.toFloat())
            .translationY(yTo.toFloat())
            .setDuration(500)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationStart(animation: Animator) {
                    CardView.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(500)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                CardView.translationX = xTo.toFloat()
                                CardView.translationY = yTo.toFloat()

                                CardView.scaleX = 1f
                                CardView.scaleY = 1f
                            }
                        })
                }
            })
    }

}