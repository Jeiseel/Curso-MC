package com.curso.udemy.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.udemy.domain.Cliente;
import com.curso.udemy.services.ClienteService;

@RestController
@RequestMapping(value ="/clientes")
public class ClienteResouce {

	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?>findId(@PathVariable Integer id){
		Cliente objCliente = clienteService.buscarCliente(id);
		return ResponseEntity.ok().body(objCliente);
	}
}
