import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Aniko");
        Player player2 = new Player("David");
        List<Player> players = new ArrayList<>(List.of(player1, player2));
        Game game = new Game(players);

        game.deal();
        for (int i = 0; i < game.players.size(); i++) {
            System.out.println(game.players.get(i).getName());
            game.players.get(i).showCards();
            System.out.println();
        }
    }
}
