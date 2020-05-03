package com.bernardtm.planets.domain.models;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Document
@JsonPropertyOrder({ "id", "name", "climate", "terrain" })
public class Planet implements Serializable {
	
	private static final long serialVersionUID = 5167026764007864122L;

	@Id
	private String id;
	
	@Indexed(unique = true)
    @NotNull(message = "Name should not be null")
	@JsonProperty(required = true)
	private String name;
	
	@NotNull(message = "Climate should not be null")
	@JsonProperty(required = true)
	private String climate;
	
	@NotNull(message = "Terrain should not be null")
	@JsonProperty(required = true)
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