package casa.controle.demo.components.dto.response;

public record ProdutosResponse(
        Integer id,
        String nome,
        CategoriasResponse categoria
) {}
