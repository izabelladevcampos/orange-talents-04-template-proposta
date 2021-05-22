package com.zupacademy.izabella.propostas.proposta;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

	@NotBlank
	private String rua;

	@NotBlank
	private String cep;

	@NotBlank
	private String numero;

	private String complemento;

	@NotBlank
	private String bairro;

	@NotBlank
	private String cidade;

	@NotBlank
	private String estado;


	public EnderecoRequest(@NotBlank String rua, @NotBlank String cep, @NotBlank String numero,
			@NotBlank String complemento, @NotBlank String bairro, @NotBlank String cidade, @NotBlank String estado) {
		this.rua = rua;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getRua() {
		return rua;
	}

	public String getCep() {
		return cep;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public Endereco paraEnderecoModel() {
		return new Endereco(this.rua, this.cep, this.numero, this.complemento, this.bairro, this.cidade, this.estado);
	}

}
