package com.zupacademy.izabella.propostas.aviso.viagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.izabella.propostas.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoViagemRequest {

    @NotBlank
    @Size(max = 50)
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTerminoViagem;

    public AvisoViagemRequest(String destino, @JsonProperty("dataTerminoViagem") LocalDate dataTerminoViagem) {
        this.destino = destino;
        this.dataTerminoViagem = dataTerminoViagem;
    }

    public AvisoViagem toModel(Cartao cartao, String ipClient, String userAgent) {
        return new AvisoViagem(this.destino, this.dataTerminoViagem, ipClient, userAgent, cartao);
    }
}
