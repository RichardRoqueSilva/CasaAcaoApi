package casa.controle.demo.components.mapper;

import casa.controle.demo.components.dto.response.ItemListaResponseDTO;
import casa.controle.demo.components.model.ListaProdutos;

public class ItemListaMapper {
    public static ItemListaResponseDTO toResponseDTO(ListaProdutos entity) {
        return new ItemListaResponseDTO(
                ProdutosMapper.toResponseDTO(entity.getProduto()),
                entity.getQuantidade(),
                entity.getPrecoUnitario(),
                entity.isComprado()
        );
    }
}
