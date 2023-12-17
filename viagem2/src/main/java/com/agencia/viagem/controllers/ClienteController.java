package com.agencia.viagem.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agencia.viagem.model.Cliente;
import com.agencia.viagem.model.Destino;
import com.agencia.viagem.services.ClienteService;
import com.agencia.viagem.services.DestinoService;



@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private DestinoService destinoService;
	
	@GetMapping
	public String listarClientes(Model model) { 
		List<Cliente> cliente = clienteService.getAllCliente();
		model.addAttribute("cliente", cliente);
		return "ListarClientes";
	}
	
	@GetMapping("/novo")
	public String showForm(Model model) { 
		Cliente cliente = new Cliente();
		List<Destino> destinos = destinoService.getAllDestinos();
		model.addAttribute("cliente", cliente);
		model.addAttribute("destinos", destinos);
		return "clienteForm";
	}
	
	@PostMapping("/save")
	public String saveCliente(@ModelAttribute("cliente") Cliente cliente, @RequestParam Set<Long> destinos) { 
		cliente.setDestinos(destinos.stream()
				.map(destinoService::getDestinoById)
				.collect(Collectors.toSet()));
		clienteService.saveCliente(cliente, destinos);
		return "redirect:/clientes";
	}
	
	@GetMapping("/editar/{id}")
	public String ShowUpdateForm(@PathVariable("id") Long id, Model model) { 
		Cliente cliente = clienteService.getClienteById(id);
		model.addAttribute("cliente", cliente);
		model.addAttribute("destinos", destinoService.getAllDestinos());
		return "editarCliente";
	}
	
	@PostMapping("/editar/{id}")
	public String updateCliente(@PathVariable("id") Long id, @ModelAttribute("cliente") Cliente cliente) { 
		clienteService.updateCliente(id, cliente);
		return "redirect:/clientes";
	}
	
	@GetMapping("/deletar/{id}")
	public String deleteCliente(@PathVariable Long id) { 
		clienteService.deleteCliente(id);;
		return "redirect:/clientes";
	}

}
