import java.util.HashSet;
import java.util.Set;

public class Player {
    private String name;
    Set<Card> handCards;
    Set<Card> blindCards;
    Set<Card> shownCards;

    public Player(String name) {
        this.name = name;
        handCards = new HashSet<>();
        blindCards = new HashSet<>();
        shownCards = new HashSet<>();
    }

    public void showCards() {
        System.out.println(name);
        System.out.println("In hand: ");
        for (Card card: handCards) {
            System.out.println(card);
        }
        System.out.println();
        System.out.println("Shown: ");
        for (Card card: shownCards) {
            System.out.println(card);
        }
        System.out.println();
    }
}
