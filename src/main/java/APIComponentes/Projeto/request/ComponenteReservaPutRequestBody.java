package APIComponentes.Projeto.request;

import lombok.Data;

@Data
public class ComponenteReservaPutRequestBody {
    private Long id;
    private Long idComponente;
    private Long idReserva;
    
}
