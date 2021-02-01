package com.github.anikos.hollandkocsma.entityforsend;

import com.github.anikos.hollandkocsma.GameService;
import com.github.anikos.hollandkocsma.entity.Player;
import java.util.HashSet;
import java.util.Set;

public abstract class GamerData {
    public final String name;
    public Set<Integer> shownCardsIds;
    public final int blindCardsNumber;

    public GamerData(Player player) {
        name = player.getName();
        fillShownCardsIds(player);
        blindCardsNumber = player.blindCards.size();
    }

    private void fillShownCardsIds(Player player) {
        if (player.shownCards.size() != 0) {
            shownCardsIds = GameService.idsFromCardSet(player.shownCards);
        } else {
            shownCardsIds = new HashSet<>();
        }
        for (int i = shownCardsIds.size(); i < GameService.shown; i++) {
            shownCardsIds.add(i - 3);   // it must be 0 or negative
        }
    }
}
