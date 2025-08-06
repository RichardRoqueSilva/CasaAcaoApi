package casa.controle.demo.components.service;

import casa.controle.demo.components.dto.request.UsuariosRequest;
import casa.controle.demo.components.dto.response.UsuariosResponse;
import casa.controle.demo.components.exception.BusinessException;
import casa.controle.demo.components.exception.ResourceNotFoundException;
import casa.controle.demo.components.mapper.UsuariosMapper;
import casa.controle.demo.components.model.Usuarios;
import casa.controle.demo.components.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuariosService {

    private final UsuariosRepository usuariosRepository;

    @Transactional
    public UsuariosResponse create(UsuariosRequest dto) {
        Usuarios usuario = UsuariosMapper.toEntity(dto);
        Usuarios savedUsuario = usuariosRepository.save(usuario);
        return UsuariosMapper.toResponseDTO(savedUsuario);
    }

    @Transactional
    public List<UsuariosResponse> findAll() {
        return usuariosRepository.findAll().stream()
                .map(UsuariosMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public UsuariosResponse findById(Integer id) {
        return usuariosRepository.findById(id)
                .map(UsuariosMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));
    }

    @Transactional
    public UsuariosResponse update(Integer id, UsuariosRequest dto) {
        Usuarios usuario = usuariosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));

        UsuariosMapper.updateEntityFromDTO(dto, usuario);
        Usuarios updatedUsuario = usuariosRepository.save(usuario);
        return UsuariosMapper.toResponseDTO(updatedUsuario);
    }

    @Transactional
    public void delete(Integer id) {
        if (!usuariosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado com o id: " + id);
        }
        // Adicionar verificação se o usuário possui listas antes de deletar, se necessário.
        // Ex: if (listaRepository.existsByUsuarioId(id)) { throw new BusinessRuleException(...) }
        usuariosRepository.deleteById(id);
    }
}
