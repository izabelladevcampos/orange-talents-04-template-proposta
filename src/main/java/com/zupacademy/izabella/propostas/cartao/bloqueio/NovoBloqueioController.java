package com.zupacademy.izabella.propostas.cartao.bloqueio;

import com.zupacademy.izabella.propostas.cartao.Cartao;
import com.zupacademy.izabella.propostas.cartao.CartaoFeingClient;
import com.zupacademy.izabella.propostas.cartao.CartaoRepository;
import com.zupacademy.izabella.propostas.compartilhado.erros.Erros;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("api/cartoes/{id}")
public class NovoBloqueioController {

    @Autowired
    private CartaoRepository cartaorepository;

    @Autowired
    private CartaoFeingClient client;

    @PostMapping("/bloqueio")
    public ResponseEntity<?> salvar(@PathVariable Long id,
                                    HttpServletRequest servletRequest,
                                    @AuthenticationPrincipal Jwt usuario) {
        String email = (String) usuario.getClaims().get("email");

        Optional<Cartao> cartao = cartaorepository.findById(id);

        if (cartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Erros("cartão", "o cartão informado não existe."));
        }

        if (!cartao.get().emailPertenceAoUsuario(email)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Erros("cartão", " o cartão informado não pertence ao usuario logado."));
        }

        String userAgent = servletRequest.getHeader("User-Agent");
        String ip = servletRequest.getRemoteAddr();

        try {
            Cartao cartaoParaBloqueio = cartao.get();
            client.bloqueia(cartao.get().getNumero(), new NovoBloqueioRequest("proposta"));
            Bloqueio bloqueio = new Bloqueio(ip, userAgent, cartao.get());
            cartaoParaBloqueio.adicionaBloqueio(bloqueio);
            cartaorepository.save(cartaoParaBloqueio);
            return ResponseEntity.ok().build();

        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Erros("cartão", "o cartão já está bloqueado"));
        }
    }
}
