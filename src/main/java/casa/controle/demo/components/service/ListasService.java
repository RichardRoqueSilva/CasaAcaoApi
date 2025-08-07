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
    public ListasResponse updateItem(Integer listaId, Integer produtoId, ItemListaRequestDTO dto) {
        Listas lista = listasRepository.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada"));

        ListaProdutos item = lista.getItens().stream()
                .filter(i -> i.getProduto().getId().equals(produtoId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado na lista"));

        // Agora é uma atribuição direta, sem conversão!
        item.setQuantidade(dto.quantidade());
        item.setPrecoUnitario(dto.precoUnitario()); // O tipo do DTO e da Entidade agora são os mesmos.

        listasRepository.save(lista);

        return ListasMapper.toResponseDTO(lista);
    }

    @Transactional
    public ListasResponse addItem(Integer listaId, ItemListaRequestDTO itemDto) {
        Listas lista = listasRepository.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada"));

        Produtos produto = produtosRepository.findById(itemDto.produtoId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        // A lógica de adicionar o item já deve chamar um construtor ou setters.
        // Garanta que o preço seja passado corretamente.
        ListaProdutos item = new ListaProdutos(lista, produto, itemDto.quantidade());
        item.setPrecoUnitario(itemDto.precoUnitario()); // Atribuição direta

        lista.getItens().add(item);

        return ListasMapper.toResponseDTO(listasRepository.save(lista));
    }

    @Transactional
    public ListasResponse update(Integer id, ListasRequest dto) {
        Listas lista = listasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada com id: " + id));

        lista.setNome(dto.nome());
        Listas updatedLista = listasRepository.save(lista);
        return ListasMapper.toResponseDTO(updatedLista);
    }

    @Transactional
    public void delete(Integer id) {
        // Busca a entidade primeiro para garantir que ela exista e para que o Hibernate
        // a coloque no contexto de persistência, o que ativa a cascata.
        Listas lista = listasRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada para o id: ".concat(id.toString())));

        // Ao deletar o objeto da entidade, o Hibernate irá deletar os filhos primeiro
        // por causa da anotação @OneToMany(cascade = CascadeType.ALL).
        listasRepository.delete(lista);
    }

    @Transactional
    public void removeItem(Integer listaId, Integer produtoId) {
        Listas lista = listasRepository.findById(listaId)
                .orElseThrow(() -> new ResourceNotFoundException("Lista não encontrada"));

        boolean removed = lista.getItens().removeIf(item -> item.getProduto().getId().equals(produtoId));

        if (!removed) {
            throw new ResourceNotFoundException("Item não encontrado para remoção");
        }

        listasRepository.save(lista);
    }
}