package APIComponentes.Projeto.service;
import APIComponentes.Projeto.domain.Componente;
import APIComponentes.Projeto.repository.ComponenteRepository;
import lombok.RequiredArgsConstructor;
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
    //private final ComponenteRepository componenteRepository;

    private static List<Componente> componentes;

    static{
        componentes = new ArrayList<>(List.of(
            new Componente(1L,"Resistor"),
            new Componente(2L,"Capacitor"),
            new Componente(3L, "Arduino")));
    }

    public List<Componente> listAll(){
        //return componenteRepository.listAll();
        return componentes;
    }

    public Componente findById(long id) {
        return componentes.stream()
            .filter(componente -> componente.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Componente não encontrado"));
    }

    public Componente save(Componente componente) {
        componente.setId(ThreadLocalRandom.current().nextLong(3,10000));
        componentes.add(componente);
        return componente;
    }

    public void delete(long id) {
        componentes.remove(findById(id));
    }

    public void replace(Componente componente) {
        delete(componente.getId());
        componentes.add(componente);
    }
}
