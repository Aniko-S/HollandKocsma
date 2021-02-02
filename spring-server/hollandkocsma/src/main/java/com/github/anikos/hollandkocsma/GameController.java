package com.github.anikos.hollandkocsma;

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

    @PostMapping("/begin/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public GameState newGame(@PathVariable String name) {
        return gameService.newGame(name);
    }

    @PostMapping("/toshown")
    public GameState putToShown(@RequestBody ArrayList<Integer> ids) {
        return gameService.putToShownCards(ids);
    }

    @PostMapping("/game")
    public GameState game(@RequestBody ArrayList<Integer> ids) {
        return gameService.turn(ids);
    }

    @GetMapping("/game")
    public GameState game() {
        return gameService.machinesTurn();
    }
}
