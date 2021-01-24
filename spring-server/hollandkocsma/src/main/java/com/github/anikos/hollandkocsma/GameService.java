package com.github.anikos.hollandkocsma;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final int inHands = 6;
    private final int shown = 3;
    private final int blind = 3;
    protected List<Card> deck;
    protected List<Player> players;
    protected Pile pile;


    public GameState newGame(String name) {
        players = new ArrayList<>();
        Player player = new Player(name);
        players.add(player);
        Player machine = new Player(name);
        players.add(machine);
        Deck cardList = new Deck();
        deck = cardList.deck;
        pile = new Pile();
        deal();
        return new GameState(
                idsFromCardSet(player.handCards),
                idsFromCardSet(player.shownCards),
                player.blindCards.size(),
                machine.handCards.size(),
                idsFromCardSet(machine.shownCards),
                machine.blindCards.size());
    }

    private void draw(Player player) {
        int i = (int) (Math.random() * deck.size());
        player.handCards.add(deck.get(i));
        deck.remove(i);
    }

    public void deal() {
        for (Player player : players) {
            for (int i = 0; i < inHands; i++) {
                draw(player);
            }
            for (int i = 0; i < blind; i++) {
                int rand = (int) (Math.random() * deck.size());
                player.blindCards.add(deck.get(rand));
                deck.remove(rand);
            }
        }
    }

    public Set<Integer> idsFromCardSet(Set<Card> cardSet) {
        return cardSet.stream().map(Card::getId).collect(Collectors.toSet());
    }

}
