package casa.controle.demo.components.mapper;

import casa.controle.demo.components.dto.request.ProdutosRequest;
import casa.controle.demo.components.dto.response.ProdutosResponse;
import casa.controle.demo.components.model.Listas;
import casa.controle.demo.components.model.Produtos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public class ProdutosMapper {
    public static ProdutosResponse toResponseDTO(Produtos entity) {
        return new ProdutosResponse(
                entity.getId(),
                entity.getNome(),
                CategoriasMapper.toResponseDTO(entity.getCategoria())
        );
    }
}