package casa.controle.demo.components.service;

import casa.controle.demo.components.dto.request.ProdutosRequest;
import casa.controle.demo.components.dto.response.ProdutosResponse;
import casa.controle.demo.components.exception.ResourceNotFoundException;
import casa.controle.demo.components.mapper.ProdutosMapper;
import casa.controle.demo.components.model.Categorias;
import casa.controle.demo.components.model.Listas;
import casa.controle.demo.components.model.Produtos;
import casa.controle.demo.components.repository.CategoriasRepository;
import casa.controle.demo.components.repository.ListasRepository;
import casa.controle.demo.components.repository.ProdutosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutosService {

    private final ProdutosRepository produtosRepository;
    private final CategoriasRepository categoriasRepository;

    @Transactional
    public ProdutosResponse create(ProdutosRequest dto) {
        Categorias categoria = categoriasRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada para o id: " + dto.categoriaId()));

        Produtos produto = new Produtos();
        produto.setNome(dto.nome());
        produto.setCategoria(categoria);

        return ProdutosMapper.toResponseDTO(produtosRepository.save(produto));
    }

    @Transactional
    public List<ProdutosResponse> findAll() {
        return produtosRepository.findAll().stream()
                .map(ProdutosMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public ProdutosResponse update(Integer id, ProdutosRequest dto) {
        Produtos produto = produtosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));

        Categorias categoria = categoriasRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com id: " + dto.categoriaId()));

        produto.setNome(dto.nome());
        produto.setCategoria(categoria);

        return ProdutosMapper.toResponseDTO(produtosRepository.save(produto));
    }

    @Transactional
    public void delete(Integer id) {
        if (!produtosRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com id: " + id);
        }
        // O banco de dados (ON DELETE RESTRICT padrão) vai impedir a exclusão se o produto
        // estiver em alguma lista. Se quiser uma mensagem mais amigável, você precisaria
        // de um `listaProdutoRepository.existsByProdutoId(id)`.
        produtosRepository.deleteById(id);
    }
}
