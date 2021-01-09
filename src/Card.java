public class Card {
    private Deck.Color color;
    private Deck.Value value;

    public Card(Deck.Color color, Deck.Value value) {
        this.color = color;
        this.value = value;
    }

    public Deck.Color getColor() {
        return color;
    }

    public Deck.Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}
