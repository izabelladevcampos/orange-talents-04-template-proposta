package com.zupacademy.izabella.propostas.apiExterna;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(value = "analises", url = "${analise.host}")
public interface AnaliseClientFeing {

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public AnaliseResponse analisaProposta(AnaliseRequest request);

}
