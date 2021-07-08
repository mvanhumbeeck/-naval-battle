package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository repository2;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

//esto hace referencia a las urls que me muestran en pantalla

    @GetMapping(value = "/games")
    public Map<String , Object> getListaGames() {
        Map<String, Object> dto = new LinkedHashMap<>();
        List<Game> games = repository2.findAll();
        dto.put("games", games.stream().map(game -> gameDTO(game)).collect(Collectors.toList()));
        return dto;
    }


    @RequestMapping("/game_view/{nn}") // hace referencia ide clase gameplayer
    public Map<String, Object> getGames(@PathVariable Long nn) {
        GamePlayer gamePlayer = gamePlayerRepository.findById(nn).get(); //devuelve un optional get: me devuelve object gameplayer
        Game game = gamePlayer.getGame();
        Map<String, Object> dto = gameDTO(game);
        dto.put("ships", gamePlayer.getShips().stream().map(ship -> shipDTO(ship)).collect(Collectors.toList()));
        dto.put("salvoes", game.getGamePlayerSet().stream().map(gp->gp.getSalvos()).flatMap(salvos -> salvos.stream()).map(salvo -> salvoDTO(salvo)).collect(Collectors.toList()));
        return dto;
    }

    private Map<String, Object> gameDTO(Game game) {
        Map<String, Object> dto = new LinkedHashMap<String, Object>();
        dto.put("id", game.getId());
        dto.put("created", game.getFecha());
        dto.put("gamePlayers", game.getGamePlayerSet().stream().map(this::gamePlayerDTO).collect(Collectors.toList()));
        dto.put("scores",game.getGamePlayerSet().stream().map(gp-> gp.getScoreDto()).collect(Collectors.toList()));
        return dto;
    }

    private Map<String, Object> playerDTO(Player player) {
        Map<String, Object> str = new LinkedHashMap<String, Object>();
        str.put("id", player.getId());
        str.put("email", player.getUserName());
        return str;
    }

    private Map<String, Object> gamePlayerDTO(GamePlayer gp) {
        Map<String, Object> str = new LinkedHashMap<String, Object>();
        str.put("id", gp.getId());
        str.put("player", playerDTO(gp.getPlayer()));
        return str;
    }

    private Map<String, Object> shipDTO(Ship ship) {
        Map<String, Object> str = new LinkedHashMap<>();
        str.put("type", ship.getType());
        str.put("locations", ship.getLocations());
        return str;
    }

    private Map<String, Object> salvoDTO(Salvo salvo) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("turn", salvo.getTurn());
        dto.put("player", salvo.getGamePlayer().getPlayer().getId());
        dto.put("locations", salvo.getLocations());
        return dto;
    }

    private Map<String , Object> makeSalvoDto(Salvo salvo){
        Map<String , Object> dto =  new LinkedHashMap<>();
        dto.put("turn",salvo.getTurn());
        dto.put("player",salvo.getGamePlayer().getPlayer().getId());
        dto.put("locations",salvo.getLocations());

        return dto;
    }

}

//@GetMapping("/api/employees/{id}")
//@ResponseBody
//public String getEmployeesById(@PathVariable String id) {
//    return "ID: " + id;
//}



