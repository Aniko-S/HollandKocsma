package com.github.anikos.hollandkocsma.entityforsend;

import com.github.anikos.hollandkocsma.entity.Card;
import lombok.AllArgsConstructor;
import java.util.Set;

@AllArgsConstructor
public class TablesData {
    public boolean hasDeck;
    public Set<Integer> pileTop;
    public String message;
}
