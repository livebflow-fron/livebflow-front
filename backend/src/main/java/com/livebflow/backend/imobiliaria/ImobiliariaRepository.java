package com.livebflow.backend.imobiliaria;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImobiliariaRepository extends JpaRepository<Imobiliaria, Integer> {

    boolean existsByCnpj(String cnpj);

    Optional<Imobiliaria> findByCnpj(String cnpj);
}
