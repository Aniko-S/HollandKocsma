package shithead;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final String name;
    protected Set<Card> handCards;
    protected Set<Card> blindCards;
    protected Set<Card> shownCards;

    public Player(String name) {
        this.name = name;
        handCards = new HashSet<>();
        blindCards = new HashSet<>();
        shownCards = new HashSet<>();
    }

    public void showCards() {
        System.out.println(name);
        if (handCards.size() == 0) {
            System.out.println("Shown: ");
            for (Card card: shownCards) {
                System.out.println(card);
            }
            System.out.println();
        }
        System.out.println("In hand: ");
        for (Card card: handCards) {
            System.out.println(card);
        }
        System.out.println();
        if (handCards.size() == 0 && shownCards.size() == 0) {
            System.out.println("Blind: ");
            for (Card card: blindCards) {
                System.out.println(card);
            }
        }
    }

    public String getName() {
        return name;
    }
}
