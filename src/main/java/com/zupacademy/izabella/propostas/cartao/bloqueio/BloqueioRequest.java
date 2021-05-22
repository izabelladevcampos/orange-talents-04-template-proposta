package com.zupacademy.izabella.propostas.cartao.bloqueio;

import com.zupacademy.izabella.propostas.cartao.Cartao;

import javax.validation.constraints.NotNull;

public class BloqueioRequest {

    @NotNull
    private String sistemaResponsavel;

    @Deprecated
    public BloqueioRequest() {

    }

    public BloqueioRequest(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
