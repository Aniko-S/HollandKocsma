package shithead;

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
        for (Player player : game.players) {
            player.showCards();
            System.out.println("Choose 3 cards to put face-up:");
            for (int i = 0; i < 3; i++) {
                int id = scanner.nextInt();
                scanner.nextLine();
                game.putToShownCards(player, id);
            }
            player.showCards();
            System.out.println();
        }

        for (int i = 0; i < 3; i++) {
            for (Player player : game.players) {
                System.out.println("shithead.Pile: " + game.pile.getTop() + "\n");
                player.showCards();
                System.out.println("0 - Pick up the pile\n");
                int canGo = 1;
                do {
                    String answer = scanner.nextLine();
                    String[] idsString = answer.split(" ");
                    int[] ids = new int[idsString.length];
                    for (int j = 0; j < ids.length; j++) {
                        ids[j] = Integer.parseInt(idsString[j]);
                    }
                    canGo = game.turn(player, ids);
                    if (canGo == 1) {
                        System.out.println("Incorrect step");
                    }
                    if (canGo == 2) {
                        System.out.println("You burned");
                        player.showCards();
                    }
                } while (canGo != 0);
            }
        }
        shownEverybodysCard();
    }

    public static void shownEverybodysCard() {
        for (Player player : game.players) {
            player.showCards();
            System.out.println();
        }
    }
}
