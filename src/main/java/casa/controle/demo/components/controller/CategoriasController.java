package casa.controle.demo.components.controller;

import casa.controle.demo.components.dto.request.CategoriasRequest;
import casa.controle.demo.components.dto.response.CategoriasResponse;
import casa.controle.demo.components.mapper.CategoriasMapper;
import casa.controle.demo.components.model.Categorias;
import casa.controle.demo.components.service.CategoriasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriasController {

    private final CategoriasService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriasResponse> create(@RequestBody @Valid CategoriasRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriasResponse>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }
}