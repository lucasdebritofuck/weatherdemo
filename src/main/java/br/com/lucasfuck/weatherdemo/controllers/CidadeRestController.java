package br.com.lucasfuck.weatherdemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasfuck.weatherdemo.entities.Cidade;
import br.com.lucasfuck.weatherdemo.services.CidadeService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CidadeRestController {

	private CidadeRestController() {
	}

	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(value = "/cidade", method = RequestMethod.GET)
	public List<Cidade> get() {
		return cidadeService.findAll();
	}

	@RequestMapping(value = "/cidade/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(name = "id") Long id) {
		return cidadeService.findById(id);
	}

	@RequestMapping(value = "/cidade", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody(required = true) Cidade cidade) {
		return cidadeService.save(cidade);
	}

	@RequestMapping(value = "/cidade/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody(required = true) Cidade cidade) {
		return cidadeService.update(id, cidade);
	}

}
