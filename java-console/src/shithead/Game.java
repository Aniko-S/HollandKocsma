package shithead;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    // A játék elején minden játékos 6 lapot kap kézbe, 3-at lefelé fordítva. A kezéből 3-at tesz le felfelé fordítva.
    private final int inHands = 6;
    private final int shown = 3;
    private final int blind = 3;
    protected List<Card> deck;
    protected List<Player> players;
    protected Pile pile;

    public Game(List<Player> players) {
        this.players = players;
        Deck cardList = new Deck();
        deck = cardList.deck;
        pile = new Pile();
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

    private void putACardFromTo(Card card, Set<Card> srcCards, Set<Card> targetCards) {
        srcCards.remove(card);
        targetCards.add(card);
    }

    public boolean putToShownCards(Player player, int[] ids) {
        Set<Card> cards = Arrays.stream(ids)
                .mapToObj(Deck::getCardFromId)
                .filter(card -> player.handCards.contains(card))
                .collect(Collectors.toSet());
        if (cards.size() != 3) {
            return false;
        }
        cards.forEach(card -> putACardFromTo(card, player.handCards, player.shownCards));
        return true;
    }


    public int turn(Player player, int[] ids) {
        // 0 : mehet tovább a játék a következő játékossal
        // 1 : rossz lépés, újra az adott játékos van
        // 2 : égetett, így újra az adott játékos van
        // 3 : nyert
        if (ids.length < 1) {
            return 1;
        }
        if (ids[0] == 0) {
            if (pile.cardSet.isEmpty()) {
                return 1;
            }
            player.handCards.addAll(pile.cardSet);
            pile = new Pile();
            return 0;
        }
        if (!areEquals(ids) || !Deck.getCardFromId(ids[0]).canPutTo(pile.getTop())) {
            return 1;
        }
        return playCards(player, ids);

    }

    private int playCards(Player player, int[] ids) {
        for (int id : ids) {
            Card playedCard = Deck.getCardFromId(id);
            Card previousCard = pile.getTop();
            if (!player.handCards.isEmpty()) {
                if (!playFromHand(player, playedCard)) {
                    return 1;
                }
            } else if (!player.shownCards.isEmpty()) {
                if (!playFromShownCards(player, playedCard)) {
                    return 1;
                }
            }
            playFromBlindCards(player, playedCard);
            countEqualCards(playedCard, previousCard);
        }
        if (player.handCards.isEmpty() && player.blindCards.isEmpty()) {
            return 3;
        }
        return isBurn(Deck.getCardFromId(ids[0]));
    }


    private boolean playFromHand(Player player, Card card) {
        if (!player.handCards.contains(card)) {
            return false;
        }
        if (!card.canPutTo(pile.getTop())) {
            return false;
        }
        putACardFromTo(card, player.handCards, pile.cardSet);
        pile.setTop(card.getId());
        if (player.handCards.size() < 3 && deck.size() > 0) {
            draw(player);
        }
        return true;
    }

    private boolean playFromShownCards(Player player, Card card) {
        if (!player.shownCards.contains(card)) {
            return false;
        }
        if (!card.canPutTo(pile.getTop())) {
            return false;
        }
        putACardFromTo(card, player.shownCards, pile.cardSet);
        pile.setTop(card.getId());
        return true;
    }

    private void playFromBlindCards(Player player, Card card) {
        if (card.canPutTo(pile.getTop())) {
            putACardFromTo(card, player.blindCards, pile.cardSet);
            pile.setTop(card.getId());
        } else {
            player.handCards.addAll(pile.cardSet);
            pile = new Pile();
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

    private boolean areEquals(int[] ids) {
        Card firstCard = Deck.getCardFromId(ids[0]);
        for (int i = 1; i < ids.length; i++) {
            Card actualCard = Deck.getCardFromId(ids[i]);
            if (!firstCard.getValue().equals(actualCard.getValue())) {
                return false;
            }
        }
        return true;
    }
}
