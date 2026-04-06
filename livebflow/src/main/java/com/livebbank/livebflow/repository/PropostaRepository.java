package com.livebbank.livebflow.repository;

import com.livebbank.livebflow.model.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    // O Spring Boot é tão inteligente que se você escrever isso aqui,
    // ele já cria uma busca automática pelas colunas do Kanban!
    Iterable<Proposta> findByStatusKanban(String statusKanban);

}