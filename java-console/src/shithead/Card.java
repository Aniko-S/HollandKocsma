package shithead;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final Deck.Color color;
    private final Deck.Value value;
    private final int id;

    public Card(Deck.Color color, Deck.Value value, int id) {
        this.color = color;
        this.value = value;
        this.id = id;
    }

    public boolean canPutTo(Card card) {
        if (card == null) {
            return true;
        }
        if (this.value.equals(Deck.Value.TWO) || this.value.equals(Deck.Value.FIVE) || this.value.equals(Deck.Value.TEN)) {
            return true;
        }
        if (card.value.equals(Deck.Value.FIVE)) {
            return this.value.ordinal() < Deck.Value.FIVE.ordinal();
        }
        return this.value.ordinal() >= card.value.ordinal();
    }

    public Deck.Color getColor() {
        return color;
    }

    public Deck.Value getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " - " + color + " " + value;
    }

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
