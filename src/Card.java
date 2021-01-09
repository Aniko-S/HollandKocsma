public class Card {
    private Deck.Color color;
    private Deck.Value value;
    private int id;

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
}
