package shithead;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Machine extends Player {
    public Machine() {
        super("Bot");
    }

    public void putToShownCards(Game game) {
        List<Card> goodCards = handCards.stream().filter(
                card ->
                        card.getValue().equals(Deck.Value.FIVE) ||
                                card.getValue().equals(Deck.Value.TEN) ||
                                card.getValue().equals(Deck.Value.TWO))
                .collect(Collectors.toList());
        for (int i = 0; i < Math.min(goodCards.size(), 3); i++) {
            game.putACardFromTo(goodCards.get(i), handCards, shownCards);
        }
        while (shownCards.size() < 3) {
            game.putACardFromTo(searchMaxCard(handCards), handCards, shownCards);
        }
    }

    public int[] put(Game game) {
        if (handCards.isEmpty() && shownCards.isEmpty()) {
            return new int[]{putFromBlindCards()};
        }
        Set<Card> goodCards = searchGoodCards(game);
        if (goodCards.isEmpty()) {
            return new int[]{0};
        }
        Set<Card> notMagicCards = searchNotMagicCards(goodCards);
        if (!notMagicCards.isEmpty()) {
            Card goodCard = searchMinCard(notMagicCards);
            return goodCards.stream()
                    .mapToInt(Card::getId)
                    .filter(id -> id == goodCard.getId())
                    .toArray();
        } else {
            return new int[]{searchMinCard(goodCards).getId()};
        }
    }

    public Card searchMaxCard(Set<Card> cardSet) {
        Optional<Card> maxCard = cardSet.stream().max(Card::compareTo);
        return maxCard.orElse(null);
    }

    public Card searchMinCard(Set<Card> cardSet) {
        Optional<Card> minCard = cardSet.stream().min(Card::compareTo);
        return minCard.orElse(null);
    }

    public int putFromBlindCards() {
        return blindCards.stream().findFirst().get().getId();
    }

    public Set<Card> searchGoodCards(Game game) {
        if (!handCards.isEmpty()) {
            return handCards.stream().filter(card ->
                    card.canPutTo(game.pile.getTop()))
                    .collect(Collectors.toSet());
        } else {
            return shownCards.stream().filter(card ->
                    card.canPutTo(game.pile.getTop()))
                    .collect(Collectors.toSet());
        }
    }

    public Set<Card> searchNotMagicCards(Set<Card> goodCards) {
        return goodCards.stream().filter(card ->
                !card.getValue().equals(Deck.Value.FIVE)
                        && !card.getValue().equals(Deck.Value.TWO)
                        && !card.getValue().equals(Deck.Value.TEN))
                .collect(Collectors.toSet());
    }
}
