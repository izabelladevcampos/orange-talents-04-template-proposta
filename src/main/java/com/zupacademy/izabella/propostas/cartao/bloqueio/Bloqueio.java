package com.zupacademy.izabella.propostas.cartao.bloqueio;

import com.zupacademy.izabella.propostas.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataDeBloqueio = LocalDateTime.now();

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private String userAgente;

    @OneToOne(mappedBy = "bloqueio")
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String ip, String userAgente, Cartao cartao) {
        this.ip = ip;
        this.userAgente = userAgente;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }
}