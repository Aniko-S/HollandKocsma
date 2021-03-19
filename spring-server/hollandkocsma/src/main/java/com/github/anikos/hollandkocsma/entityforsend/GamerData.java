package com.github.anikos.hollandkocsma.entityforsend;

import com.github.anikos.hollandkocsma.GameService;
import com.github.anikos.hollandkocsma.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class GamerData {
    private static final Logger log = LoggerFactory.getLogger(GamerData.class);

    public final String name;
    public List<Integer> shownCardsIds;
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
            shownCardsIds = new ArrayList<>();
        }
        for (int i = shownCardsIds.size(); i < blindCardsNumber; i++) {
            shownCardsIds.add(-1);
        }
    }
}