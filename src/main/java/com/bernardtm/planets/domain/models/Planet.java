package com.bernardtm.planets.domain.models;


import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({ "id", "name", "climate", "terrain" })
public class Planet implements Serializable {
	
	private static final long serialVersionUID = 5167026764007864122L;

	@Id
	private String id;
	
	private String name;
	
	private String climate;
	
	private String terrain;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

}