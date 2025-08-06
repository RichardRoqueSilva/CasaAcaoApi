package casa.controle.demo.components.service;

import casa.controle.demo.components.dto.request.ItemListaRequestDTO;
import casa.controle.demo.components.dto.request.ListasRequest;
import casa.controle.demo.components.dto.response.ListasResponse;
import casa.controle.demo.components.exception.ResourceNotFoundException;
import casa.controle.demo.components.mapper.ListasMapper;
import casa.controle.demo.components.model.*;
import casa.controle.demo.components.repository.ListasRepository;
import casa.controle.demo.components.repository.ProdutosRepository;
import casa.controle.demo.components.repository.UsuariosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListasService {

    private final ListasRepository listasRepository;
    private final UsuariosRepository usuariosRepository;
    private final ProdutosRepository produtosRepository;

    @Transactional
    public ListasResponse create(ListasRequest dto) {
        Usuarios usuario = usuariosRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + dto.usuarioId()));

        Listas lista = new Listas();
        lista.setNome(dto.nome());
        lista.setUsuario(usuario);

        return ListasMapper.toResponseDTO(listasRepository.save(lista));
    }

    @Transactional
    public ListasResponse findById(Integer id) {
        return listasRepository.findById(id)
                .map(ListasMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada com id: " + id));
    }

    @Transactional
    public List<ListasResponse> findAll() {
        return listasRepository.findAll().stream()
                .map(ListasMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ListasResponse addItem(Integer listaId, ItemListaRequestDTO itemDto) {
        Listas lista = listasRepository.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada com id: " + listaId));

        Produtos produto = produtosRepository.findById(itemDto.produtoId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + itemDto.produtoId()));

        ListaProdutoId itemId = new ListaProdutoId(listaId, itemDto.produtoId());

        // Verifica se o item já existe para atualizar a quantidade, ou cria um novo
        ListaProdutos item = lista.getItens().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElse(new ListaProdutos());

        item.setId(itemId);
        item.setLista(lista);
        item.setProduto(produto);
        item.setQuantidade(itemDto.quantidade());

        lista.getItens().add(item); // O Set garante que não haverá duplicatas

        return ListasMapper.toResponseDTO(listasRepository.save(lista));
    }

    @Transactional
    public void removeItem(Integer listaId, Integer produtoId) {
        Listas lista = listasRepository.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada com id: " + listaId));

        boolean removed = lista.getItens().removeIf(item -> item.getProduto().getId().equals(produtoId));
        if(!removed){
            throw new ResourceNotFoundException("Produto com id " + produtoId + " não encontrado na lista " + listaId);
        }

        listasRepository.save(lista); // orphanRemoval=true fará a mágica no banco
    }
}
