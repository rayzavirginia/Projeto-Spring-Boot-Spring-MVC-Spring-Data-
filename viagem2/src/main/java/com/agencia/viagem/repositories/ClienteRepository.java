package com.agencia.viagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agencia.viagem.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
