package edu.nwmsu.group6.hunt6.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "locations")
public class Location {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "location_generator")
	@SequenceGenerator(name = "location_generator", sequenceName = "location_sequence", initialValue = 1000)
	private Long id;

	@NotNull
	@Column(name = "location_name")
	private String location_name;

	@NotNull
	@Column(name = "latitude")
	private double latitude;

	@NotNull
	@Column(name = "longitude")
	private double longitude;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", location_name=" + location_name + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
}
