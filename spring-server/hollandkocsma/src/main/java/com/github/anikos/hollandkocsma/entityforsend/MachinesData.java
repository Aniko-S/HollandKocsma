package com.github.anikos.hollandkocsma.entityforsend;

import com.github.anikos.hollandkocsma.entity.Machine;

public class MachinesData extends GamerData {
    public int handCardsNumber;

    public MachinesData(Machine machine) {
        super(machine);
        handCardsNumber = machine.handCards.size();
    }
}
