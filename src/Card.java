public class Card {
    private Game.Color color;
    private Game.Value value;

    public Card(Game.Color color, Game.Value value) {
        this.color = color;
        this.value = value;
    }

    public Game.Color getColor() {
        return color;
    }

    public Game.Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}
