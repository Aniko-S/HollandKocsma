import java.util.ArrayList;
import java.util.List;

public class Game {
    // A játék elején minden játékos 6 lapot kap kézbe, 3-at lefelé fordítva. A kezéből 3-at tesz le felfelé fordítva.
    private final int inHands = 6;
    private final int shown = 3;
    private final int blind = 3;
    List<Card> deck;
    List<Player> players;

    public Game(List<Player> players) {
        this.players = players;
        Deck cardList = new Deck();
        deck = cardList.deck;
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

}
