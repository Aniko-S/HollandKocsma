package com.github.anikos.hollandkocsma;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Deck {
    public List<Card> deck = new ArrayList<>();
    private static List<Card> allCards = new ArrayList<>();

    public Deck() {
        int id = 1;
        for (int i = 0; i < Color.values().length; i++) {
            for (int j = 0; j < Value.values().length; j++) {
                deck.add(new Card(Color.values()[i], Value.values()[j], id));
                id++;
            }
        }
        allCards = List.copyOf(deck);
    }

    public enum Color {
        DIAMOND,
        CLUB,
        HEART,
        SPADE
    }

    public enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, J, Q, K, A
    }

    public static Card getCardFromId(int id) {
        Optional<Card> goodCard = allCards.stream().filter(card ->
                card.getId() == id).findAny();
        if (goodCard.isPresent()) {
            return goodCard.get();
        } else {
            throw new RuntimeException("Card not found");
        }
    }
}
