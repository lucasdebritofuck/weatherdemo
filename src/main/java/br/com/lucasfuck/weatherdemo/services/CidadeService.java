package br.com.lucasfuck.weatherdemo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.lucasfuck.weatherdemo.entities.Cidade;
import br.com.lucasfuck.weatherdemo.repositories.CidadeRepository;
import net.aksingh.owmjapis.model.CurrentWeather;

@Service
public class CidadeService {

	private static final String CIDADE_INVALIDA_API = "Cidade inválida na API OpenWeatherMap.";

	private CidadeService() {
	}

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private WeatherService weatherService;

	public List<Cidade> findAll() {
		return cidadeRepository.findAll();
	}

	public ResponseEntity<?> findById(Long id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		return ResponseEntity.of(cidade);
	}

	public ResponseEntity<?> update(long id, @Valid Cidade cidadePut) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);

		if (cidade.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		Cidade cidadeSave = cidade.get();

		if (Boolean.FALSE.equals(isCidadeValida(cidadeSave))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST) //
					.body(CIDADE_INVALIDA_API);
		}

		cidadeSave.setNome(cidadePut.getNome());
		cidadeSave.setPais(cidadePut.getPais());
		cidadeSave = cidadeRepository.saveAndFlush(cidadeSave);

		return new ResponseEntity<Cidade>(cidadeSave, HttpStatus.OK);
	}

	public ResponseEntity<?> save(Cidade cidade) {
		if (Boolean.FALSE.equals(isCidadeValida(cidade))) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST) //
					.body(CIDADE_INVALIDA_API);
		}

		cidade = cidadeRepository.saveAndFlush(cidade);

		return new ResponseEntity<Cidade>(cidade, HttpStatus.OK);
	}

	private boolean isCidadeValida(Cidade cidade) {

		if (StringUtils.isBlank(cidade.getNome())) {
			return false;
		}

		CurrentWeather currentWeather = weatherService.getCurrentWeatherByCityName(cidade);

		if (currentWeather == null) {
			return false;
		}

		cidade.setWeatherId(Long.valueOf(currentWeather.getCityId()));
		return true;
	}
}
