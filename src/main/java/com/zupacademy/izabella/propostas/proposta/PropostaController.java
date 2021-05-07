package com.zupacademy.izabella.propostas.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	PropostaRepository propostaRepository;

	@PostMapping
	public ResponseEntity<NovaPropostaRequest> criaProposta(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder builder) {
		Proposta novaProposta = request.toModel();
		propostaRepository.save(novaProposta);
		URI path = builder.path("/propostas/{id}").build(novaProposta.getId());
		return ResponseEntity.created(path).build();
	}

}
