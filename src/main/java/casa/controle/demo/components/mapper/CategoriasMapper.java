package casa.controle.demo.components.mapper;

import casa.controle.demo.components.dto.request.CategoriasRequest;
import casa.controle.demo.components.dto.response.CategoriasResponse;
import casa.controle.demo.components.model.Categorias;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

public class CategoriasMapper {
    public static Categorias toEntity(CategoriasRequest dto) {
        Categorias categoria = new Categorias();
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        return categoria;
    }

    public static CategoriasResponse toResponseDTO(Categorias entity) {
        return new CategoriasResponse(entity.getId(), entity.getNome(), entity.getDescricao());
    }

    public static void updateEntityFromDTO(CategoriasRequest dto, Categorias entity) {
        entity.setNome(dto.nome());
        entity.setDescricao(dto.descricao());
    }
}
