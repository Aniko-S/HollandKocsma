package shithead;

import java.util.Optional;
import java.util.Set;

public class Machine extends Player {
    public Machine() {
        super("Bot");
    }

    public Card searchMaxCard(Set<Card> cardSet) {
        Optional<Card> maxCard = cardSet.stream().max(Card::compareTo);
        return maxCard.orElse(null);
    }
}
