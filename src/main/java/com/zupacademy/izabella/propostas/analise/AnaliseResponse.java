package com.zupacademy.izabella.propostas.analise;

import com.zupacademy.izabella.propostas.proposta.StatusAnalise;

public class AnaliseResponse {

	private String documento;
	private String nome;
	private ResultadoDaSolicitacao resultadoSolicitacao;
	private Long idProposta;


	public AnaliseResponse(String documento, String nome, ResultadoDaSolicitacao resultadoSolicitacao,
			Long idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.resultadoSolicitacao = resultadoSolicitacao;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public ResultadoDaSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public StatusAnalise status() {
		return resultadoSolicitacao.getStatus();
	}

}
