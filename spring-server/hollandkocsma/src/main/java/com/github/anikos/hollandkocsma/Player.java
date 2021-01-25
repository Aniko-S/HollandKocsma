package com.github.anikos.hollandkocsma;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private String name;
    protected Set<Card> handCards;
    protected Set<Card> blindCards;
    protected Set<Card> shownCards;

    public Player(String name) {
        this.name = name;
        handCards = new HashSet<>();
        blindCards = new HashSet<>();
        shownCards = new HashSet<>();
    }

    public String getName() {
        return name;
    }
}
