package APIComponentes.Projeto.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import APIComponentes.Projeto.domain.Componente;

//@Repository

    
public interface ComponenteRepository {
    List<Componente> listAll();
}
    

