package com.zupacademy.izabella.propostas.cartao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zupacademy.izabella.propostas.proposta.Proposta;
import com.zupacademy.izabella.propostas.proposta.PropostaRepository;

@Component
@EnableScheduling
public class NumeroCartao {

	@Autowired
	private CartaoFeingClient client;

	@Autowired
	private PropostaRepository propostaRepository;

	@Scheduled(fixedDelay = 10000)
	public void associaCartaoProposta() {

		Set<Proposta> propostasElegiveis = propostaRepository.propostasElegiveis();
		for (Proposta proposta : propostasElegiveis) {

			CartaoResponse cartao = client.emiteCartao(proposta.getId());
			Cartao cartaoGerado = cartao.toModel(proposta);
			proposta.setCartao(cartaoGerado);
			propostaRepository.save(proposta);

		}
	}
}