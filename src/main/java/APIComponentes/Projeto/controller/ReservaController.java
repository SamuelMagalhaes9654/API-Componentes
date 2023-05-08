package APIComponentes.Projeto.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import APIComponentes.Projeto.domain.Componente;
import APIComponentes.Projeto.domain.Reserva;
import APIComponentes.Projeto.request.ReservaPostRequestBody;
import APIComponentes.Projeto.request.ReservaPutRequestBody;
import APIComponentes.Projeto.service.ReservaService;
import APIComponentes.Projeto.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@RestController
@RequestMapping("reservas")
@Log4j2
@RequiredArgsConstructor

public class ReservaController {
    private final DateUtil dateUtil;
    private final ReservaService reservaService;

    @GetMapping
    public ResponseEntity<Page<Reserva>> list(Pageable pageable){
        //log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(reservaService.listAll(pageable));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable long id){
        return ResponseEntity.ok(reservaService.findByIdOrThrowBadRequestException(id));
    }

    //findbyid que vai pegar as informações do usuario que estiver logado
    @GetMapping(path = "by-id/{id}")
    public ResponseEntity<Reserva> findByIdAutenticationPrincipal(@PathVariable long id,
                                                                    @AuthenticationPrincipal UserDetails userDetails){
        //log.info(userDetails);
        return ResponseEntity.ok(reservaService.findByIdOrThrowBadRequestException(id));
    }

    
    @GetMapping(path = "/find-user")
    public ResponseEntity<List<Reserva>> findByUsuario(@RequestParam String emailUsuario){
        return ResponseEntity.ok(reservaService.findByemailUsuario(emailUsuario));
    }

    @PostMapping
    public ResponseEntity<Reserva> save(@AuthenticationPrincipal UserDetails userDetails ,@RequestBody ReservaPostRequestBody reservaPostRequestBody){
        String email = userDetails.getUsername(); // obtém o username do usuario autenticado
        reservaPostRequestBody.setEmailUsuario(email);
        //log.info(userDetails);
        return new ResponseEntity<>(reservaService.save(reservaPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        reservaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ReservaPutRequestBody reservaPutRequestBody){
        reservaService.replace(reservaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}