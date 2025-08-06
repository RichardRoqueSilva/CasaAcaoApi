package casa.controle.demo.components.controller;

import casa.controle.demo.components.dto.request.ItemListaRequestDTO;
import casa.controle.demo.components.dto.request.ListasRequest;
import casa.controle.demo.components.dto.response.ListasResponse;
import casa.controle.demo.components.model.Listas;
import casa.controle.demo.components.service.ListasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listas")
@RequiredArgsConstructor
public class ListasController {

    private final ListasService listasService;

    @PostMapping
    public ResponseEntity<ListasResponse> create(@RequestBody @Valid ListasRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(listasService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListasResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(listasService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ListasResponse>> findAll() {
        List<ListasResponse> listas = listasService.findAll();
        return ResponseEntity.ok(listas);
    }

    // Endpoint para adicionar um item a uma lista existente
    @PostMapping("/{listaId}/itens")
    public ResponseEntity<ListasResponse> addItemToLista(@PathVariable Integer listaId, @RequestBody @Valid ItemListaRequestDTO itemDto) {
        return ResponseEntity.ok(listasService.addItem(listaId, itemDto));
    }

    // Endpoint para remover um item de uma lista existente
    @DeleteMapping("/{listaId}/itens/{produtoId}")
    public ResponseEntity<Void> removeItemDaLista(@PathVariable Integer listaId, @PathVariable Integer produtoId) {
        listasService.removeItem(listaId, produtoId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ListasResponse> update(@PathVariable Integer id, @RequestBody @Valid ListasRequest dto) {
        ListasResponse updatedLista = listasService.update(id, dto);
        return ResponseEntity.ok(updatedLista);
    }

}
