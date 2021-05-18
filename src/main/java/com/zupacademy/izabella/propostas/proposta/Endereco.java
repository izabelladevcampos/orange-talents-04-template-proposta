package com.zupacademy.izabella.propostas.proposta;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {

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

	/*
	 * @Deprecated apenas para uso do hibernate
	 * 
	 */
	@Deprecated
	Endereco() {

	}

	public Endereco(@NotBlank String rua, @NotBlank String cep, @NotBlank String numero, String complemento,
			@NotBlank String bairro, @NotBlank String cidade, @NotBlank String estado) {
		this.rua = rua;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

}
