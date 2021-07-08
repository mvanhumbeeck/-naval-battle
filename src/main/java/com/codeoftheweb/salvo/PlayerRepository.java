package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
interface PlayerRepository extends JpaRepository<Player, Long> {

}
