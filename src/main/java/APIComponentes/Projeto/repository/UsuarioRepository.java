package APIComponentes.Projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import APIComponentes.Projeto.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}