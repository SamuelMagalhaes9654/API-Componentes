package APIComponentes.Projeto.request;

import lombok.Data;

@Data
public class ComponentePutRequestBody {
    private Long id;
    private String nome;
    private String descricao;
    private Long quantidade;
    private String local;
}
