package com.example.drunkard

import android.util.Log
import java.util.*

class PVPDrunkGame() {
    private var Deck1 : Deck = Deck()
    private var Deck2 : Deck = Deck()
    private var CurDeck1 = Deck()
    private var CurDeck2 = Deck()
    var Card1 = Card("", 0, "")
    var Card2 = Card("", 0, "")
    init {
        var Deck : Array<Card> = emptyArray()
        var used : Array<Boolean> = emptyArray()
        var faces = arrayOf("jack", "queen", "king", "ace")
        var suits = arrayOf("spades", "hearts", "clubs", "diamonds")

        for (i in 6..10) {
            for (j in 0..3) {
                Deck = Deck.plus(Card(suits[j], i, i.toString()))
                used = used.plus(false)
            }
        }
        for (i in 11..14) {
            for (j in 0..3) {
                Deck = Deck.plus(Card(suits[j], i, faces[i - 11]))
                used = used.plus(false)
            }
        }

        while (Deck1.GetDeckSize() < 18) {
            var i : Int = Math.abs(Random().nextInt()) % 36
            Log.d("Check", i.toString())
            if (!used[i]) {
                used[i]= true
                Deck1.AddCard(Deck[i])
            }
        }
        while (Deck2.GetDeckSize() < 18) {
            var i : Int = Math.abs(Random().nextInt()) % 36
            if (!used[i]) {
                used[i]= true
                Deck2.AddCard(Deck[i])
            }
        }
    }

    fun StartTurn1() {
        if (CurDeck1.GetDeckSize() > CurDeck2.GetDeckSize())
            return
        Card1 = Deck1.GetFirstCard()
        Log.d("Player 1 card = ", Card1.GetCardText())
        CurDeck1.AddCard(Card1)
    }

    fun StartTurn2() {

        if (CurDeck2.GetDeckSize() > CurDeck1.GetDeckSize())
            return
        Card2 = Deck2.GetFirstCard()
        Log.d("Player 2 card = ", Card2.GetCardText())
        CurDeck2.AddCard(Card2)

    }

    fun EndTurn() : Int {
        if (Deck1.GetDeckSize() == 0 || Deck2.GetDeckSize() == 0)
            return -1
        if (isCardBigger(Card1, Card2) >= 0) {
            MergeDecks(Deck1, CurDeck1)
            MergeDecks(Deck1, CurDeck2)
            return 1
        }
        if  (isCardBigger(Card1, Card2) < 0) {
            MergeDecks(Deck2, CurDeck1)
            MergeDecks(Deck2, CurDeck2)
            return 2
        }
        return 0

    }

    fun GetDeckSize1() : Int {
        return Deck1.GetDeckSize()
    }

    fun GetDeckSize2() : Int {
        return Deck2.GetDeckSize()
    }

    fun GetCurDeckSize1() : Int {
        return CurDeck1.GetDeckSize()
    }

    fun GetCurDeckSize2() : Int {
        return CurDeck2.GetDeckSize()
    }
}