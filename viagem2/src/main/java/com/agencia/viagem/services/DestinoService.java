package com.agencia.viagem.services;

import java.util.List;

import com.agencia.viagem.model.Destino;

public interface DestinoService {

	List<Destino> getAllDestinos();

	Destino saveDestino(Destino destino);

	Destino getDestinoById(Long id);

	Destino updateDestino(Long id, Destino destino);

	void deleteDestino(Long id);
	
	
}
