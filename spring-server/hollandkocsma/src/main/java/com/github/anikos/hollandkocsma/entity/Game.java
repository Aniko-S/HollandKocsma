package com.github.anikos.hollandkocsma.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public int id;
    public Player player;
    public Machine machine;
    public List<Player> players;
    public List<Card> deck;
    public Pile pile;

    public Game(Player player, int gamesNumber) {
        id = gamesNumber;
        this.player = player;
        this.machine = new Machine();
        players = new ArrayList<>();
        players.add(player);
        players.add(machine);
        Deck cardList = new Deck();
        deck = cardList.deck;
        pile = new Pile();
    }
}
