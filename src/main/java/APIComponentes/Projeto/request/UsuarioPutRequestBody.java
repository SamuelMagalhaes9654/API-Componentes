package APIComponentes.Projeto.request;

import lombok.Data;

@Data
public class UsuarioPutRequestBody {
    private Long id;
    private String nome;
    private String email;
    private String senha;
}
