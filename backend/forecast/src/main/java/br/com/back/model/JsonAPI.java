package br.com.back.model;

import java.util.ArrayList;
import lombok.Data;

@Data
public class JsonAPI {
	private String cod;
	private String message;
	private ArrayList<Object> list = new ArrayList <Object> ();
	private Cyte city;

	@Data
	public class Cyte {
		private Long id;
		private String name;
		private Coord coord;
		private String country;
		private float timezone;
		private float sunrise;
		private float sunset;
	}

	@Data
	public class Coord {
		private float lat;
		private float lon;
	}
}
