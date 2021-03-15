package edu.nwmsu.group6.hunt6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {
	@Id
	private int locationId;
	@Column(name="latitude")
	private double latitude;
	@Column(name="longitude")
	private double longitude;
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
		return "Location [locationId=" + locationId + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
