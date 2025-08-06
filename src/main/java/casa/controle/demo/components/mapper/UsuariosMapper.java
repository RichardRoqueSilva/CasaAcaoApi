package casa.controle.demo.components.mapper;

import casa.controle.demo.components.dto.request.UsuariosRequest;
import casa.controle.demo.components.dto.response.UsuariosResponse;
import casa.controle.demo.components.model.Usuarios;
import org.mapstruct.Mapper;

public class UsuariosMapper {

    /**
     * Converte um DTO de requisição em uma Entidade Usuario.
     * @param dto O objeto com os dados de entrada.
     * @return Uma nova instância de Usuario.
     */
    public static Usuarios toEntity(UsuariosRequest dto) {
        Usuarios usuario = new Usuarios();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        return usuario;
    }

    /**
     * Converte uma Entidade Usuario em um DTO de resposta.
     * @param entity A entidade vinda do banco de dados.
     * @return Um DTO seguro para ser exposto na API.
     */
    public static UsuariosResponse toResponseDTO(Usuarios entity) {
        return new UsuariosResponse(entity.getId(), entity.getNome(), entity.getEmail());
    }

    /**
     * Atualiza uma entidade existente com dados de um DTO.
     * @param dto O objeto com os dados para atualização.
     * @param entity A entidade a ser atualizada.
     */
    public static void updateEntityFromDTO(UsuariosRequest dto, Usuarios entity) {
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
    }
}