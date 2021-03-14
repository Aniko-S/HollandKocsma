package com.github.anikos.hollandkocsma;

import com.github.anikos.hollandkocsma.entity.*;
import com.github.anikos.hollandkocsma.entityforsend.GameState;
import com.github.anikos.hollandkocsma.entityforsend.MachinesData;
import com.github.anikos.hollandkocsma.entityforsend.PlayersData;
import com.github.anikos.hollandkocsma.entityforsend.TablesData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameService {
    private static final Logger log = LoggerFactory.getLogger(GameService.class);
    private static final int inHands = 6;
    public static final int shown = 3;
    private static final int blind = 3;
    List<Game> games = new ArrayList<>();

    public GameState newGame(String name) {
        Player player = new Player(name);
        Game game = new Game(player, games.size());
        games.add(game);
        deal(game);
        log.info("Players: {}", game.players);
        return new GameState(
                game.id,
                new PlayersData(player),
                new MachinesData(game.machine),
                new TablesData(true, new HashSet<>(), "Choose three cards to put down face-up"),
                true,
                false,
                new ArrayList<>()
        );
    }

    public GameState putToShownCards(int gameId, ArrayList<Integer> ids) {
        Game game = getGameFromId(gameId);
        String message = "Your turn";
        boolean isValid = true;
        if (!canPutToShownCards(ids, game)) {
            message = "Incorrect step. You have to select three cards.";
            isValid = false;
            log.info("Can't put");
        } else {
            ids.forEach(id -> putACardFromTo(Deck.getCardFromId(id), game.player.handCards, game.player.shownCards));
            (game.machine).putToShownCards(this);
            log.info("Can put");
        }
        return new GameState(
                gameId,
                new PlayersData(game.player),
                new MachinesData(game.machine),
                new TablesData(!game.deck.isEmpty(), new HashSet<>(), message),
                isValid,
                false,
                new ArrayList<>()
        );
    }

    public GameState playersTurn(int gameId, List<Integer> answer) {
        Game game = getGameFromId(gameId);
        List<Integer> ids;
        if (answer.get(0) == -1) {
            log.info("Player put from blind cards");
            Card playedCard = game.player.blindCards.stream().findFirst().orElseThrow();
            ids = List.of(playedCard.getId());
        } else {
            ids = answer;
        }
        String message = "";
        boolean isFinished = false;
        int gameStatus = turn(game.player, ids, game);
        log.info("GameStatus: {}", gameStatus);
        if (gameStatus == 3) {
            isFinished = true;
        } else if (gameStatus == 0) {
            message = "Machine's turn";
            isFinished = true;
            if (ids.get(0) != 0 && answer.get(0) != -1) {
                game.pile.topCardSet = ids.stream().map(Deck::getCardFromId).collect(Collectors.toSet());
            } else if (answer.get(0) != -1) {
                game.pile.topCardSet = new HashSet<>();
            }
        } else if (gameStatus == 1) {
            message = "Incorrect step. You have to put one or more cards with the same value "
                    + createMessage(game)
                    + ", or you can put magic card (2, 5, 10). "
                    + "If you can't put any cards, you have to pick up the pile.";
        } else {
            message = "You burned. It's your turn again.";
            game.pile.topCardSet = new HashSet<>();
        }
        return new GameState(
                gameId,
                new PlayersData(game.player),
                new MachinesData(game.machine),
                new TablesData(!game.deck.isEmpty(), message, game.pile),
                isFinished,
                gameStatus == 2,
                ids
        );
    }

    public GameState machinesTurn(int gameId) {
        Game game = getGameFromId(gameId);
        List<Integer> ids;
        List<Integer> answer = (game.machine).put(game);
        if (answer.get(0) == -1) {
            log.info("Machine put from blind cards");
            Card playedCard = game.machine.blindCards.stream().findFirst().orElseThrow();
            ids = List.of(playedCard.getId());
        } else {
            ids = answer;
        }
        String message = "";
        boolean isFinished = false;
        int gameStatus = turn(game.machine, ids, game);
        log.info("GameStatus: {}", gameStatus);
        if (gameStatus == 3) {
            isFinished = true;
        } else if (gameStatus == 0) {
            message = "Your turn";
            isFinished = true;
            if (ids.get(0) != 0 && answer.get(0) != -1) {
                game.pile.topCardSet = ids.stream().map(Deck::getCardFromId).collect(Collectors.toSet());
            } else if (answer.get(0) != -1) {
                game.pile.topCardSet = new HashSet<>();
            }
        } else if (gameStatus == 1) {
            message = "Incorrect step";
        } else {
            message = "Machine burned. It's his turn again.";
            game.pile.topCardSet = new HashSet<>();
        }
        log.info("Machine's hand cards: {}", game.machine.handCards);
        return new GameState(
                gameId,
                new PlayersData(game.player),
                new MachinesData(game.machine),
                new TablesData(!game.deck.isEmpty(), message, game.pile),
                isFinished,
                gameStatus == 2,
                ids
        );
    }

    public void putACardFromTo(Card card, Set<Card> srcCards, Set<Card> targetCards) {
        srcCards.remove(card);
        targetCards.add(card);
    }

    private void deal(Game game) {
        log.info("Deal to: {}", game.players);
        for (Player player : game.players) {
            for (int i = 0; i < inHands; i++) {
                draw(player, game);
            }
            for (int i = 0; i < blind; i++) {
                int rand = (int) (Math.random() * game.deck.size());
                player.blindCards.add(game.deck.get(rand));
                game.deck.remove(rand);
            }
        }
    }

    private void draw(Player player, Game game) {
        int i = (int) (Math.random() * game.deck.size());
        player.handCards.add(game.deck.get(i));
        game.deck.remove(i);
    }

    public static Set<Integer> idsFromCardSet(Set<Card> cardSet) {
        return cardSet.stream().map(Card::getId).collect(Collectors.toSet());
    }

    private boolean canPutToShownCards(ArrayList<Integer> ids, Game game) {
        Set<Card> cards = ids.stream()
                .map(Deck::getCardFromId)
                .filter(card -> game.player.handCards.contains(card))
                .collect(Collectors.toSet());
        return cards.size() == shown;
    }

    private int turn(Player player, List<Integer> ids, Game game) {
        log.info("Cards number in deck: {}", game.deck.size());
        log.info("{} put:", player.getName());
        for (int id: ids) {
            if (id == 0) {
                log.info("0");
            } else {
                log.info("{}", Deck.getCardFromId(id));
            }
        }
        // pick up the pile
        if (ids.get(0) == 0) {
            log.info("Pick up the pile");
            player.handCards.addAll(game.pile.cardSet);
            game.pile = new Pile();
            return 0;
        }
        // play cards
        if (!isValidCardSet(ids, game)) {
            log.info("Invalid card set");
            return 1;
        }
        // play from hand
        if (!player.handCards.isEmpty()) {
            log.info("Play from hand");
            if (!isTheSetContainsAllCards(player.handCards, ids)) {
                log.info("Cards not found in hand");
                return 1;
            }
            return playKnownCards(ids, player, player.handCards, game);
        }
        // play from shown
        if (!player.shownCards.isEmpty()) {
            log.info("Play from shown");
            if (!isTheSetContainsAllCards(player.shownCards, ids)) {
                log.info("Cards not found in shown");
                return 1;
            }
            return playKnownCards(ids, player, player.shownCards, game);
        }
        // play from blind
        Card playedCard = Deck.getCardFromId(ids.get(0));
        if (ids.size() > 1 || !player.blindCards.contains(playedCard)) {
            log.info("Cards not found in blind or choose more blind card");
            return 1;
        }
        return playFromBlindCards(playedCard, player, game);
    }

    private boolean isValidCardSet(List<Integer> ids, Game game) {
        if (ids.size() < 1) {
            log.info("No selected card");
            return false;
        }
        if (game.pile.cardSet.isEmpty() && ids.get(0) == 0) {
            log.info("Can't pick up the pile because there is no pile");
            return false;
        }
        if (ids.get(0) != 0) {
            return areEquals(ids);
        }
        return true;
    }

    private boolean areEquals(List<Integer> ids) {
        Card firstCard = Deck.getCardFromId(ids.get(0));
        for (int i = 1; i < ids.size(); i++) {
            Card actualCard = Deck.getCardFromId(ids.get(i));
            if (!firstCard.getValue().equals(actualCard.getValue())) {
                log.info("Not equal cards");
                return false;
            }
        }
        return true;
    }

    private boolean isTheSetContainsAllCards(Set<Card> cardSet, List<Integer> ids) {
        Set<Card> cardSetFromIds = ids.stream()
                .map(Deck::getCardFromId)
                .collect(Collectors.toSet());
        return cardSet.containsAll(cardSetFromIds);
    }

    private int playKnownCards(List<Integer> ids, Player player, Set<Card> playCardSet, Game game) {
        if (game.pile.getTop() != null && !Deck.getCardFromId(ids.get(0)).canPutTo(game.pile.getTop())) {
            log.info("Can't put this card");
            return 1;
        }
        for (int id : ids) {
            Card playedCard = Deck.getCardFromId(id);
            Card previousCard = game.pile.getTop();
            game.pile.setTop(id);
            putACardFromTo(playedCard, playCardSet, game.pile.cardSet);
            if (player.handCards.size() < 3 && game.deck.size() > 0) {
                draw(player, game);
            }
            countEqualCards(playedCard, previousCard, game);
        }
        return isBurn(Deck.getCardFromId(ids.get(0)), game);
    }

    private int playFromBlindCards(Card card, Player player, Game game) {
        log.info("Blind card: {}", card);
        if (card.canPutTo(game.pile.getTop())) {
            Card previousCard = game.pile.getTop();
            putACardFromTo(card, player.blindCards, game.pile.cardSet);
            game.pile.setTop(card.getId());
            game.pile.topCardSet = new HashSet<>(Set.of(card));
            countEqualCards(card, previousCard, game);
            if (player.blindCards.isEmpty()) {
                return 3;
            }
            return isBurn(card, game);
        } else {
            putACardFromTo(card, player.blindCards, game.pile.cardSet);
            player.handCards.addAll(game.pile.cardSet);
            game.pile = new Pile();
            return 0;
        }
    }

    private void countEqualCards(Card playedCard, Card previousCard, Game game) {
        if (previousCard != null && playedCard.getValue().equals(previousCard.getValue())) {
            game.pile.incrementEqualCardsCounter();
        } else {
            game.pile.setEqualCardsCounter(1);
        }
    }

    private int isBurn(Card card, Game game) {
        if (card.getValue().equals(Deck.Value.TEN)) {
            return burn(game);
        }
        if (game.pile.getEqualCardsCounter() == 4) {
            return burn(game);
        }
        return 0;
    }

    private int burn(Game game) {
        game.pile = new Pile();
        return 2;
    }

    private String createMessage(Game game) {
        if (game.pile.getTop().getValue().equals(Deck.Value.FIVE)) {
            return "less than or equals to FIVE";
        }
        return "greater than or equals to " + game.pile.getTop().getValue().toString();
    }

    public Game getGameFromId(int id) {
        Optional<Game> goodGame = games.stream().filter(game ->
                game.id == id).findAny();
        if (goodGame.isPresent()) {
            return goodGame.get();
        } else {
            throw new RuntimeException("Incorrect game id");
        }
    }
}
