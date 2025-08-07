package casa.controle.demo.components.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemListaRequestDTO(
        @NotNull Integer produtoId,
        @Positive Integer quantidade,
        @Positive BigDecimal precoUnitario
) {}
