package br.com.lucasfuck.weatherdemo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucasfuck.weatherdemo.entities.Cidade;
import br.com.lucasfuck.weatherdemo.repositories.CidadeRepository;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.core.OWM.Unit;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;

@Service
public class WeatherService {

	private static final String API_KEY = "eb8b1a9405e659b2ffc78f0a520b1a46";

	private WeatherService() {
	}

	@Autowired
	private CidadeRepository cidadeRepository;

	public CurrentWeather getCurrentWeatherByCityName(Cidade cidade) {

		OWM openWeatherMap = new OWM(API_KEY);
		openWeatherMap.setUnit(Unit.METRIC);

		try {
			CurrentWeather currentWeather = openWeatherMap.currentWeatherByCityName(cidade.getNome());
			return currentWeather;
		} catch (APIException e) {
			e.printStackTrace();
		}

		return null;
	}

	public HourlyWeatherForecast getHourlyWeatherForecastByCidadeId(Long cidadeId) {

		Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

		if (cidade.isEmpty()) {
			return null;
		}

		OWM openWeatherMap = new OWM(API_KEY);
		openWeatherMap.setUnit(Unit.METRIC);

		try {
			HourlyWeatherForecast hourlyWeatherForecast = openWeatherMap
					.hourlyWeatherForecastByCityId(cidade.get().getWeatherId().intValue());
			return hourlyWeatherForecast;
		} catch (APIException e) {
			e.printStackTrace();
		}

		return null;
	}

}
