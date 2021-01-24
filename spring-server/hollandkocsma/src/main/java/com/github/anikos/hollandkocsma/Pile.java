package com.github.anikos.hollandkocsma;

import java.util.HashSet;
import java.util.Set;

public class Pile {
    protected Set<Card> cardSet;
    private Card top;
    private int equalCardsCounter;

    public Pile() {
        cardSet = new HashSet<>();
    }
}
