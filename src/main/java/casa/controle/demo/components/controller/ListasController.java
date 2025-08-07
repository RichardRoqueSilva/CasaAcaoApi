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
        return ResponseEntity.ok(listasService.findAll());
    }

    @PostMapping("/{listaId}/itens")
    public ResponseEntity<ListasResponse> addItemToLista(@PathVariable Integer listaId, @RequestBody @Valid ItemListaRequestDTO itemDto) {
        return ResponseEntity.ok(listasService.addItem(listaId, itemDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListasResponse> update(@PathVariable Integer id, @RequestBody @Valid ListasRequest dto) {
        return ResponseEntity.ok(listasService.update(id, dto));
    }

//    @DeleteMapping("/{listaId}/itens/{produtoId}")
//    public ResponseEntity<Void> removeItemDaLista(@PathVariable Integer listaId, @PathVariable Integer produtoId) {
//        listasService.removeItem(listaId, produtoId);
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        listasService.delete(id);
        // Retorna um status 204 No Content, que é o padrão HTTP para uma exclusão bem-sucedida.
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{listaId}/itens/{produtoId}")
    public ResponseEntity<ListasResponse> updateItem(
            @PathVariable Integer listaId,
            @PathVariable Integer produtoId,
            // Agora esperamos o DTO existente
            @RequestBody @Valid ItemListaRequestDTO dto) {
        ListasResponse listaAtualizada = listasService.updateItem(listaId, produtoId, dto);
        return ResponseEntity.ok(listaAtualizada);
    }

    @DeleteMapping("/{listaId}/itens/{produtoId}")
    public ResponseEntity<Void> removeItem(@PathVariable Integer listaId, @PathVariable Integer produtoId) {
        listasService.removeItem(listaId, produtoId);
        return ResponseEntity.noContent().build();
    }
}
