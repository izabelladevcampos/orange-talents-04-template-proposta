package com.zupacademy.izabella.propostas.aviso.viagem;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.izabella.propostas.cartao.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class AvisoViagemRequest {

    @NotBlank
    @Size(max = 50)
    private String destino;

    @NotNull
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate validoAte;

    @Deprecated
    public AvisoViagemRequest() {
    }

    public AvisoViagemRequest(String destino, @JsonProperty("validoAte") LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public AvisoViagem toModel(Cartao cartao, String ipClient, String userAgent) {
        return new AvisoViagem(this.destino, this.validoAte, ipClient, userAgent, cartao);
    }
}
