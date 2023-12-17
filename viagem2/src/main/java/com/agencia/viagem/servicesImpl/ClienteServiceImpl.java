package com.agencia.viagem.servicesImpl;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.agencia.viagem.model.Cliente;
import com.agencia.viagem.model.Destino;
import com.agencia.viagem.repositories.ClienteRepository;
import com.agencia.viagem.services.ClienteService;
import com.agencia.viagem.services.DestinoService;


@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private DestinoService destinoService;
	
	@Override
	public List<Cliente> getAllCliente() {
		return clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente getClienteById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	public Cliente saveCliente(Cliente cliente, Set<Long> destinoIds) {
		Set<Destino> destinos = destinoIds.stream()
				.map(destinoService::getDestinoById)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());
		
		cliente.setDestinos(destinos);
		
		return clienteRepository.save(cliente);
	}

	@Override
	public Cliente updateCliente(Long id, Cliente clienteAtualizado) {
		Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
		if (clienteExistente != null) { 
			clienteExistente.setNome(clienteAtualizado.getNome());
			clienteExistente.setCpf(clienteAtualizado.getCpf());
			clienteExistente.setEndereco(clienteAtualizado.getEndereco());
			clienteExistente.setEstado(clienteAtualizado.getEstado());
			clienteExistente.setTelefone(clienteAtualizado.getTelefone());
			clienteExistente.setEmail(clienteAtualizado.getEmail());
			
			Set<Destino> destinosAtualizados = clienteAtualizado.getDestinos();
			for ( Destino destino : destinosAtualizados) { 
				destino.getClientes().add(clienteExistente);
			}
			
			clienteExistente.setDestinos(destinosAtualizados);
			
			return clienteRepository.save(clienteExistente);
		} else { 
			throw new RuntimeException("Cliente com o ID" + id + "não encontrado.");
		}
	}

	@Override
	public void deleteCliente(Long id) {
		clienteRepository.deleteById(id);
	}



}
