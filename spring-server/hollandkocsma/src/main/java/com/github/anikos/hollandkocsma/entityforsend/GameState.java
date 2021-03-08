package com.github.anikos.hollandkocsma.entityforsend;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameState {
    public int gameId;
    public PlayersData playersData;
    public MachinesData machinesData;
    public TablesData tablesData;
    public boolean isTurnFinished;
}
