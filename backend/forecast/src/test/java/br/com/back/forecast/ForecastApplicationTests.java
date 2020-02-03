package br.com.back.forecast;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.back.controller.CityController;
import br.com.back.main.ForecastApplication;
import br.com.back.model.City;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ForecastApplication.class)
class ForecastApplicationTests {

	@Autowired
	private CityController controller;

	@Test
	void testAddCity() {
		City city = new City();
		city.setName("Blumenau");
		this.controller.create(city);

		Assertions.assertThat(city.getId()).isNotNull();
	}

	@Test
	void testFindAllCitys() {
		List<City> lista = this.controller.findAll();

		Assertions.assertThat(lista.size()).isEqualTo(1);
	}

	@Test
	void testFindById() {
		long id = 3469968;
		ResponseEntity<City> city = this.controller.findById(id);

		Assertions.assertThat(city.getBody().getId()).isEqualTo(id);
	}
	
	@Test
	void testDelete() {
		long id = 3469968;
		this.controller.delete(id);
		ResponseEntity<City> city = this.controller.findById(id);

		Assertions.assertThat(city.getBody()).isNull();;
	}	
}
