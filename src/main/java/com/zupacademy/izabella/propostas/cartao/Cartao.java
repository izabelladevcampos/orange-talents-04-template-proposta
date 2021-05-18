package com.zupacademy.izabella.propostas.cartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.zupacademy.izabella.propostas.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String numero;
	private String titular;
	private LocalDateTime dataEmissao;

	@OneToOne(mappedBy = "cartao")
	private Proposta proposta;

	@Deprecated
	Cartao() {
	}

	public Cartao(String numero, String titular, LocalDateTime dataEmissao, Proposta proposta) {
		this.numero = numero;
		this.titular = titular;
		this.dataEmissao = dataEmissao;
		this.proposta = proposta;
	}

	public String getNumero() {
		return numero;
	}

	public String getTitular() {
		return titular;
	}

	public LocalDateTime getDataEmissao() {
		return dataEmissao;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
