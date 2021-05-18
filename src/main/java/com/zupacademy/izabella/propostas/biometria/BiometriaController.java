package com.zupacademy.izabella.propostas.biometria;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zupacademy.izabella.propostas.biometria.validacao.ValidaFormatoBase64;
import com.zupacademy.izabella.propostas.cartao.Cartao;
import com.zupacademy.izabella.propostas.compartilhado.erros.Erros;

@RestController
@RequestMapping("api/cartoes/{id}")
public class BiometriaController {

	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ValidaFormatoBase64());
	}

	@PersistenceContext
	EntityManager manager;

	@Transactional
	@PostMapping(value = "/biometrias")
	public ResponseEntity<?> criaBiometria(@RequestBody @Valid NovaBiometriaRequest request, @PathVariable Long id,
			UriComponentsBuilder uriBuilder) {

		Cartao cartao = manager.find(Cartao.class, id);
		if (cartao == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Erros("cartão", "O cartão informado não existe!"));
		}

		Biometria biometria = request.toModel(cartao);
		manager.persist(biometria);
		URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
