package APIComponentes.Projeto.controller;

import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import APIComponentes.Projeto.domain.Usuario;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("tokenauth")
@Log4j2
@RequiredArgsConstructor
public class TokenAuthController {
    
    @PostMapping
    public ResponseEntity<String> token(@RequestBody Usuario usuario) {
    
    String token = usuario.getEmail()+":"+usuario.getPassword();
    
    // Converter o token para Base64
    String tokenBase64 = Base64.getEncoder().encodeToString(token.getBytes());
    
    // Retornar o token em Base64
    return new ResponseEntity<>(tokenBase64, HttpStatus.CREATED);
}
}
