package com.github.anikos.hollandkocsma.entity;

import com.github.anikos.hollandkocsma.entity.Card;

import java.util.HashSet;
import java.util.Set;

public class Pile {
    public Set<Card> cardSet;
    public Set<Card> topCardSet;
    private Card top;
    private int equalCardsCounter;

    public Pile() {
        cardSet = new HashSet<>();
        topCardSet = new HashSet<>();
    }

    public Card getTop() {
        return top;
    }

    public void setTop(int cardId) {
        top = Deck.getCardFromId(cardId);
    }

    public int getEqualCardsCounter() {
        return equalCardsCounter;
    }

    public void setEqualCardsCounter(int equalCardsCounter) {
        this.equalCardsCounter = equalCardsCounter;
    }

    public void incrementEqualCardsCounter() {
        equalCardsCounter++;
    }
}
