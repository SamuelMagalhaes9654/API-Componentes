package APIComponentes.Projeto.request;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class ReservaPutRequestBody {
    private Long id;
    private String emailUsuario;
    private Long idComponenteReserva;
    private String materia;
    private Date data;
    private String horario;
}
