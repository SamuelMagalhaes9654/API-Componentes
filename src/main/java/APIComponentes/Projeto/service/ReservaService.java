package APIComponentes.Projeto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import APIComponentes.Projeto.domain.Reserva;
import APIComponentes.Projeto.mapper.ComponenteMapper;
import APIComponentes.Projeto.mapper.ReservaMapper;
import APIComponentes.Projeto.repository.ReservaRepository;
import APIComponentes.Projeto.request.ComponentePostRequestBody;
import APIComponentes.Projeto.request.ReservaPostRequestBody;
import APIComponentes.Projeto.request.ReservaPutRequestBody;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public List<Reserva> listAll(){
        return reservaRepository.findAll();
    }

    public List<Reserva> findByemailUsuario(String emailUsuario){
        return reservaRepository.findByemailUsuario(emailUsuario);
        
    }

    public Reserva findByIdOrThrowBadRequestException(long id) {
        return reservaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reserva não encontrada"));
    }

    public Reserva save(ReservaPostRequestBody reservaPostRequestBody) {
        return reservaRepository.save(ReservaMapper.INSTANCE.toReserva(reservaPostRequestBody));
        
    }

    public void delete(long id) {
        reservaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ReservaPutRequestBody reservaPutRequestBody) {        
        Reserva savedReserva = findByIdOrThrowBadRequestException(reservaPutRequestBody.getId());
        Reserva reserva = ReservaMapper.INSTANCE.toReserva(reservaPutRequestBody);
        reserva.setId(savedReserva.getId());
        reservaRepository.save(reserva);
    }
}

