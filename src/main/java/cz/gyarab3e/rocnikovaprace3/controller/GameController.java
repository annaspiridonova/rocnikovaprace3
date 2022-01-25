package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.jpa.Game;
import cz.gyarab3e.rocnikovaprace3.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<GameHolder> startGame(){
        Game game = gameService.startGameWithCode();
        return ResponseEntity.ok(new GameHolder(game.getId(),game.getPlayingCode()));
    }
    @PostMapping("/join")
    public ResponseEntity<GameHolder> joinGame(@RequestBody String code){
        Game game=gameService.joinGame(code);
        return ResponseEntity.ok(new GameHolder(game.getId(),game.getPlayingCode()));
    }

    @PostMapping("/saveBoard")
    public ResponseEntity<Void> saveBoard(@RequestBody BoardHolder boardholder){
        gameService.saveBoard(boardholder.id, boardholder.getBoard());
       return new ResponseEntity<>( HttpStatus.OK );
    }
}
