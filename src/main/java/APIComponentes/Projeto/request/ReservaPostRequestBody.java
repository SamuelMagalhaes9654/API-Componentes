package APIComponentes.Projeto.request;

import java.sql.Date;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @NoArgsConstructor
// @AllArgsConstructor
@Data
public class ReservaPostRequestBody {
    private String emailUsuario;
    private Long idComponenteReserva;
    private String materia;
    private Date data;//ta salvando o dia -1
    private String horario;

    
}