package shithead;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Player player1 = new Player("Aniko");
    public static Player player2 = new Player("David");
    public static Player player3 = new Player("Juliska");
    public static Player player4 = new Player("Jancsika");
    public static List<Player> players = new ArrayList<>(List.of(player1, player2, player3, player4));
    public static Game game = new Game(players);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        game.deal();
        for (Player player : game.players) {
            player.showCards();
            System.out.println("Choose 3 cards to put face-up:");
            boolean isCorrectStep = false;
            while (!isCorrectStep) {
                String answer = scanner.nextLine();
                String[] idsString = answer.split(" ");
                int[] ids = new int[idsString.length];
                for (int j = 0; j < ids.length; j++) {
                    ids[j] = Integer.parseInt(idsString[j]);
                }
                isCorrectStep = game.putToShownCards(player, ids);
                if (!isCorrectStep) {
                    System.out.println("Incorrect step");
                }
            }
            player.showCards();
            System.out.println();
        }

        Player winner = null;
        do {
            for (Player player : game.players) {
                System.out.println("Pile: " + game.pile.getTop() + "\n");
                player.showCards();
                System.out.println("0 - Pick up the pile\n");
                int gameState = 1;
                do {
                    String answer = scanner.nextLine();
                    String[] idsString = answer.split(" ");
                    int[] ids = new int[idsString.length];
                    for (int j = 0; j < ids.length; j++) {
                        ids[j] = Integer.parseInt(idsString[j]);
                    }
                    gameState = game.turn(player, ids);
                    if (gameState == 1) {
                        System.out.println("Incorrect step");
                    }
                    if (gameState == 2) {
                        System.out.println("You burned");
                        player.showCards();
                    }
                    if (gameState == 3) {
                        winner = player;
                    }
                } while (gameState != 0 && gameState != 3);
                if (gameState == 3) {
                    break;
                }
            }
        } while (winner == null);
        System.out.println("Winner: " + winner.getName());
    }

    public static void shownEverybodysCard() {
        for (Player player : game.players) {
            player.showCards();
            System.out.println();
        }
    }
}
