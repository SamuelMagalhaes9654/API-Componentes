package APIComponentes.Projeto.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import APIComponentes.Projeto.domain.Usuario;
import APIComponentes.Projeto.mapper.UsuarioMapper;
import APIComponentes.Projeto.repository.UsuarioRepository;
import APIComponentes.Projeto.request.UsuarioPostRequestBody;
import APIComponentes.Projeto.request.UsuarioPutRequestBody;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> listAll(){
        return usuarioRepository.findAll();
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