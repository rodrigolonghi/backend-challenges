package com.rlonghi.AmeDigital.dto;

import java.io.Serializable;

import com.rlonghi.AmeDigital.entities.Planet;

public class PlanetDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String weather;
	private String land;
	
	public PlanetDTO() {
	}

	public PlanetDTO(Long id, String name, String weather, String land) {
		this.id = id;
		this.name = name;
		this.weather = weather;
		this.land = land;
	}
	
	public PlanetDTO(Planet entity) {
		id = entity.getId();
		name = entity.getName();
		weather = entity.getWeather();
		land = entity.getLand();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}
}
