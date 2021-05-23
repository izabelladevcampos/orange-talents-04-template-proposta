package com.zupacademy.izabella.propostas.aviso.viagem;

import com.zupacademy.izabella.propostas.cartao.Cartao;
import com.zupacademy.izabella.propostas.cartao.CartaoFeingClient;
import com.zupacademy.izabella.propostas.cartao.CartaoRepository;
import com.zupacademy.izabella.propostas.compartilhado.erros.Erros;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/cartoes/{id}")
public class NovoAvisoViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private CartaoFeingClient client;

    @PostMapping(value = "/viagens")
    public ResponseEntity<?> criaAviso(@PathVariable Long id, @RequestBody @Valid AvisoViagemRequest request,
                                       HttpServletRequest servletRequest) {

        String userAgent = servletRequest.getHeader("User-Agent");
        String ipClient = servletRequest.getRemoteAddr();
        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Erros("cartão", "cartão informado não existe."));
        }

        try {
            client.avisaViagem(cartao.get().getNumero(), request);
            AvisoViagem avisoViagem = request.toModel(cartao.get(), ipClient, userAgent);
            avisoViagemRepository.save(avisoViagem);
            return ResponseEntity.ok().build();
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Erros("aviso de viagem", "Já existe um aviso de viagem para o lugar informado."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }

    }
}
