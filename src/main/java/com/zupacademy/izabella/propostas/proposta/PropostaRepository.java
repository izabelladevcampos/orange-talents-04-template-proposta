package com.zupacademy.izabella.propostas.proposta;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {

	boolean existsByDocumento(String documento);
	
	@Query("SELECT p FROM Proposta p WHERE p.status = 'ELEGIVEL' "
			+ "AND p.cartao.id = NULL")
	Set<Proposta> propostasElegiveis();
	
}
