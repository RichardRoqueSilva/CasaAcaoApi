package casa.controle.demo.components.dto.response;

import java.time.Instant;
import java.util.Set;

public record ListasResponse(
        Integer id,
        String nome,
        Instant dataCriacao,
        Integer usuarioId,
        Set<ItemListaResponseDTO> itens
) {}
