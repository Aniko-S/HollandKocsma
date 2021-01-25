package com.github.anikos.hollandkocsma;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public class GameState {
    public String name;
    public Set<Integer> playersHandCardsIds;
    public Set<Integer> playersShownCardsIds;
    public int playersBlindCardsNumber;
    public int machineHandCardsNumber;
    public Set<Integer> machineShownCardsIds;
    public int machineBlindCardsNumber;
    public boolean hasDeck;
}
