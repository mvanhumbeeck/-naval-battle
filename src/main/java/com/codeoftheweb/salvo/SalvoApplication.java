package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(PlayerRepository repository, GameRepository repository2, GamePlayerRepository repository3, ShipRepository shipRepository, SalvoRepository salvoRepository4, ScoreRepository scoreRepository5) {
		return (args) -> {
//Player
			Player player1 = repository.save(new Player("play1@hotmail.com"));
			Player player2 = repository.save(new Player("play2@hotmail.com")); //solo muestra jugador
			Player player3 = repository.save(new Player("play3@hotmail.com"));
			Player player4 = repository.save(new Player("play4@hotmail.com"));
//Game
			Game game1 = repository2.save(new Game(LocalDateTime.now())); //solo muestra el juego
			Game game2 = repository2.save(new Game(LocalDateTime.now()));
//Gameplayer
			GamePlayer gameplayer1 = repository3.save(new GamePlayer(game1, player1)); //muestra ambas
			GamePlayer gameplayer2 = repository3.save(new GamePlayer(game1, player2));
			GamePlayer gameplayer3 = repository3.save(new GamePlayer(game2, player3));
			GamePlayer gameplayer4 = repository3.save(new GamePlayer(game2, player4));
//ships
			Ship ship1 = shipRepository.save(new Ship("DESTROYER", List.of("B2", "B3", "B4", "B5", "B6"), gameplayer1));
			Ship ship2 = shipRepository.save(new Ship("BATTLE SHIP", List.of("C1", "C2", "C3", "C4"), gameplayer2));
//salvoes
			Salvo salvo1 = salvoRepository4.save(new Salvo(1,List.of("D2","D4","E6","D7","E8"), gameplayer1));
			Salvo salvo2 = salvoRepository4.save(new Salvo(1,List.of("B2","G4","F6","H7","F8"), gameplayer2));

			Score score1 = new Score(player1,game1,0.5,LocalDateTime.now());
			Score score2 = new Score(player2,game1,0.5,LocalDateTime.now());
			Score score3 = new Score(player3,game2,0,LocalDateTime.now());
			Score score4 = new Score(player4,game2,1,LocalDateTime.now());

			scoreRepository5.save(score1);
			scoreRepository5.save(score2);
			scoreRepository5.save(score3);
			scoreRepository5.save(score4);
		};
	}

}