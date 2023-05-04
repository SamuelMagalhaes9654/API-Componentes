package APIComponentes.Projeto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import APIComponentes.Projeto.domain.ComponenteReserva;
import APIComponentes.Projeto.request.ComponenteReservaPostRequestBody;
import APIComponentes.Projeto.request.ComponenteReservaPutRequestBody;

@Mapper(componentModel = "spring" )
public abstract class ComponenteReservaMapper {
    public static final ComponenteReservaMapper INSTANCE = Mappers.getMapper(ComponenteReservaMapper.class);

    //converter os ResquestBody para Reserva
    public abstract ComponenteReserva toComponenteReserva(ComponenteReservaPostRequestBody componenteReservaPostRequestBody);

    public abstract ComponenteReserva toComponenteReserva(ComponenteReservaPutRequestBody componenteReservaPutRequestBody);

}

