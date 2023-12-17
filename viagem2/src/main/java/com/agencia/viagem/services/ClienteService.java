package com.agencia.viagem.services;

import java.util.List;
import java.util.Set;

import com.agencia.viagem.model.Cliente;

public interface ClienteService {

	List<Cliente> getAllCliente();

	Cliente saveCliente(Cliente cliente, Set<Long> destinos);

	Cliente getClienteById(Long id);

	Cliente updateCliente(Long id, Cliente cliente);

	void deleteCliente(Long id);

}
