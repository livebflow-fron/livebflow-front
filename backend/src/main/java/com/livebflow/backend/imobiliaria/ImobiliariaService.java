package com.livebflow.backend.imobiliaria;

import com.livebflow.backend.common.BusinessException;
import com.livebflow.backend.common.ResourceNotFoundException;
import com.livebflow.backend.imobiliaria.dto.ImobiliariaRequest;
import com.livebflow.backend.imobiliaria.dto.ImobiliariaResponse;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImobiliariaService {

    private final ImobiliariaRepository repository;

    public ImobiliariaService(ImobiliariaRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ImobiliariaResponse> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ImobiliariaResponse buscarPorId(Integer id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Imobiliaria nao encontrada"));
    }

    @Transactional
    public ImobiliariaResponse criar(ImobiliariaRequest request) {
        validarCnpjDuplicado(request.cnpj(), null);

        Imobiliaria imobiliaria = new Imobiliaria();
        preencherCampos(imobiliaria, request);
        return toResponse(repository.save(imobiliaria));
    }

    @Transactional
    public ImobiliariaResponse atualizar(Integer id, ImobiliariaRequest request) {
        Imobiliaria imobiliaria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Imobiliaria nao encontrada"));

        validarCnpjDuplicado(request.cnpj(), id);
        preencherCampos(imobiliaria, request);
        return toResponse(repository.save(imobiliaria));
    }

    @Transactional
    public void excluir(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Imobiliaria nao encontrada");
        }
        repository.deleteById(id);
    }

    private void validarCnpjDuplicado(String cnpj, Integer idAtual) {
        repository.findByCnpj(cnpj)
                .filter(imobiliaria -> !imobiliaria.getId().equals(idAtual))
                .ifPresent(imobiliaria -> {
                    throw new BusinessException("Ja existe uma imobiliaria cadastrada com este CNPJ");
                });
    }

    private void preencherCampos(Imobiliaria imobiliaria, ImobiliariaRequest request) {
        imobiliaria.setRazaoSocial(request.razaoSocial().trim());
        imobiliaria.setCnpj(request.cnpj().trim());
        imobiliaria.setEmail(request.email() == null ? null : request.email().trim());
        imobiliaria.setTelefone(request.telefone() == null ? null : request.telefone().trim());
    }

    private ImobiliariaResponse toResponse(Imobiliaria imobiliaria) {
        return new ImobiliariaResponse(
                imobiliaria.getId(),
                imobiliaria.getRazaoSocial(),
                imobiliaria.getCnpj(),
                imobiliaria.getEmail(),
                imobiliaria.getTelefone()
        );
    }
}
