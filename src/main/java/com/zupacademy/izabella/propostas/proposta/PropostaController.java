package com.zupacademy.izabella.propostas.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zupacademy.izabella.propostas.apiExterna.AnaliseClientFeing;
import com.zupacademy.izabella.propostas.apiExterna.AnaliseRequest;

import feign.FeignException;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	PropostaRepository propostaRepository;

	@Autowired
	private AnaliseClientFeing client;

	@PostMapping
	public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder builder) {

		boolean documentoCadastrado = propostaRepository.existsByDocumento(request.getDocumento());
		if (documentoCadastrado) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body("Já existe uma proposta para o documento informado");
		}

		Proposta novaProposta = request.toModel();
		System.out.println(novaProposta);
		propostaRepository.save(novaProposta);
		analisaDocumento(novaProposta);
		propostaRepository.save(novaProposta);
		URI path = builder.path("/propostas/{id}").build(novaProposta.getId());
		return ResponseEntity.created(path).build();
	}

	private void analisaDocumento(Proposta proposta) {
		RespostaAnalise status;
		try {
			client.analisaProposta(new AnaliseRequest(proposta));
			status = RespostaAnalise.ELEGIVEL;
		} catch (FeignException.UnprocessableEntity e) {
			status = RespostaAnalise.NAO_ELEGIVEL;
		}
		proposta.setStatusAnalise(status);
	}
}
