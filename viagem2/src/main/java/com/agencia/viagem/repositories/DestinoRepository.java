package com.agencia.viagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agencia.viagem.model.Destino;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long>{

	

}

