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

    public int turnWidthOneCard(Player player, int id) {
        if (id == 0) {
            player.handCards.addAll(pile.cardSet);
            pile = new Pile();
            return 0;
        } else if (!Deck.getCardFromId(id).canPutTo(pile.getTop())) {
            return 1;
        } else {
            putACardFromTo(id, player.handCards, pile.cardSet);
            pile.setTop(id);
            if (player.handCards.size() < 3) {
                draw(player);
            }
            return 0;
        }
    }

    public int turn(Player player, int[] ids) {
        // 0 : mehet tovább a játék a következő játékossal
        // 1 : rossz lépés, újra az adott játékos van
        // TODO: 2 : égetett, így újra az adott játékos van
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
            if (firstCard != null && actualCard != null) {
                if (!firstCard.getValue().equals(actualCard.getValue())) {
                    return false;
                }
            }
        }
        return true;
    }

}
