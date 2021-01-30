package com.github.anikos.hollandkocsma;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Objects;

@Data
@AllArgsConstructor
public class Card implements Comparable<Card> {
    private final Deck.Color color;
    private final Deck.Value value;
    private final int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && color == card.color && value == card.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value, id);
    }

    @Override
    public int compareTo(Card card) {
        if (this.value.ordinal() < card.value.ordinal()) {
            return -1;
        }
        if (this.value.ordinal() > card.value.ordinal()) {
            return 1;
        }
        return 0;
    }

}
