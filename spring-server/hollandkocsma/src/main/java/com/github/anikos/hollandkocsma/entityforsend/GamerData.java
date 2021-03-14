package com.github.anikos.hollandkocsma.entityforsend;

import com.github.anikos.hollandkocsma.GameService;
import com.github.anikos.hollandkocsma.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashSet;
import java.util.Set;

public abstract class GamerData {
    private static final Logger log = LoggerFactory.getLogger(GamerData.class);

    public final String name;
    public Set<Integer> shownCardsIds;
    public final int blindCardsNumber;
    public boolean isWinner;

    public GamerData(Player player) {
        name = player.getName();
        blindCardsNumber = player.blindCards.size();
        fillShownCardsIds(player);
        log.info("Player:{}, blindCardsNumber: {}", player.getName(), blindCardsNumber);
        isWinner = player.handCards.isEmpty() && player.shownCards.isEmpty() && player.blindCards.isEmpty();
    }

    private void fillShownCardsIds(Player player) {
        if (player.shownCards.size() != 0) {
            shownCardsIds = GameService.idsFromCardSet(player.shownCards);
        } else {
            shownCardsIds = new HashSet<>();
        }
        for (int i = shownCardsIds.size(); i < blindCardsNumber; i++) {
            shownCardsIds.add(i - 3);   // it must be 0 or negative
        }
    }
}