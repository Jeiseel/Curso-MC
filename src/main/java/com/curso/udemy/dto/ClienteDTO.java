package com.curso.udemy.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.curso.udemy.domain.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;  
	
	private Integer id;
	
	@NotEmpty(message = "Campo com preenchimento obrigatorio!")
	@Length(min = 5, max = 120, message = "O tamanho deve ter entre 5 e 120 caracteres!")
	private String nome;
	
	@NotEmpty(message = "Campo com preenchimento obrigatorio!")
	@Email(message = "email invalido")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
