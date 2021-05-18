package com.zupacademy.izabella.propostas.biometria.validacao;

import java.util.Base64;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.zupacademy.izabella.propostas.biometria.NovaBiometriaRequest;

public class ValidaFormatoBase64 implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaBiometriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (errors.hasErrors())
			return;

		NovaBiometriaRequest request = (NovaBiometriaRequest) target;
		Base64.Decoder decoder = Base64.getDecoder();

		try {
			decoder.decode(request.getBiometriaCodificada());
		} catch (IllegalArgumentException e) {
			errors.reject("biometriaCodificada", "Formato não compatível com base64");
		}
	}
}
