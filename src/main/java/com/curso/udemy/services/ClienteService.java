package com.curso.udemy.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.udemy.domain.Cliente;
import com.curso.udemy.exceptions.ObjectNotFoundException;
import com.curso.udemy.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscarCliente(Integer id) {
		Optional<Cliente> objCliente = clienteRepository.findById(id);
		return objCliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado!:"+ id + "Nome" + Cliente.class.getName()));
	}
}
