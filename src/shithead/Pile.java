package shithead;

import java.util.HashSet;
import java.util.Set;

public class Pile {
    protected Set<Card> cardSet;
    private Card top;

    public Pile() {
        cardSet = new HashSet<>();
    }

    public Card getTop() {
        return top;
    }

    public void setTop(int cardId) {
        top = Deck.getCardFromId(cardId);
    }
}
