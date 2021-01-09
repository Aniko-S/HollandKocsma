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
        int i = (int)(Math.random() * deck.size());
        player.handCards.add(deck.get(i));
        deck.remove(i);
    }

    public void deal() {
        for (Player player: players) {
            for (int i = 0; i < inHands; i++) {
                draw(player);
            }
        }
    }

    public void putACardFromTo(int cardId, Set<Card> srcCards, Set<Card> targetCards) {
        Optional<Card> goodCard = srcCards.stream().filter(card ->
            card.getId() == cardId).findAny();
        if (goodCard.isPresent()) {
            srcCards.remove(goodCard.get());
            targetCards.add(goodCard.get());
        }
    }

    public void putToShownCards(Player player, int id) {
       putACardFromTo(id, player.handCards, player.shownCards);
    }

    public void turn(Player player, int id) {
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

}
