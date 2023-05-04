package APIComponentes.Projeto.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import APIComponentes.Projeto.domain.Reserva;

@Repository

    
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByemailUsuario(String emailUsuario); 
}
    