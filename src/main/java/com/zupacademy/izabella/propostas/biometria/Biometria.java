package com.zupacademy.izabella.propostas.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.zupacademy.izabella.propostas.cartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String biometria;

	private LocalDateTime momentoDeCriacao = LocalDateTime.now();

	@ManyToOne
	private Cartao cartao;

	@Deprecated
	Biometria() {

	}

	public Biometria(String biometria, Cartao cartao) {
		this.biometria = biometria;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getBiometria() {
		return biometria;
	}

	public LocalDateTime getMomentoDeCriacao() {
		return momentoDeCriacao;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
