package com.zupacademy.izabella.propostas.proposta;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zupacademy.izabella.propostas.analise.AnaliseClientFeing;
import com.zupacademy.izabella.propostas.analise.AnaliseRequest;
import com.zupacademy.izabella.propostas.analise.AnaliseResponse;
import com.zupacademy.izabella.propostas.compartilhado.erros.Erros;

import feign.FeignException;

@RestController
@RequestMapping("api/propostas")
public class PropostaController {

    @Autowired
    PropostaRepository propostaRepository;

    @Autowired
    private AnaliseClientFeing client;

    @PostMapping
    public ResponseEntity<?> criaProposta(@RequestBody @Valid NovaPropostaRequest request,
                                          UriComponentsBuilder builder) {

        if (propostaRepository.existsByDocumento(request.getDocumento())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new Erros("documento", "Já existe uma proposta para o documento informado!"));
        }

        Proposta novaProposta = request.toModel();
        propostaRepository.save(novaProposta);
        analisaDocumento(novaProposta);
        propostaRepository.save(novaProposta);
        URI path = builder.path("/propostas/{id}").build(novaProposta.getId());
        return ResponseEntity.created(path).build();
    }

    private void analisaDocumento(Proposta proposta) {

        try {
            AnaliseResponse resposta = client.analisaProposta(new AnaliseRequest(proposta));
            StatusAnalise status = resposta.status();
            proposta.setRespostaAnalise(status);

        } catch (FeignException.UnprocessableEntity e) {
            proposta.setRespostaAnalise(StatusAnalise.NAO_ELEGIVEL);
        } catch (FeignException.ServiceUnavailable ex) {
            propostaRepository.delete(proposta);
        }

    }
}
