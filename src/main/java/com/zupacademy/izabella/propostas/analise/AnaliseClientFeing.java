package com.zupacademy.izabella.propostas.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "analises", url="http://localhost:9999")
public interface AnaliseClientFeing {

	@PostMapping("/api/solicitacao")
	public AnaliseResponse analisaProposta(AnaliseRequest request);

}
