package casa.controle.demo.components.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ListasRequest(
        @NotBlank String nome,
        @NotNull Integer usuarioId
) {}
