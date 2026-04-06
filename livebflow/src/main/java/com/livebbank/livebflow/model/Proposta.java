package com.livebbank.livebflow.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campos atualizados para bater perfeitamente com o Front-end do Figma
    @Column(name = "nome_imobiliaria", length = 150)
    private String nomeImobiliaria;

    @Column(name = "nome_inquilino", length = 150)
    private String nomeInquilino;

    @Column(name = "valor_aluguel", precision = 10, scale = 2)
    private BigDecimal valorAluguel;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "status_kanban", length = 50)
    private String statusKanban = "A Fazer";

    public Proposta() {
    }

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeImobiliaria() { return nomeImobiliaria; }
    public void setNomeImobiliaria(String nomeImobiliaria) { this.nomeImobiliaria = nomeImobiliaria; }

    public String getNomeInquilino() { return nomeInquilino; }
    public void setNomeInquilino(String nomeInquilino) { this.nomeInquilino = nomeInquilino; }

    public BigDecimal getValorAluguel() { return valorAluguel; }
    public void setValorAluguel(BigDecimal valorAluguel) { this.valorAluguel = valorAluguel; }

    public LocalDate getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }

    public String getStatusKanban() { return statusKanban; }
    public void setStatusKanban(String statusKanban) { this.statusKanban = statusKanban; }
}