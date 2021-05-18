package com.zupacademy.izabella.propostas.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analise", url="${analise.host}")
public interface AnaliseClientFeing {

	@PostMapping("/api/solicitacao")
	public AnaliseResponse analisaProposta(AnaliseRequest request);

}
