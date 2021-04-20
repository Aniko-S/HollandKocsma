package com.github.anikos.hollandkocsma.entityforsend;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Builder
@AllArgsConstructor
public class GameState {
    public String gameId;
    public PlayersData playersData1;
    public PlayersData playersData2;
    public TablesData tablesData;
    public boolean isTurnFinished;
    public boolean isBurned;
    public List<Integer> selectedIds;
    public boolean isFromBlind;
}
