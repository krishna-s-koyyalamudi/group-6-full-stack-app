package edu.nwmsu.group6.hunt6.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "locations")
public class Location {
	@Id
	@GeneratedValue(generator = "location_generator")
    @SequenceGenerator(
            name = "location_generator",
            sequenceName = "location_sequence",
            initialValue = 1000
    )
	private int locationId;
	@Column(name="name")
	private String location_name;
	@Column(name="latitude")
	private double latitude;
	@Column(name="longitude")
	private double longitude;
	
	public Location(int locationId, String location_name, double latitude, double longitude) {
		super();
		this.locationId = locationId;
		this.location_name = location_name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
		return "Location [locationId=" + locationId + ", location_name=" + location_name + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
}
