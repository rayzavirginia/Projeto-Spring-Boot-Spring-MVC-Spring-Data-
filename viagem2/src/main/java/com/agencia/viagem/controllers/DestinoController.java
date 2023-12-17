package com.agencia.viagem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agencia.viagem.model.Destino;
import com.agencia.viagem.services.DestinoService;



@Controller
@RequestMapping("/destinos")
public class DestinoController {

	@Autowired
	private DestinoService destinoService;

	// Listar
	@GetMapping
	public String listDestinos(Model model) {
		List<Destino> destinos = destinoService.getAllDestinos();
		model.addAttribute("destinos", destinos);
		return "ListarDestinos";
	}

	// Formulário de criação
	@GetMapping("/novo")
	public String showFormForAdd(Model model) {
		Destino destino = new Destino();
		model.addAttribute("destino", destino);
		return "destinoForm";
	}

	// Persistencia da criação
	@PostMapping("/save")
	public String saveDestino(@ModelAttribute("destino") Destino destino) {
		destinoService.saveDestino(destino);
		return "redirect:/destinos";
	}

	// Formulário de edição
	@GetMapping("/editar/{id}")
	public String showFormForUpdate(@PathVariable Long id, Model model) {
		Destino destino = destinoService.getDestinoById(id);
		model.addAttribute("destino", destino);
		return "editarDestino";
	}

	// Persistencia da edição
	@PostMapping("/editar/{id}")
	public String updateDestino(@PathVariable Long id, @ModelAttribute("destino") 
	Destino destino) {
		destinoService.updateDestino(id, destino);
		return "redirect:/destinos";
	}
	
	// Excluir destino
	@GetMapping("/deletar/{id}")
	public String deleteDestino(@PathVariable Long id) { 
		destinoService.deleteDestino(id);
		return "redirect:/destinos";
	}

	
}
