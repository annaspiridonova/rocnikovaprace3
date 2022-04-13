package cz.gyarab3e.rocnikovaprace3.controller;

import cz.gyarab3e.rocnikovaprace3.jpa.Game;
import cz.gyarab3e.rocnikovaprace3.services.*;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

//game endpoints, receiving and handling client requests
@CrossOrigin
@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {

        this.gameService = gameService;
    }

    @GetMapping("/getWinningRate")
    public ResponseEntity<Integer> getWinningRate(@PathParam("username") String username) {

        return ResponseEntity.ok(gameService.getWinningRate(username));
    }

    @PostMapping("/start")
    public ResponseEntity<GameHolder> startGame() {
        Game game = gameService.startGameWithCode();
        return ResponseEntity.ok(new GameHolder(game.getId(), game.getPlayingCode(), game.getStatus(), null));
    }

    @PostMapping("/join")
    public ResponseEntity<GameHolder> joinGame(@RequestBody CodeHolder code) throws ValidationException, NotFoundException {
        Game game = gameService.joinGame(code.getCode());
        return ResponseEntity.ok(new GameHolder(game.getId(), game.getPlayingCode(), game.getStatus(), null));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GameHolder> getGame(@PathVariable Long id) {
        Optional<Game> game = gameService.getGame(id);
        return game.map(value -> ResponseEntity.ok(new GameHolder(value))).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/getUsersBoard")
    public ResponseEntity<BoardHolder> getUsersBoard(@Param("id") Long id, @Param("username") String username) throws NotFoundException {

        return ResponseEntity.ok(new BoardHolder(id, gameService.returnUsersBoard(id, username)));

    }

    @GetMapping(value = "/getOpponentsBoard")
    public ResponseEntity<BoardHolder> getOpponentsBoard(@Param("id") Long id, @Param("username") String username) throws NotFoundException {

        return ResponseEntity.ok(new BoardHolder(id, gameService.returnOpponentsBoard(id, username)));

    }


    @PostMapping("/saveBoard")
    public ResponseEntity<Void> saveBoard(@RequestBody BoardHolder boardholder) throws ValidationException, NotFoundException {
        gameService.saveBoard(boardholder.getId(), boardholder.getBoard());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/getMove")
    public ResponseEntity<MoveStatus> move(@RequestBody MoveHolder moveHolder) throws MoveException, NotFoundException {
        return ResponseEntity.ok(gameService.move(moveHolder.id, moveHolder.x, moveHolder.y));


    }

    @GetMapping("/abandon")
    public ResponseEntity<Void> abandon(@Param("id") Long id) throws AccessDeniedExceptions, NotFoundException {

        gameService.abandon(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}