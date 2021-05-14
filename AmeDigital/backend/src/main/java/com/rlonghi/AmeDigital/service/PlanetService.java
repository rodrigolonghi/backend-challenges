package com.rlonghi.AmeDigital.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rlonghi.AmeDigital.dto.PlanetDTO;
import com.rlonghi.AmeDigital.entities.Planet;
import com.rlonghi.AmeDigital.repositories.PlanetRepository;

@Service
public class PlanetService {

	@Autowired
	private PlanetRepository repository;

	public List<PlanetDTO> findAll() {
		List<Planet> result = repository.findAll();
		return result.stream().map(x -> new PlanetDTO(x)).collect(Collectors.toList());
	}

	public PlanetDTO findById(Long id) {
		Planet planet = repository.findById(id).orElse(null);
		if (planet == null)
			return null;
		else
			return new PlanetDTO(planet);
	}

	public PlanetDTO findByName(String name) {
		List<Planet> planets = repository.findAll();
		for (Planet planet : planets) {
			if (planet.getName().toUpperCase().equals(name.toUpperCase()))
				return new PlanetDTO(planet);
		}
		return null;
	}

	public void deletePlanetById(Long id) {
		repository.deleteById(id);
	}

	public void newPlanet(Planet planet) {
		repository.save(planet);
	}
}
