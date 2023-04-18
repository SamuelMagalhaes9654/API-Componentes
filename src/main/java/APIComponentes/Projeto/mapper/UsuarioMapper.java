package APIComponentes.Projeto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import APIComponentes.Projeto.domain.Usuario;
import APIComponentes.Projeto.request.UsuarioPostRequestBody;
import APIComponentes.Projeto.request.UsuarioPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {
    public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    public abstract Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody);

    public abstract Usuario toUsuario(UsuarioPutRequestBody usuarioPostRequestBody);
}