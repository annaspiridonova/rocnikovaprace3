package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.services.GameService;
import cz.gyarab3e.rocnikovaprace3.services.GameServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;
    public GameController(GameService gameService){

        this.gameService = gameService;
    }
    @PostMapping("/start")
    public void startGame(){
        gameService.startGameWithCode();
    }
    public void joinGame(){

    }
}
