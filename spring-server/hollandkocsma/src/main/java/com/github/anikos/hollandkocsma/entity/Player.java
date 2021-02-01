package com.github.anikos.hollandkocsma.entity;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private String name;
    public Set<Card> handCards;
    public Set<Card> blindCards;
    public Set<Card> shownCards;

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
