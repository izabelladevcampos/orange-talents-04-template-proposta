package com.zupacademy.izabella.propostas.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url = "http://localhost:8888")
public interface CartaoFeingClient {

	@GetMapping(value = "/api/cartoes")
	public CartaoResponse emiteCartao(@RequestParam("idProposta") Long idProposta);

}
