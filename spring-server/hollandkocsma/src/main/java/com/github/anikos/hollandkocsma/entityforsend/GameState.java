package com.github.anikos.hollandkocsma.entityforsend;

import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.List;

@Builder
@AllArgsConstructor
public class GameState {
    public String gameId;
    public MyData myData;
    public OthersData othersData;
    public TablesData tablesData;
    public boolean isTurnFinished;
    public boolean isBurned;
    public List<Integer> selectedIds;
    public boolean isFromBlind;
}
