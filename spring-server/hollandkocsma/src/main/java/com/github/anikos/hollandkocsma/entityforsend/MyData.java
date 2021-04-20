package com.github.anikos.hollandkocsma.entityforsend;

import com.github.anikos.hollandkocsma.GameService;
import com.github.anikos.hollandkocsma.entity.Player;
import java.util.List;

public class MyData extends GamerData {
    public List<Integer> handCardsIds;

    public MyData(Player player) {
        super(player);
        handCardsIds = GameService.idsFromCardSet(player.handCards);
    }
}
