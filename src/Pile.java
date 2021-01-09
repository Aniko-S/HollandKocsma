import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Pile {
    Set<Card> cardSet;
    private Card top;

    public Pile() {
        cardSet = new HashSet<>();
    }

    public Card getTop() {
        return top;
    }

    public void setTop(int cardId) {
        Optional<Card> goodCard = cardSet.stream().filter(card ->
                card.getId() == cardId).findAny();
        if (goodCard.isPresent()) {
            top = goodCard.get();
        }
    }
}
