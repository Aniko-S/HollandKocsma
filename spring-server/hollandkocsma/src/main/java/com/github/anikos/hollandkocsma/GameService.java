package com.github.anikos.hollandkocsma;

import com.github.anikos.hollandkocsma.entity.*;
import com.github.anikos.hollandkocsma.entityforsend.GameState;
import com.github.anikos.hollandkocsma.entityforsend.MachinesData;
import com.github.anikos.hollandkocsma.entityforsend.PlayersData;
import com.github.anikos.hollandkocsma.entityforsend.TablesData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {
    private static final Logger log = LoggerFactory.getLogger(GameService.class);
    private static final int inHands = 6;
    public static final int shown = 3;
    private static final int blind = 3;
    private Player player;
    private Player machine;
    protected List<Player> players = new ArrayList<>();
    protected List<Card> deck;
    public Pile pile;

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
            log.info("Can't put");
        } else {
            ids.forEach(id -> putACardFromTo(Deck.getCardFromId(id), player.handCards, player.shownCards));
            ((Machine) machine).putToShownCards(this);
            log.info("Can put");
        }
        return new GameState(
                new PlayersData(player),
                new MachinesData((Machine) machine),
                new TablesData(!deck.isEmpty(), new HashSet<>(), message),
                isValid
        );
    }

    public GameState playersTurn(List<Integer> ids) {
        log.info("Player put: {}", ids);
        String message;
        boolean isFinished = false;
        int gameStatus = turn(player, ids);
        log.info("GameStatus: {}", gameStatus);
        if (gameStatus == 0) {
            message = "Machine's turn";
            isFinished = true;
            pile.topCardSet = ids.stream().map(Deck::getCardFromId).collect(Collectors.toSet());
        } else if (gameStatus == 1) {
            message = "Incorrect step";
        } else {
            message = "You burn. It's your turn again.";
            pile.topCardSet = new HashSet<>();
        }
        return new GameState(
                new PlayersData(player),
                new MachinesData((Machine) machine),
                new TablesData(!deck.isEmpty(), message, pile),
                isFinished
        );
    }

    public GameState machinesTurn() {
        List<Integer> ids = ((Machine)machine).put(this);
        log.info("Machine put: {}", ids);
        String message;
        boolean isFinished = false;
        int gameStatus = turn(machine, ids);
        log.info("GameStatus: {}", gameStatus);
        if (gameStatus == 0) {
            message = "Your turn";
            isFinished = true;
            if (ids.get(0) != 0) {
                pile.topCardSet = ids.stream().map(Deck::getCardFromId).collect(Collectors.toSet());
            } else {
                pile.topCardSet = new HashSet<>();
            }
        } else if (gameStatus == 1) {
            message = "Incorrect step";
        } else {
            message = "Machine burn. It's his turn again.";
            pile.topCardSet = new HashSet<>();
        }
        return new GameState(
                new PlayersData(player),
                new MachinesData((Machine) machine),
                new TablesData(!deck.isEmpty(), message, pile),
                isFinished
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

    private int turn(Player player, List<Integer> ids) {
        if (!isValidCardSet(ids)) {
            log.info("Invalid card set");
            return 1;
        }
        if (ids.get(0) == 0) {
            log.info("Pick up the pile");
            player.handCards.addAll(pile.cardSet);
            pile = new Pile();
            return 0;
        }
        if (!player.handCards.isEmpty()) {
            if (!isTheSetContainsAllCards(player.handCards, ids)) {
                return 1;
            }
            return playKnownCards(ids, player, player.handCards);
        }
        if (!player.shownCards.isEmpty()) {
            if (!isTheSetContainsAllCards(player.shownCards, ids)) {
                return 1;
            }
            return playKnownCards(ids, player, player.shownCards);
        }
        Card playedCard = Deck.getCardFromId(ids.get(0));
        if (ids.size() > 1 || !player.blindCards.contains(playedCard)) {
            return 1;
        }
        return playFromBlindCards(playedCard, player);
    }

    private boolean isValidCardSet(List<Integer> ids) {
        if (ids.size() < 1) {
            log.info("No selected card");
            return false;
        }
        if (pile.cardSet.isEmpty() && ids.get(0) == 0) {
            log.info("Can't pick up the pile because there is no pile");
            return false;
        }
        if (ids.get(0) != 0) {
            return areEquals(ids);
        }
        return true;
    }

    private boolean areEquals(List<Integer> ids) {
        Card firstCard = Deck.getCardFromId(ids.get(0));
        for (int i = 1; i < ids.size(); i++) {
            Card actualCard = Deck.getCardFromId(ids.get(i));
            if (!firstCard.getValue().equals(actualCard.getValue())) {
                log.info("Not equal cards");
                return false;
            }
        }
        return true;
    }

    private boolean isTheSetContainsAllCards(Set<Card> cardSet, List<Integer> ids) {
        Set<Card> cardSetFromIds = ids.stream()
                .map(Deck::getCardFromId)
                .collect(Collectors.toSet());
        return cardSet.containsAll(cardSetFromIds);
    }

    private int playKnownCards(List<Integer> ids, Player player, Set<Card> playCardSet) {
        if (pile.getTop() != null && !Deck.getCardFromId(ids.get(0)).canPutTo(pile.getTop())) {
            return 1;
        }
        for (int id : ids) {
            Card playedCard = Deck.getCardFromId(id);
            Card previousCard = pile.getTop();
            pile.setTop(id);
            putACardFromTo(playedCard, playCardSet, pile.cardSet);
            if (player.handCards.size() < 3 && deck.size() > 0) {
                draw(player);
            }
            countEqualCards(playedCard, previousCard);
        }
        return isBurn(Deck.getCardFromId(ids.get(0)));
    }

    private int playFromBlindCards(Card card, Player player) {
        if (card.canPutTo(pile.getTop())) {
            Card previousCard = pile.getTop();
            putACardFromTo(card, player.blindCards, pile.cardSet);
            pile.setTop(card.getId());
            countEqualCards(card, previousCard);
            if (player.blindCards.isEmpty()) {
                return 3;
            }
            return isBurn(card);
        } else {
            player.handCards.addAll(pile.cardSet);
            pile = new Pile();
            return 0;
        }
    }

    private void countEqualCards(Card playedCard, Card previousCard) {
        if (previousCard != null && playedCard.getValue().equals(previousCard.getValue())) {
            pile.incrementEqualCardsCounter();
        } else {
            pile.setEqualCardsCounter(1);
        }
    }

    private int isBurn(Card card) {
        if (card.getValue().equals(Deck.Value.TEN)) {
            return burn();
        }
        if (pile.getEqualCardsCounter() == 4) {
            return burn();
        }
        return 0;
    }

    private int burn() {
        pile = new Pile();
        return 2;
    }
}
