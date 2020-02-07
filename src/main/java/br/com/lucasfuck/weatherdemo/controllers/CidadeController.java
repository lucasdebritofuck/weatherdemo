package br.com.lucasfuck.weatherdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasfuck.weatherdemo.repositories.CidadeRepository;

@RestController
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;

	private CidadeController() {
	}

}
