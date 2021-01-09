import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Player player1 = new Player("Aniko");
    public static Player player2 = new Player("David");
    public static List<Player> players = new ArrayList<>(List.of(player1, player2));
    public static Game game = new Game(players);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        game.deal();
        for (Player player: game.players) {
            player.showCards();
            System.out.println("Choose 3 cards to put face-up:");
            for (int i = 0; i < 3; i++) {
                int id = scanner.nextInt();
                game.putToShownCards(player, id);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (Player player: game.players) {
                System.out.println("Pile: " + game.pile.getTop());
                player.showCards();
                System.out.println("0 - Pick up the pile");
                int id = scanner.nextInt();
                game.turn(player, id);
                player.showCards();
            }
        }
        shownEverybodysCard();
    }

    public static void shownEverybodysCard() {
        for (Player player: game.players) {
            player.showCards();
            System.out.println();
        }
    }
}
