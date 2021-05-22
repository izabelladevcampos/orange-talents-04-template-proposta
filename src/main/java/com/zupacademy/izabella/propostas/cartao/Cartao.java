package com.zupacademy.izabella.propostas.cartao;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.zupacademy.izabella.propostas.cartao.bloqueio.Bloqueio;
import com.zupacademy.izabella.propostas.proposta.Proposta;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String titular;
    private LocalDateTime dataEmissao;

    @Enumerated(EnumType.STRING)
    private StatusCartao status = StatusCartao.ATIVO;

    @OneToOne(mappedBy = "cartao")
    private Proposta proposta;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bloqueio_id", referencedColumnName = "id")
    private Bloqueio bloqueio;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String numero, String titular, LocalDateTime dataEmissao, Proposta proposta) {
        this.numero = numero;
        this.titular = titular;
        this.dataEmissao = dataEmissao;
        this.proposta = proposta;
    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public StatusCartao getStatus() {
        return status;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean emailPertenceAoUsuario(Object email) {
        return email.equals(proposta.getEmail());
    }

    public void adicionaBloqueio(Bloqueio bloqueio) {
        this.bloqueio = bloqueio;
        this.status = StatusCartao.BLOQUEADO;

    }
}