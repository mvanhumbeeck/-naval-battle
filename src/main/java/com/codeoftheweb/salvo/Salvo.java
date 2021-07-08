package com.codeoftheweb.salvo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

    @Entity
    public class Salvo {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
        @GenericGenerator(name = "native", strategy = "native")
        private Long id;

        private int turn; // turno del jugador

        @ElementCollection
        @Column(name = "SalvoLocations") // nombre de la base de datos
        private List<String> locations; // lista de locaciones

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "gamePlayer_id")
        private GamePlayer gamePlayer ;

        public Salvo() {}

        public Salvo(int turn, List<String> locations, GamePlayer gamePlayer) {
            this.turn = turn;
            this.locations = locations;
            this.gamePlayer = gamePlayer;
        }

        public int getTurn() { return turn; }

        public void setTurn(int turn) {
            this.turn = turn;
        }

        public List<String> getLocations() {
            return locations;
        }

        public void setLocations(List<String> locations) {
            this.locations = locations;
        }

        public GamePlayer getGamePlayer() {
            return gamePlayer;
        }

        public void setGamePlayer(GamePlayer gamePlayer) {
            this.gamePlayer = gamePlayer;
        }

        public Long getId() {
            return id;
        }


    }




