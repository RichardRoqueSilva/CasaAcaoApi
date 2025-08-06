package casa.controle.demo.components.mapper;

import casa.controle.demo.components.dto.request.ListasRequest;
import casa.controle.demo.components.dto.response.ListasResponse;
import casa.controle.demo.components.model.Listas;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

public class ListasMapper {
    public static ListasResponse toResponseDTO(Listas entity) {
        return new ListasResponse(
                entity.getId(),
                entity.getNome(),
                entity.getDataCriacao(),
                entity.getUsuario().getId(),
                entity.getItens().stream()
                        .map(ItemListaMapper::toResponseDTO)
                        .collect(Collectors.toSet())
        );
    }
}
