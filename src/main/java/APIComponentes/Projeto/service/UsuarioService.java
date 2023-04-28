package APIComponentes.Projeto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import APIComponentes.Projeto.domain.Usuario;
import APIComponentes.Projeto.mapper.UsuarioMapper;
import APIComponentes.Projeto.repository.UsuarioRepository;
import APIComponentes.Projeto.request.UsuarioPostRequestBody;
import APIComponentes.Projeto.request.UsuarioPutRequestBody;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = findByEmail(username);
        return new User(
            usuario.getUsername(),
            usuario.getPassword(),
            usuario.getAuthorities());
    }
    /*
     * return new User(
            usuario.getUsername(),
             usuario.getPassword(),
              usuario.isEnabled(),
               usuario.isAccountNonExpired(),
                usuario.isCredentialsNonExpired(),
                 usuario.isAccountNonLocked(),
                  usuario.getAuthorities());
     */

    // @Override
    // public UserDetails loadUserByUsername(String username){
    //     return Optional.ofNullable(usuarioRepository.findByEmail(username))
    //             .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));
    // }
    
    public Usuario findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public Page<Usuario> listAll(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    public List<Usuario> findByNome(String nome){
        return usuarioRepository.findByNome(nome);
        
    }

    public Usuario findByIdOrThrowBadRequestException(long id) {
        return usuarioRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario não encontrado"));
    }

    public Usuario save(UsuarioPostRequestBody usuarioPostRequestBody){
        return usuarioRepository.save(UsuarioMapper.INSTANCE.toUsuario(usuarioPostRequestBody));
    }

    public void delete(long id) {
        usuarioRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(UsuarioPutRequestBody usuarioPutRequestBody) {
        Usuario savedusuario = findByIdOrThrowBadRequestException(usuarioPutRequestBody.getId());
        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioPutRequestBody);
        usuario.setId(savedusuario.getId());
        usuarioRepository.save(usuario);
    }

    

}