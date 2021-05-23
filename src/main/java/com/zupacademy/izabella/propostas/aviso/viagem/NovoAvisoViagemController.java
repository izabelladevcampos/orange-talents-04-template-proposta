package com.zupacademy.izabella.propostas.aviso.viagem;

import com.zupacademy.izabella.propostas.cartao.Cartao;
import com.zupacademy.izabella.propostas.cartao.CartaoRepository;
import com.zupacademy.izabella.propostas.compartilhado.erros.Erros;
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

    @PostMapping(value = "/aviso-viagem")
    public ResponseEntity<?> criaAviso(@PathVariable Long id, @RequestBody @Valid AvisoViagemRequest request,
                                       HttpServletRequest servletRequest) {

        String userAgent = servletRequest.getHeader("User-Agent");
        String ipClient = servletRequest.getRemoteAddr();
        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if (cartao.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Erros("cartão", "cartão informado não existe."));
        }
        AvisoViagem avisoViagem = request.toModel(cartao.get(), ipClient, userAgent);
        avisoViagemRepository.save(avisoViagem);
        return ResponseEntity.ok().build();

    }
}
