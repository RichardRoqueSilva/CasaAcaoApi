package casa.controle.demo.components.service;

import casa.controle.demo.components.dto.request.CategoriasRequest;
import casa.controle.demo.components.dto.response.CategoriasResponse;
import casa.controle.demo.components.exception.ResourceNotFoundException;
import casa.controle.demo.components.mapper.CategoriasMapper;
import casa.controle.demo.components.model.Categorias;
import casa.controle.demo.components.repository.CategoriasRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Injeção de dependência via construtor com Lombok
public class CategoriasService {

    private final CategoriasRepository categoriaRepository;

    @Transactional
    public CategoriasResponse create(CategoriasRequest dto) {
        Categorias categoria = CategoriasMapper.toEntity(dto);
        return CategoriasMapper.toResponseDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriasResponse findById(Integer id) {
        return categoriaRepository.findById(id)
                .map(CategoriasMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com o id: " + id));
    }

    @Transactional
    public List<CategoriasResponse> findAll() {
        return categoriaRepository.findAll().stream()
                .map(CategoriasMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
