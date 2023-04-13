package APIComponentes.Projeto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import APIComponentes.Projeto.domain.Componente;
import APIComponentes.Projeto.request.ComponentePostRequestBody;
import APIComponentes.Projeto.request.ComponentePutRequestBody;

@Mapper(componentModel = "spring")
public abstract class ComponenteMapper {
    public static final ComponenteMapper INSTANCE = Mappers.getMapper(ComponenteMapper.class);

    //converter os ResquestBody para Componente
    public abstract Componente toComponente(ComponentePostRequestBody componentePostRequestBody);
    public abstract Componente toComponente(ComponentePutRequestBody componentePutRequestBody);

}
