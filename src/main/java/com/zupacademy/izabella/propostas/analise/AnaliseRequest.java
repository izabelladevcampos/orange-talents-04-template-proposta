package com.zupacademy.izabella.propostas.analise;

import com.zupacademy.izabella.propostas.proposta.Proposta;

public class AnaliseRequest {

	private String documento;
	private String nome;
	private Long idProposta;

	public AnaliseRequest(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getIdProposta() {
		return idProposta;
	}

}
