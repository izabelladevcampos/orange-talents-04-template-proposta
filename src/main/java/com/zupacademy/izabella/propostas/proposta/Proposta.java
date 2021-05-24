package com.zupacademy.izabella.propostas.proposta;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zupacademy.izabella.propostas.cartao.Cartao;
import com.zupacademy.izabella.propostas.compartilhado.config.criptografia.DadosConverter;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Convert(converter = DadosConverter.class)
	private String documento;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@Valid
	@Embedded
	private Endereco endereco;

	@NotNull
	@Positive
	private BigDecimal salario;

	@Enumerated(EnumType.STRING)
	private StatusAnalise status;

	@OneToOne(cascade = CascadeType.ALL)
	private Cartao cartao;

	/*
	 * @Deprecated apenas para uso do hibernate
	 * 
	 */

	@Deprecated
	public Proposta() {

	}

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@Valid Endereco endereco, @NotNull @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public StatusAnalise getStatusResposta() {
		return status;
	}

	public void setRespostaAnalise(StatusAnalise status) {
		this.status = status;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

}
