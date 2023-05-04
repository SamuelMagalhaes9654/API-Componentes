package APIComponentes.Projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import APIComponentes.Projeto.domain.ComponenteReserva;

@Repository

public interface ComponenteReservaRepository extends JpaRepository<ComponenteReserva, Long> {
     
}