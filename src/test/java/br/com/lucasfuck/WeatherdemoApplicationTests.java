package br.com.lucasfuck;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.lucasfuck.weatherdemo.WeatherdemoApplication;
import br.com.lucasfuck.weatherdemo.entities.Cidade;
import br.com.lucasfuck.weatherdemo.services.CidadeService;

@SpringBootTest(classes = WeatherdemoApplication.class)
public class WeatherdemoApplicationTests {

	@Autowired
	private CidadeService cidadeService;

	@Test
	void incluiCidadeInvalida() {

		Cidade cidade = new Cidade();
		cidade.setNome("Cidade não existente");
		ResponseEntity<?> responseEntity = cidadeService.save(cidade);

		if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
			fail();
		}
	}

}
