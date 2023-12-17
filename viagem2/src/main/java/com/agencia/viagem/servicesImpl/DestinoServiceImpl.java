package com.agencia.viagem.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agencia.viagem.model.Destino;
import com.agencia.viagem.repositories.DestinoRepository;
import com.agencia.viagem.services.DestinoService;



@Service
public class DestinoServiceImpl implements DestinoService{

	@Autowired
	private DestinoRepository destinoRepository;

	@Override
	public List<Destino> getAllDestinos() {
		return destinoRepository.findAll();
		}

	@Override
	@Transactional(readOnly = true)
	public Destino getDestinoById(Long id) {
		return destinoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Destino saveDestino(Destino destino) {
		return destinoRepository.save(destino);
	}

	@Override
	public Destino updateDestino(Long id, Destino destinoAtualizado) {
		Destino destinoExistente = destinoRepository.findById(id).orElse(null);
		if (destinoExistente != null) { 
			destinoExistente.setNome(destinoAtualizado.getNome());
			destinoExistente.setImgUrl(destinoAtualizado.getImgUrl());
			destinoExistente.setEstado(destinoAtualizado.getEstado());
			destinoExistente.setPais(destinoAtualizado.getPais());
			destinoExistente.setDataInicio(destinoAtualizado.getDataInicio());
			destinoExistente.setDataFim(destinoAtualizado.getDataFim());
			destinoExistente.setValor(destinoAtualizado.getValor());
			
			return destinoRepository.save(destinoExistente);
		} else { 
			throw new RuntimeException("Destino com o ID" + id + "não encontrado.");
		}
	}

	@Override
	public void deleteDestino(Long id) {
		destinoRepository.deleteById(id);
	}

	
	
}
