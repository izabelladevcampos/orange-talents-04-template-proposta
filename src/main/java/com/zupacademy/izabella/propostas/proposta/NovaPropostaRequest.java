package com.zupacademy.izabella.propostas.proposta;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zupacademy.izabella.propostas.compartilhado.validacao.CPFouCNPJ;

public class NovaPropostaRequest {

	@CPFouCNPJ
	@NotBlank
	private String documento;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@Valid
	private EnderecoRequest endereco;

	@NotNull
	@Positive
	private BigDecimal salario;

	public NovaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@Valid EnderecoRequest endereco, @NotNull @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Proposta toModel() {
		Endereco novoEndereco = endereco.paraEnderecoModel();

		return new Proposta(this.documento, this.email, this.nome, novoEndereco, this.salario);
	}

	public String getDocumento() {
		return documento;
	}

}
