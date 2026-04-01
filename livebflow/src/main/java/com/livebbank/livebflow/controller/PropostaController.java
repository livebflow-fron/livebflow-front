package com.livebbank.livebflow.controller;

import com.livebbank.livebflow.model.Proposta;
import com.livebbank.livebflow.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/propostas")
@CrossOrigin(origins = "*") // Isso aqui é ouro! Permite que o Front-end se conecte sem dar erro de segurança (CORS)
public class PropostaController {

    @Autowired
    private PropostaRepository repository;

    // 1. LISTAR TODAS (Para carregar o Kanban inteiro)
    @GetMapping
    public List<Proposta> listarTodas() {
        return repository.findAll();
    }

    // 2. CRIAR UMA NOVA PROPOSTA
    @PostMapping
    public Proposta criar(@RequestBody Proposta proposta) {
        return repository.save(proposta);
    }

    // 3. MOVER O CARTÃO NO KANBAN (Atualizar só o status)
    @PatchMapping("/{id}/status")
    public ResponseEntity<Proposta> moverCartao(@PathVariable Long id, @RequestBody Map<String, String> statusAtualizado) {
        return repository.findById(id)
                .map(proposta -> {
                    // Pega o novo status que veio do Front-end (ex: "Em Análise")
                    proposta.setStatusKanban(statusAtualizado.get("statusKanban"));
                    Proposta atualizada = repository.save(proposta);
                    return ResponseEntity.ok(atualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    // 4. EXCLUIR PROPOSTA (Caso seja cancelada/negada)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}