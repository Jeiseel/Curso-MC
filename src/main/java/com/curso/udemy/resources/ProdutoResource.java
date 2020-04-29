package com.curso.udemy.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.udemy.domain.Produto;
import com.curso.udemy.dto.ProdutoDTO;
import com.curso.udemy.services.ProdutoService;
import com.curso.udemy.utils.URL;

@RestController
@RequestMapping(value ="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findId(@PathVariable Integer id) {
		Produto objProduto = produtoService.find(id);
		return ResponseEntity.ok().body(objProduto);
	}
	
	@RequestMapping(method = RequestMethod.GET)  //paginação
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value ="nome", defaultValue = "") String nome,
			@RequestParam(value ="categorias", defaultValue = "") String categorias,
			@RequestParam(value ="page", defaultValue = "0") Integer page, 
			@RequestParam(value ="linesPerPage", defaultValue = "24")Integer linesPerpages, 
			@RequestParam(value ="orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value ="direction", defaultValue = "ASC")String direction) {
		String nomeDecoded = URL.DecpdeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = produtoService.findDistinctByNomeContainingAndCategoriasIn(nomeDecoded, ids, page, linesPerpages, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
