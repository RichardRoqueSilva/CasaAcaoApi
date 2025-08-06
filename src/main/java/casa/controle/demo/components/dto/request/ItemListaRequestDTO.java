package casa.controle.demo.components.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemListaRequestDTO(
        @NotNull Integer produtoId,
        @NotNull @Positive Integer quantidade
) {}
