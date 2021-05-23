package com.zupacademy.izabella.propostas.carteiradigital;

import com.zupacademy.izabella.propostas.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository <Carteira, Long> {

    boolean existsByCarteiraAndCartao(TipoCarteira tipoCarteira, Cartao cartao);
}
