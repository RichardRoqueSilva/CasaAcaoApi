package casa.controle.demo.components.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoriasRequest(
        @NotBlank String nome,
        String descricao
) {}