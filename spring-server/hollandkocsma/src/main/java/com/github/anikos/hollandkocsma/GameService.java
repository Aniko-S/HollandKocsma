package com.github.anikos.hollandkocsma;

import com.github.anikos.hollandkocsma.entity.*;
import com.github.anikos.hollandkocsma.entityforsend.GameState;
import com.github.anikos.hollandkocsma.entityforsend.MachinesData;
import com.github.anikos.hollandkocsma.entityforsend.PlayersData;
import com.github.anikos.hollandkocsma.entityforsend.TablesData;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {
    private static final int inHands = 6;
    public static final int shown = 3;
    private static final int blind = 3;
    private Player player;
    private Player machine;
    protected List<Player> players = new ArrayList<>();
    protected List<Card> deck;
    protected Pile pile;

    public GameState newGame(String name) {
        player = new Player(name);
        machine = new Machine();
        players.add(player);
        players.add(machine);
        Deck cardList = new Deck();
        deck = cardList.deck;
        pile = new Pile();
        deal();
        return new GameState(
                new PlayersData(player),
                new MachinesData((Machine) machine),
                new TablesData(true, new HashSet<>(), "Choose three cards to put down face-up"),
                true
        );
    }

    public GameState putToShownCards(ArrayList<Integer> ids) {
        String message = "Your turn";
        boolean isValid = true;
        if (!canPutToShownCards(ids)) {
            message = "Incorrect step. You have to select three cards.";
            isValid = false;
        } else {
            ids.forEach(id -> putACardFromTo(Deck.getCardFromId(id), player.handCards, player.shownCards));
            ((Machine) machine).putToShownCards(this);
        }
        return new GameState(
                new PlayersData(player),
                new MachinesData((Machine) machine),
                new TablesData(!deck.isEmpty(), new HashSet<>(), message),
                isValid
        );
    }


    public void putACardFromTo(Card card, Set<Card> srcCards, Set<Card> targetCards) {
        srcCards.remove(card);
        targetCards.add(card);
    }

    private void deal() {
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

    private void draw(Player player) {
        int i = (int) (Math.random() * deck.size());
        player.handCards.add(deck.get(i));
        deck.remove(i);
    }

    public static Set<Integer> idsFromCardSet(Set<Card> cardSet) {
        return cardSet.stream().map(Card::getId).collect(Collectors.toSet());
    }

    private boolean canPutToShownCards(ArrayList<Integer> ids) {
        Set<Card> cards = ids.stream()
                .map(Deck::getCardFromId)
                .filter(card -> player.handCards.contains(card))
                .collect(Collectors.toSet());
        return cards.size() == shown;
    }
}
