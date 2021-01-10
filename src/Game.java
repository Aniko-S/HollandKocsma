import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Game {
    // A játék elején minden játékos 6 lapot kap kézbe, 3-at lefelé fordítva. A kezéből 3-at tesz le felfelé fordítva.
    private final int inHands = 6;
    private final int shown = 3;
    private final int blind = 3;
    List<Card> deck;
    List<Player> players;
    Pile pile;

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

    public void turnWidthOneCard(Player player, int id) {
        if (id == 0) {
            player.handCards.addAll(pile.cardSet);
            pile = new Pile();
        } else {
            putACardFromTo(id, player.handCards, pile.cardSet);
            pile.setTop(id);
            if (player.handCards.size() < 3) {
                draw(player);
            }
        }
    }

    public void turn(Player player, int[] ids) {
        if (ids.length > 1) {
            if (areEquals(ids)) {
                for (int i = 0; i < ids.length; i++) {
                    turnWidthOneCard(player, ids[i]);
                }
            }
        } else {
           turnWidthOneCard(player, ids[0]);
        }
    }

    public boolean areEquals(int[] ids) {
        Card firstCard = Deck.getCardFromId(ids[0]);
        for (int i = 1; i < ids.length; i++) {
            Card actualCard = Deck.getCardFromId(ids[i]);
            if (firstCard != null && actualCard != null) {
                if (!firstCard.getValue().equals(actualCard.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

}
