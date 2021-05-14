package com.rlonghi.AmeDigital.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rlonghi.AmeDigital.dto.PlanetDTO;
import com.rlonghi.AmeDigital.entities.Planet;
import com.rlonghi.AmeDigital.service.PlanetService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/planets")
public class PlanetController {

	@Autowired
	private PlanetService service;

	@GetMapping
	public ResponseEntity<List<PlanetDTO>> findAll() {
		List<PlanetDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/byname/{name}")
	public PlanetDTO findByName(@PathVariable String name) {
		PlanetDTO planet = service.findByName(name);
		return (planet);
	}

	@GetMapping(value = "/byid/{id}")
	public PlanetDTO findById(@PathVariable Long id) {
		PlanetDTO planet = service.findById(id);
		return (planet);
	}

	@PostMapping
	public ResponseEntity<Void> newPlanet(@RequestBody Planet planet) {
		service.newPlanet(planet);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(planet.getName())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(value = "/byid/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws NotFoundException {
        PlanetDTO planet = service.findById(id);
        if (planet == null)
            throw new NotFoundException("Planet not found.");
        service.deletePlanetById(id);
    }
	
	@DeleteMapping(value = "/byname/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(@PathVariable String name) throws NotFoundException {
        PlanetDTO planet = service.findByName(name);
        if (planet == null)
            throw new NotFoundException("Planet not found.");
        service.deletePlanetById(planet.getId());
    }
	
}
