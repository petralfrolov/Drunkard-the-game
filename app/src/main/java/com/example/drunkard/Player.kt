package com.example.drunkard

import android.content.Context
import android.media.MediaPlayer
import java.io.Serializable

class Player(context: Context, songID: Int) : Serializable{
    var volume : Float
    var muted : Boolean

    lateinit var player: MediaPlayer

    init{
        volume = 0.5f
        player = MediaPlayer.create(context, songID)
        player.setVolume(volume, volume)
        player.isLooping = true
        muted = false
    }

    fun play() {
        if (!muted) {
            player.start()
        }
    }

    fun pause() {
        player.pause()
    }

    fun stop() {
        player.stop()
    }

    fun changeVolume(newVolume : Float){
        player.setVolume(newVolume, newVolume)
    }

    fun cancelLooping(){
        player.isLooping = false
    }
}