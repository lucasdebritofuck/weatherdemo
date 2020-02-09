package br.com.lucasfuck.weatherdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucasfuck.weatherdemo.services.WeatherService;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;

@RestController
public class WeatherRestController {

	private WeatherRestController() {
	}

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = "/weather/{cidade_id}", method = RequestMethod.GET)
	public HourlyWeatherForecast getHourlyWeatherForecast(@PathVariable(value = "cidade_id") Long cidadeId,
			Long weatherId) {
		return weatherService.getHourlyWeatherForecastByCidadeId(cidadeId);
	}

}
