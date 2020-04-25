package com.curso.udemy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.udemy.domain.Cliente;
import com.curso.udemy.dto.ClienteDTO;
import com.curso.udemy.exceptions.DataIntegrityException;
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
	

	public Cliente update(Cliente obj) {
		Cliente newObj = buscarCliente(obj.getId());
		updateDate(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		buscarCliente(id);
		try {
			clienteRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que contém pedidos [entidades relacionadas] !"); 
			
		}
	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	//Metodo auxiliar que estancia um DTO
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	private void updateDate(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}
