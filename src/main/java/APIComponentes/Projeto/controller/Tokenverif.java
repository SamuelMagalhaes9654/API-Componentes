package APIComponentes.Projeto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tokenverif")
public class Tokenverif {
    
    @GetMapping
    public ResponseEntity verificarToken() {
        Boolean Auth = true;
        return new ResponseEntity<>(Auth, HttpStatus.CREATED);
    }
}
