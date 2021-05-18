package com.zupacademy.izabella.propostas.biometria;

import java.util.Base64;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.zupacademy.izabella.propostas.cartao.Cartao;

public class NovaBiometriaRequest {

	@NotBlank
	private String biometriaCodificada;

	@JsonCreator(mode = Mode.PROPERTIES)
	public NovaBiometriaRequest(String biometriaCodificada) {
		this.biometriaCodificada = biometriaCodificada;
	}

	public String getBiometriaCodificada() {
		return biometriaCodificada;
	}

	public Biometria toModel(Cartao cartao) {
		byte[] biometriaDecode = Base64.getDecoder().decode(this.biometriaCodificada.getBytes());
		String biometria = new String(biometriaDecode);

		return new Biometria(biometria, cartao);

	}
}
