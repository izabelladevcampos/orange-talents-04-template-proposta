package com.zupacademy.izabella.propostas.compartilhado.erros;

public class Erros {

	private String campo;
	private String erro;

	public Erros(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
