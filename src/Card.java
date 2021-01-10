import java.util.Objects;

public class Card {
    private final Deck.Color color;
    private final Deck.Value value;
    private final int id;

    public Card(Deck.Color color, Deck.Value value, int id) {
        this.color = color;
        this.value = value;
        this.id = id;
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
}
