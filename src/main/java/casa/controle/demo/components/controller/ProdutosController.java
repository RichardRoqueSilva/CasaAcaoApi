package casa.controle.demo.components.controller;

import casa.controle.demo.components.dto.request.ProdutosRequest;
import casa.controle.demo.components.dto.response.ProdutosResponse;
import casa.controle.demo.components.service.ProdutosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutosController {

    private final ProdutosService produtosService;

    /**
     * Endpoint para criar um novo produto.
     * O corpo da requisição deve conter o nome do produto e o ID da sua categoria.
     *
     * @param dto O DTO com os dados do produto.
     * @return O produto recém-criado com status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<ProdutosResponse> create(@RequestBody @Valid ProdutosRequest dto) {
        ProdutosResponse createdProduto = produtosService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduto);
    }

    /**
     * Endpoint para listar todos os produtos cadastrados no sistema.
     * A resposta incluirá os detalhes da categoria de cada produto.
     *
     * @return Uma lista com todos os produtos.
     */
    @GetMapping
    public ResponseEntity<List<ProdutosResponse>> findAll() {
        List<ProdutosResponse> produtos = produtosService.findAll();
        return ResponseEntity.ok(produtos);
    }

    // --- Endpoints Adicionais (Opcionais) ---
    // Você poderia facilmente adicionar endpoints para buscar, atualizar e deletar um produto.
    // A lógica no ProdutoService precisaria ser expandida para suportá-los.

    /*
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> findById(@PathVariable Integer id) {
        ProdutoResponseDTO produto = produtoService.findById(id); // Implementar no service
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Integer id, @RequestBody @Valid ProdutoRequestDTO dto) {
        ProdutoResponseDTO updatedProduto = produtoService.update(id, dto); // Implementar no service
        return ResponseEntity.ok(updatedProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.delete(id); // Implementar no service
        return ResponseEntity.noContent().build();
    }
    */
}
