package com.github.anikos.hollandkocsma.entity;

import com.github.anikos.hollandkocsma.entity.Card;

import java.util.HashSet;
import java.util.Set;

public class Pile {
    public Set<Card> cardSet;
    private Card top;
    private int equalCardsCounter;

    public Pile() {
        cardSet = new HashSet<>();
    }
}
