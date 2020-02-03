package br.com.back.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class City {
	
	@Id
	private Long id;
	private String name;
	private float longitude;
	private float latitude;
	private String country;
	
}
