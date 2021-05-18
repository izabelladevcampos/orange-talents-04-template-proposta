package com.zupacademy.izabella.propostas.analise;

import com.zupacademy.izabella.propostas.proposta.StatusAnalise;

public enum ResultadoDaSolicitacao {

	COM_RESTRICAO(StatusAnalise.NAO_ELEGIVEL),
	SEM_RESTRICAO(StatusAnalise.ELEGIVEL);

	private final StatusAnalise status;

	ResultadoDaSolicitacao(StatusAnalise status) {
		this.status = status;
	}

	public StatusAnalise getStatus() {
		return status;
	}
	
}
