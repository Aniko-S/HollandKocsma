package com.github.anikos.hollandkocsma;

import com.github.anikos.hollandkocsma.entity.Player;
import com.github.anikos.hollandkocsma.entityforsend.GameState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("game")
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/single/begin/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public GameState gameWithMachine(@PathVariable String name) {
        return gameService.gameWithMachine(name);
    }

    @PostMapping("/multi/create/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMultiplayerGame(@PathVariable String name) {
        return gameService.createMultiplayerGame(name);
    }

    @PostMapping("/multi/{gameId}/{name}")
    public ArrayList<Player> join(@PathVariable String gameId, @PathVariable String name) {
        return gameService.join(gameId, name);
    }
/*
    @PostMapping("/toshown/{gameId}")
    public GameState putToShown(@PathVariable int gameId, @RequestBody ArrayList<Integer> ids) {
        return gameService.putToShownCards(gameId, ids);
    }

    @PostMapping("/game/{gameId}")
    public GameState game(@PathVariable int gameId, @RequestBody ArrayList<Integer> ids) {
        return gameService.playersTurn(gameId, ids);
    }

    @GetMapping("/game/{gameId}")
    public GameState game(@PathVariable int gameId) {
        return gameService.machinesTurn(gameId);
    }

    @GetMapping("/game/{gameId}/asc")
    public GameState orderByAsc(@PathVariable int gameId) {
        return gameService.orderByAsc(gameId);
    }

 */
}
