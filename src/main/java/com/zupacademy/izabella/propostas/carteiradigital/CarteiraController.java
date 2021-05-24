package com.zupacademy.izabella.propostas.carteiradigital;

import com.zupacademy.izabella.propostas.cartao.Cartao;
import com.zupacademy.izabella.propostas.cartao.CartaoFeingClient;
import com.zupacademy.izabella.propostas.cartao.CartaoRepository;
import com.zupacademy.izabella.propostas.compartilhado.erros.Erros;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/cartoes/{id}")
public class CarteiraController {

    @Autowired
    CarteiraRepository carteiraRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @Autowired
    CartaoFeingClient client;

    @PostMapping(value = "/carteiras")
    public ResponseEntity<?> criaCarteira(@PathVariable Long id,
                                          @RequestBody @Valid NovaCarteiraRequest request,
                                          UriComponentsBuilder uriBuilder) {

        Optional<Cartao> possivelCartao = cartaoRepository.findById(id);
        if (possivelCartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Erros("cartão", "cartão informado não existe"));
        }
        Cartao cartao = possivelCartao.get();

        if (possuiCarteira(cartao, request.getCarteira())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Erros("carteira", "essa carteira já está associada ao cartão."));

        }
        try {
            Carteira carteira = request.toModel(cartao);
            client.adicionaCarteira(cartao.getNumero(), request);
            carteiraRepository.save(carteira);
            URI uri = uriBuilder.path("/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (FeignException e) {

            return ResponseEntity.badRequest().build();
        }

    }

    boolean possuiCarteira(Cartao cartao, TipoCarteira carteira) {
        return carteiraRepository.existsByCarteiraAndCartao(carteira, cartao);
    }
}