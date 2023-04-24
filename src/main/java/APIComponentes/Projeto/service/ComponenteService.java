package APIComponentes.Projeto.service;
import APIComponentes.Projeto.domain.Componente;
import APIComponentes.Projeto.mapper.ComponenteMapper;
import APIComponentes.Projeto.repository.ComponenteRepository;
import APIComponentes.Projeto.request.ComponentePostRequestBody;
import APIComponentes.Projeto.request.ComponentePutRequestBody;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComponenteService {
    private final ComponenteRepository componenteRepository;

    public Page<Componente> listAll(Pageable pageable){
        return componenteRepository.findAll(pageable);
        
    }

    public List<Componente> findByNome(String nome){
        return componenteRepository.findByNome(nome);
        
    }

    public Componente findByIdOrThrowBadRequestException(long id) {
        return componenteRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Componente não encontrado"));
    }

    public Componente save(ComponentePostRequestBody componentePostRequestBody) {
        return componenteRepository.save(ComponenteMapper.INSTANCE.toComponente(componentePostRequestBody));
        
    }

    public void delete(long id) {
        componenteRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(ComponentePutRequestBody componentePutRequestBody) {        
        Componente savedComponente = findByIdOrThrowBadRequestException(componentePutRequestBody.getId());
        Componente componente = ComponenteMapper.INSTANCE.toComponente(componentePutRequestBody);
        componente.setId(savedComponente.getId());
        componenteRepository.save(componente);
    }
}
