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
        player.isLooping = true
        player.setVolume(volume, volume)

        muted = false
    }

    fun play() {
        player.start()
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
}