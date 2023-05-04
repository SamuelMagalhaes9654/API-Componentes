package APIComponentes.Projeto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import APIComponentes.Projeto.domain.ComponenteReserva;
import APIComponentes.Projeto.mapper.ComponenteReservaMapper;
import APIComponentes.Projeto.mapper.ReservaMapper;
import APIComponentes.Projeto.repository.ComponenteReservaRepository;
import APIComponentes.Projeto.request.ComponenteReservaPostRequestBody;
import APIComponentes.Projeto.request.ComponenteReservaPutRequestBody;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComponenteReservaService {
    private final ComponenteReservaRepository componenteReservaRepository;

    public Page<ComponenteReserva> listAll(Pageable pageable){
        return componenteReservaRepository.findAll(pageable);
        
    }


    public ComponenteReserva findByIdOrThrowBadRequestException(long id) {
        return componenteReservaRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reserva não encontrada"));
    }

    public ComponenteReserva save(ComponenteReservaPostRequestBody componenteReservaPostRequestBody) {
        return componenteReservaRepository.save(ComponenteReservaMapper.INSTANCE.toComponenteReserva(componenteReservaPostRequestBody));
        
    }

    public void delete(long id) {
        componenteReservaRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ComponenteReservaPutRequestBody componenteReservaPutRequestBody) {        
        ComponenteReserva savedComponenteReserva = findByIdOrThrowBadRequestException(componenteReservaPutRequestBody.getId());
        ComponenteReserva componenteReserva = ComponenteReservaMapper.INSTANCE.toComponenteReserva(componenteReservaPutRequestBody);
        componenteReserva.setId(savedComponenteReserva.getId());
        componenteReservaRepository.save(componenteReserva);
    }
}

