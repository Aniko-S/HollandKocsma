package com.github.anikos.hollandkocsma.entity;

import com.github.anikos.hollandkocsma.GameService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Machine extends Player {

    public Machine() {
        super("Bob");
    }

    public void putToShownCards(GameService game) {
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

    private Card searchMaxCard(Set<Card> cardSet) {
        Optional<Card> maxCard = cardSet.stream().max(Card::compareTo);
        return maxCard.orElse(null);
    }


}
