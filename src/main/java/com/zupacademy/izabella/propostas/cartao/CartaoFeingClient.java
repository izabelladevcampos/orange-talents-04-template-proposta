package com.zupacademy.izabella.propostas.cartao;

import com.zupacademy.izabella.propostas.aviso.viagem.AvisoViagemRequest;
import com.zupacademy.izabella.propostas.cartao.bloqueio.NovoBloqueioRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartoes", url = "${cartao.host}")
public interface CartaoFeingClient {

	@GetMapping(value = "/api/cartoes")
	public CartaoResponse emiteCartao(@RequestParam("idProposta") Long idProposta);

	@PostMapping(value = "/api/cartoes/{id}/bloqueios", consumes = "application/json")
	void bloqueia(@PathVariable("id") String id, @RequestBody NovoBloqueioRequest request);

	@PostMapping(value = "/api/cartoes/{id}/avisos", consumes = "application/json")
	void avisaViagem(@PathVariable("id") String id, @RequestBody AvisoViagemRequest request);

}
