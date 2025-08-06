package casa.controle.demo.components.dto.response;

import java.math.BigDecimal;

public record ItemListaResponseDTO(
        ProdutosResponse produto,
        Integer quantidade,
        BigDecimal precoUnitario,
        boolean comprado
) {}
