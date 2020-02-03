package br.com.back.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.back.model.City;
import br.com.back.model.CityRepository;
import br.com.back.model.JsonAPI;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping({"/city"})
public class CityController {

	private CityRepository repository;

	CityController(CityRepository repository) {
		this.repository = repository;
	}

	/**
	 * @return Retorna lista com todas as cidades cadastradas
	 */
	@GetMapping
	public List<City> findAll(){
		return repository.findAll();
	}

	/**
	 * @param id - Código da cidade.
	 * @return Retorna os dados da cidade com base nó código:
	 *         - Caso ache o registro vai retorna com o código 200.
	 *         - Se o registro não for encontrado vai retornar código 404.
	 */
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<City> findById(@PathVariable("id") long id){
		return repository.findById(id).map(row -> ResponseEntity.ok().body(row))
				.orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * @param id - Código da cidade.
	 * @return Retorna os dados da cidade com base nó código:
	 *         - Caso ache o registro vai retorna com o código 200.
	 *         - Se o registro não for encontrado vai retornar código 404.
	 */
	@GetMapping(path = {"/forecast/{id}"})
	public ResponseEntity<String> forecast(@PathVariable("id") long id){
		return ResponseEntity.ok(this.findForecastCity(id));
	}

	/**
	 * @param city - Cidade que deve ser inserida no banco
	 * @return retorna a cidade inserida no banco de dados
	 */
	@PostMapping
	public ResponseEntity<City> create(@RequestBody City city){

		if(city.getName() != null && !city.getName().isEmpty()) {
			JsonAPI returnAPI = this.verifyCityAPI(city.getName());

			if(returnAPI != null) {
				city.setId(returnAPI.getCity().getId());
				city.setCountry(returnAPI.getCity().getCountry());
				city.setLatitude(returnAPI.getCity().getCoord().getLat());
				city.setLongitude(returnAPI.getCity().getCoord().getLon());
				city.setName(returnAPI.getCity().getName());

				return repository.findById(city.getId())
						.map(row -> new ResponseEntity<>(city, null, HttpStatus.LOCKED))
						.orElse(ResponseEntity.ok().body(repository.save(city)));
			}
		}

		return 	ResponseEntity.notFound().build();
	}

	/**
	 * @param city - Cidade que deseja atualizar.
	 * @return Cidade atualizada, juntamente com o código:
	 * 		   - Caso de certo a atualziação do registro vai retorna com o código 200.
	 *         - Se o registro não vai retornar código 404.
	 */
	@PutMapping
	public ResponseEntity<City> update(@RequestBody City city) {
		return repository.findById(city.getId()).map(row -> {
			row.setName(city.getName());
			City cityDB = repository.save(row);
			return ResponseEntity.ok().body(cityDB);
		}).orElse(ResponseEntity.notFound().build());
	}

	/**
	 * @param id - Código da cidade que deseja deletar.
	 * @return Código 200 caso de certo a remoção, senão 404
	 */
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity<Object> delete(@Valid @PathVariable long id) {
		return repository.findById(id)
				.map(row -> {
					repository.deleteById(id);
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}

	/**
	 * @param name - Nome da cidade que será procurada na API
	 * @return Informações obtidas pela API
	 */
	private JsonAPI verifyCityAPI(String name) {
		HttpGet httpGet;
		
		try {
			httpGet = new HttpGet("http://api.openweathermap.org/data/2.5/forecast"
					+ "?units=metric"
					+ "&lang=pt_br"
					+ "&APPID=eb8b1a9405e659b2ffc78f0a520b1a46"
					+ "&cnt=1"
					+ "&q=" + URLEncoder.encode(name,"UTF-8"));

		
		HttpClient httpClient = HttpClientBuilder.create().build();

		String json = null;
			json = IOUtils.toString(httpClient.execute(httpGet).getEntity().getContent());

			if(json != null && !json.isEmpty()) {
				JsonAPI jsonObject = new Gson().fromJson(json, JsonAPI.class);

				if(jsonObject.getCod().equalsIgnoreCase("200"))
					return jsonObject;
			}


		} catch (UnsupportedOperationException | IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @param id - Chave da cidade
	 * @return Informações obtidas pela API
	 */
	private String findForecastCity(Long id) {
		HttpGet httpGet;
		
		try {
			httpGet = new HttpGet("http://api.openweathermap.org/data/2.5/forecast"
					+ "?units=metric"
					+ "&lang=pt_br"
					+ "&APPID=eb8b1a9405e659b2ffc78f0a520b1a46"
					+ "&id=" + id);

		
		HttpClient httpClient = HttpClientBuilder.create().build();

		String json = null;
			json = IOUtils.toString(httpClient.execute(httpGet).getEntity().getContent());

			if(json != null && !json.isEmpty()) {
				return json;

			}
		} catch (UnsupportedOperationException | IOException e1) {
			e1.printStackTrace();
		}
		
		return null;
	}
}