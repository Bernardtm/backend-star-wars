package com.bernardtm.planets.domain.models.dtos;

public class PlanetDto {
	
	private Long id;
	
	private String type;
	
	private String action;
	
	private String number;
	
	private Boolean active;
	
	private String[] events;
	
	private Long app_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String[] getEvents() {
		return events;
	}

	public void setEvents(String[] events) {
		this.events = events;
	}

	public Long getApp_id() {
		return app_id;
	}

	public void setApp_id(Long app_id) {
		this.app_id = app_id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
