package com.github.anikos.hollandkocsma;

import com.github.anikos.hollandkocsma.entity.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final int inHands = 6;
    private final int shown = 3;
    private final int blind = 3;
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
                player.getName(),
                idsFromCardSet(player.handCards),
                new HashSet<>(Set.of(0, -1, -2)),
                player.blindCards.size(),
                machine.handCards.size(),
                new HashSet<>(Set.of(0, -1, -2)),
                machine.blindCards.size(),
                !deck.isEmpty(),
                "Choose three cards to put down face-up");
    }

    public GameState putToShownCards(ArrayList<Integer> ids) {
        String message = "Your turn";
        Set<Integer> playersShownCardsIds = new HashSet<>(Set.of(0, -1, -2));
        Set<Integer> machinesShownCardsIds = new HashSet<>(Set.of(0, -1, -2));
        if (!canPutToShownCards(ids)) {
            message = "Incorrect step. You have to select three cards.";
        } else {
            ids.forEach(id -> putACardFromTo(Deck.getCardFromId(id), player.handCards, player.shownCards));
            ((Machine)machine).putToShownCards(this);
            playersShownCardsIds = idsFromCardSet(player.shownCards);
            machinesShownCardsIds = idsFromCardSet(machine.shownCards);
        }
        return new GameState(
                player.getName(),
                idsFromCardSet(player.handCards),
                playersShownCardsIds,
                player.blindCards.size(),
                machine.handCards.size(),
                machinesShownCardsIds,
                machine.blindCards.size(),
                !deck.isEmpty(),
                message);
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

    private Set<Integer> idsFromCardSet(Set<Card> cardSet) {
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
