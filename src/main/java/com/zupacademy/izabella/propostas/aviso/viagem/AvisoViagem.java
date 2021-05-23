package com.zupacademy.izabella.propostas.aviso.viagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zupacademy.izabella.propostas.cartao.Cartao;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTerminoViagem;

    private String ipClient;

    private String userAgent;

    private LocalDateTime momentoAviso = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {

    }

    public AvisoViagem(String destino, LocalDate dataTerminoViagem,
                       String ipClient, String userAgent, Cartao cartao) {
        this.destino = destino;
        this.dataTerminoViagem = dataTerminoViagem;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTerminoViagem() {
        return dataTerminoViagem;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LocalDateTime getMomentoAviso() {
        return momentoAviso;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
