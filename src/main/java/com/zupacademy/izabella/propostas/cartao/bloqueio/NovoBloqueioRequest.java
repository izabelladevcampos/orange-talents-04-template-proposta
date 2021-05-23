package com.zupacademy.izabella.propostas.cartao.bloqueio;

import com.zupacademy.izabella.propostas.cartao.Cartao;

import javax.validation.constraints.NotNull;

public class NovoBloqueioRequest {

    @NotNull
    private String sistemaResponsavel;

    @Deprecated
    public NovoBloqueioRequest() {

    }

    public NovoBloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
