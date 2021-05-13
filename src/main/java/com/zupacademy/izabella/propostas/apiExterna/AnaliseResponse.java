package com.zupacademy.izabella.propostas.apiExterna;

public class AnaliseResponse {

	private String documento;
	private String nome;
	private EstadoDaProposta estadoDaProposta;
	private Long idProposta;

	public AnaliseResponse(String documento, String nome, EstadoDaProposta estadoDaProposta, Long idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.estadoDaProposta = estadoDaProposta;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public EstadoDaProposta getEstadoDaProposta() {
		return estadoDaProposta;
	}

	public Long getIdProposta() {
		return idProposta;
	}
}
