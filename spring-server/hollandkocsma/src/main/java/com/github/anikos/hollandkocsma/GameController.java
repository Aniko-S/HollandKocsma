package com.github.anikos.hollandkocsma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}