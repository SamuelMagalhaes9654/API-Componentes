package APIComponentes.Projeto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import APIComponentes.Projeto.domain.Reserva;
import APIComponentes.Projeto.request.ReservaPostRequestBody;
import APIComponentes.Projeto.request.ReservaPutRequestBody;

    
@Mapper(componentModel = "spring" )
public abstract class ReservaMapper {
    public static final ReservaMapper INSTANCE = Mappers.getMapper(ReservaMapper.class);

    //converter os ResquestBody para Reserva
    public abstract Reserva toReserva(ReservaPostRequestBody reservaPostRequestBody);

    public abstract Reserva toReserva(ReservaPutRequestBody reservaPutRequestBody);

}

