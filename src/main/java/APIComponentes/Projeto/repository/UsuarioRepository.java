package APIComponentes.Projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import APIComponentes.Projeto.domain.Usuario;


import java.util.List;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNome(String nome); 
}