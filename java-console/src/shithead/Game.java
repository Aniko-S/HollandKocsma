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
        if (!isValidCardSet(ids)) {
            return 1;
        }
        if (ids[0] == 0) {
            player.handCards.addAll(pile.cardSet);
            pile = new Pile();
            return 0;
        }
        if (!player.handCards.isEmpty()) {
            if (!isTheSetContainsAllCards(player.handCards, ids)) {
                return 1;
            }
            return playKnownCards(player, ids, player.handCards);
        }
        if (!player.shownCards.isEmpty()) {
            if (!isTheSetContainsAllCards(player.shownCards, ids)) {
                return 1;
            }
            return playKnownCards(player, ids, player.shownCards);
        }
        Card playedCard = Deck.getCardFromId(ids[0]);
        if (ids.length > 1 || !player.blindCards.contains(playedCard)) {
            return 1;
        }
        return playFromBlindCards(player, playedCard);


    }

    private boolean isValidCardSet(int[] ids) {
        if (ids.length < 1) {
            return false;
        }
        if (pile.cardSet.isEmpty() && ids[0] == 0) {
            return false;
        }
        if (ids[0] != 0) {
            if (pile.getTop() != null && !Deck.getCardFromId(ids[0]).canPutTo(pile.getTop())) {
                return false;
            }
            return areEquals(ids);
        }
        return true;
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

    private int playKnownCards(Player player, int[] ids, Set<Card> playCardSet) {
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
        if (player.handCards.isEmpty() && player.blindCards.isEmpty()) {
            return 3;
        }
        return isBurn(Deck.getCardFromId(ids[0]));
    }

    private boolean isTheSetContainsAllCards(Set<Card> cardSet, int[] ids) {
        Set<Card> cardSetFromIds = Arrays.stream(ids)
                .mapToObj(Deck::getCardFromId)
                .collect(Collectors.toSet());
        return cardSet.containsAll(cardSetFromIds);
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


    private int playFromBlindCards(Player player, Card card) {
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
}
