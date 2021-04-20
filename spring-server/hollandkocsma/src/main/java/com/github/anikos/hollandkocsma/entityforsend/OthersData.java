package com.github.anikos.hollandkocsma.entityforsend;

import com.github.anikos.hollandkocsma.entity.Machine;

public class OthersData extends GamerData {
    public int handCardsNumber;

    public OthersData(Machine machine) {
        super(machine);
        handCardsNumber = machine.handCards.size();
    }
}
