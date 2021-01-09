import java.util.ArrayList;
import java.util.List;

public class Deck {
    List<Card> deck = new ArrayList<>();

    public Deck() {
        int id = 0;
        for (int i = 0; i < Color.values().length; i++) {
            for (int j = 0; j < Value.values().length; j++) {
                deck.add(new Card(Color.values()[i], Value.values()[j], id));
                id++;
            }
        }
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
}
