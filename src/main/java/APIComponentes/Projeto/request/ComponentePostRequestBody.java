package APIComponentes.Projeto.request;

import lombok.Data;

@Data
public class ComponentePostRequestBody {
    private String nome;
    private String descricao;
    private Long quantidade;
    private String local;
}
