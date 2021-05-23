package com.zupacademy.izabella.propostas.carteiradigital;

import com.zupacademy.izabella.propostas.cartao.Cartao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovaCarteiraRequest {

    @Email
    @NotBlank
    private String email;

    private TipoCarteira carteira;

    public NovaCarteiraRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(this.email, this.carteira, cartao);
    }
}
