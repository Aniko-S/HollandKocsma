package shithead;

import java.util.List;
import java.util.Set;

public class Game {
    // A játék elején minden játékos 6 lapot kap kézbe, 3-at lefelé fordítva. A kezéből 3-at tesz le felfelé fordítva.
    private final int inHands = 6;
    private final int shown = 3;
    private final int blind = 3;
    private List<Card> deck;
    protected List<Player> players;
    protected Pile pile;

    public Game(List<Player> players) {
        this.players = players;
        Deck cardList = new Deck();
        deck = cardList.deck;
        pile = new Pile();
    }

    public void draw(Player player) {
        int i = (int) (Math.random() * deck.size());
        player.handCards.add(deck.get(i));
        deck.remove(i);
    }

    public void deal() {
        for (Player player : players) {
            for (int i = 0; i < inHands; i++) {
                draw(player);
            }
        }
    }

    public void putACardFromTo(int cardId, Set<Card> srcCards, Set<Card> targetCards) {
        Card goodCard = Deck.getCardFromId(cardId);
        srcCards.remove(goodCard);
        targetCards.add(goodCard);
    }

    public void putToShownCards(Player player, int id) {
        putACardFromTo(id, player.handCards, player.shownCards);
    }

    private int burn(Player player) {
        pile = new Pile();
        return 2;
    }

    private int turnWidthOneCard(Player player, int id) {
        // felveszi a pile-t
        if (id == 0) {
            if (pile.cardSet.size() == 0) {
                return 1;
            }
            player.handCards.addAll(pile.cardSet);
            pile = new Pile();
            return 0;
        }
        // kijátszik egy lapot
        Card playedCard = Deck.getCardFromId(id);
        Card previousCard = pile.getTop();
        if (player.handCards.size() > 0 || player.shownCards.size() > 0) {
            return playKnownCard(player, playedCard, previousCard);
        }
        return playBlindCard(player, playedCard, previousCard);
    }

    public int turn(Player player, int[] ids) {
        // 0 : mehet tovább a játék a következő játékossal
        // 1 : rossz lépés, újra az adott játékos van
        // 2 : égetett, így újra az adott játékos van
        if (ids.length < 1) {
            throw new RuntimeException("Didn't played any card");
        }
        if (ids.length > 1) {
            if (areEquals(ids) && Deck.getCardFromId(ids[0]).canPutTo(pile.getTop())) {
                for (int id : ids) {
                    turnWidthOneCard(player, id);
                }
                return 0;
            }
            return 1;
        } else {
            return turnWidthOneCard(player, ids[0]);
        }
    }

    public boolean areEquals(int[] ids) {
        Card firstCard = Deck.getCardFromId(ids[0]);
        for (int i = 1; i < ids.length; i++) {
            Card actualCard = Deck.getCardFromId(ids[i]);
            if (!firstCard.getValue().equals(actualCard.getValue())) {
                return false;
            }
        }
        return true;
    }

    private int playKnownCard(Player player, Card playedCard, Card previousCard) {
        if (!playedCard.canPutTo(pile.getTop())) {
            return 1;
        }
        if (player.handCards.size() > 0) {
            playFromHand(player, playedCard);
        } else {
            putACardFromTo(playedCard.getId(), player.shownCards, pile.cardSet);
        }
        if (playedCard.getValue().equals(Deck.Value.TEN)) {
            return burn(player);
        }
        if (playedCard.equals(previousCard)) {
            pile.incrementEqualCardsCounter();
        } else {
            pile.setEqualCardsCounter(1);
        }
        if (pile.getEqualCardsCounter() == 4) {
            return burn(player);
        }
        return 0;
    }

    private void playFromHand(Player player, Card playedCard) {
        if (player.handCards.contains(playedCard)) {
            putACardFromTo(playedCard.getId(), player.handCards, pile.cardSet);
            if (player.handCards.size() < 3 && deck.size() > 0) {
                draw(player);
            }
        } else {
            throw new RuntimeException("The handcards list doesn't contain the selected card");
        }
    }

    private void playFromShown(Player player, Card playedCard) {
        if (player.shownCards.contains(playedCard)) {
            putACardFromTo(playedCard.getId(), player.shownCards, pile.cardSet);
        } else {
            throw new RuntimeException("The showncards list doesn't contain the selected card");
        }
    }

    private int playBlindCard(Player player, Card playedCard, Card previousCard) {
        return 0;
    }

}
