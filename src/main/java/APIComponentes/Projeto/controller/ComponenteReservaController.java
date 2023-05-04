package APIComponentes.Projeto.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import APIComponentes.Projeto.domain.ComponenteReserva;
import APIComponentes.Projeto.request.ComponenteReservaPostRequestBody;
import APIComponentes.Projeto.request.ComponenteReservaPutRequestBody;
import APIComponentes.Projeto.service.ComponenteReservaService;
import APIComponentes.Projeto.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("componentereservas")
@Log4j2
@RequiredArgsConstructor

public class ComponenteReservaController {
    private final DateUtil dateUtil;
    private final ComponenteReservaService componenteReservaService;

    @GetMapping
    public ResponseEntity<Page<ComponenteReserva>> list(Pageable pageable){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(componenteReservaService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ComponenteReserva> findById(@PathVariable long id){
        return ResponseEntity.ok(componenteReservaService.findByIdOrThrowBadRequestException(id));
    }

    //findbyid que vai pegar as informações do usuario que estiver logado
    @GetMapping(path = "by-id/{id}")
    public ResponseEntity<ComponenteReserva> findByIdAutenticationPrincipal(@PathVariable long id,
                                                                    @AuthenticationPrincipal UserDetails userDetails){
        log.info(userDetails);
        return ResponseEntity.ok(componenteReservaService.findByIdOrThrowBadRequestException(id));
    }


    @PostMapping
    public ResponseEntity<ComponenteReserva> save(@AuthenticationPrincipal UserDetails userDetails ,@RequestBody ComponenteReservaPostRequestBody componenteReservaPostRequestBody){
        // String email = userDetails.getUsername(); // obtém o username do usuario autenticado
        // ComponenteReservaPostRequestBody.setEmailUsuario(email);
        // log.info(userDetails);
         return new ResponseEntity<>(componenteReservaService.save(componenteReservaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        componenteReservaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ComponenteReservaPutRequestBody componenteReservaPutRequestBody){
        componenteReservaService.replace(componenteReservaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}