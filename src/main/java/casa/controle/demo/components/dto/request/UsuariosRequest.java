package casa.controle.demo.components.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuariosRequest(
        @NotBlank String nome,
        @NotBlank @Email String email
) {}