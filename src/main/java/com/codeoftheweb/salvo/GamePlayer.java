package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*; // importa automatico
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Entity
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch  = FetchType.EAGER )
    @JoinColumn(name= "game_id") //me almacena datos en una columna de database
    private Game game;


    @ManyToOne(fetch  = FetchType.EAGER )
    @JoinColumn(name= "player_id")
    private Player player;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gamePlayer")
    private Set<Ship> ships;

    @OneToMany(mappedBy="gamePlayer", fetch=FetchType.EAGER) //
    private Set<Salvo> salvos =  new HashSet<>();

    private LocalDateTime date;

    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.date = LocalDateTime.of(LocalDate.now(), LocalTime.now());

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public GamePlayer() {

    }

    public Long getId() {
        return id;
    }

    public Set<Ship> getShips() {
        return ships; // me trae los barcos
    }


    public Set<Salvo> getSalvos() {
        return salvos;
    }

    public Map<String, Object> getScoreDto(){
        Map<String , Object> dto =  new LinkedHashMap<>();
        dto.put("player", this.getPlayer().getId());
        Score score = this.getPlayer().getScore(this.getGame());
        if(score != null){
            dto.put("score",score.getScore());
            dto.put("finishDate",score.getFinishDate());
        }else {
            dto.put("score",null);
            dto.put("finishDate",null);
        }
        return dto;
    }
}
