package casa.controle.demo.components.controller;

import casa.controle.demo.components.dto.request.UsuariosRequest;
import casa.controle.demo.components.dto.response.UsuariosResponse;
import casa.controle.demo.components.service.UsuariosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuariosService usuariosService;

    @PostMapping
    public ResponseEntity<UsuariosResponse> create(@RequestBody @Valid UsuariosRequest dto) {
        UsuariosResponse createdUsuario = usuariosService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuariosResponse>> findAll() {
        List<UsuariosResponse> usuarios = usuariosService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuariosResponse> findById(@PathVariable Integer id) {
        UsuariosResponse usuario = usuariosService.findById(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuariosResponse> update(@PathVariable Integer id, @RequestBody @Valid UsuariosRequest dto) {
        UsuariosResponse updatedUsuario = usuariosService.update(id, dto);
        return ResponseEntity.ok(updatedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuariosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

