package com.livebflow.backend.imobiliaria;

import com.livebflow.backend.imobiliaria.dto.ImobiliariaRequest;
import com.livebflow.backend.imobiliaria.dto.ImobiliariaResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imobiliarias")
public class ImobiliariaController {

    private final ImobiliariaService service;

    public ImobiliariaController(ImobiliariaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ImobiliariaResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImobiliariaResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ImobiliariaResponse> criar(@Valid @RequestBody ImobiliariaRequest request) {
        ImobiliariaResponse response = service.criar(request);
        return ResponseEntity
                .created(URI.create("/api/imobiliarias/" + response.id()))
                .body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImobiliariaResponse> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ImobiliariaRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
