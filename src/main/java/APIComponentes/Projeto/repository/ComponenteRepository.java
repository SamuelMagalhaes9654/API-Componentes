package APIComponentes.Projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import APIComponentes.Projeto.domain.Componente;

//@Repository

    
public interface ComponenteRepository extends JpaRepository<Componente, Long> {
    
}
    

