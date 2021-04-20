package com.github.anikos.hollandkocsma.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Game {
    public String id;
    public ArrayList<Player> players;
    public List<Card> deck;
    public Pile pile;

    public Game(String name) {
        id = UUID.randomUUID().toString();
        players = new ArrayList<>();
        addPlayer(new Player(name));
        Deck cardList = new Deck();
        deck = cardList.deck;
        pile = new Pile();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
