package casa.controle.demo.components.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutosRequest(
        @NotBlank String nome,
        @NotNull Integer categoriaId
) {}
