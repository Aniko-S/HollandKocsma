import java.util.ArrayList;
import java.util.List;

public class Game {
    // A játék elején minden játékos 6 lapot kap kézbe, 3-at lefelé fordítva. A kezéből 3-at tesz le felfelé fordítva.
    private final int inHands = 6;
    private final int shown = 3;
    private final int blind = 3;
    List<Card> deck = new ArrayList<>();
    List<Player> players = new ArrayList<>();

    public Game(List<Player> players) {
        fillDeck();
        this.players = players;
    }

    public enum Color {
        DIAMOND,
        CLUB,
        HEART,
        SPADE
    }

    public enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, J, Q, K, A
    }

    public void fillDeck() {
        for (int i = 0; i < Color.values().length; i++) {
            for (int j = 0; j < Value.values().length; j++) {
                deck.add(new Card(Game.Color.values()[i], Game.Value.values()[j]));
            }
        }
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
