package com.github.anikos.hollandkocsma.entityforsend;

import com.github.anikos.hollandkocsma.entity.Card;
import com.github.anikos.hollandkocsma.entity.Pile;
import lombok.AllArgsConstructor;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TablesData {
    public boolean hasDeck;
    public Set<Integer> pileTop;
    public String message;

    public TablesData(boolean hasDeck, String message, Pile pile) {
        this.hasDeck = hasDeck;
        this.message = message;
        pileTop = pile.topCardSet.stream().map(Card::getId).collect(Collectors.toSet());
    }
}
