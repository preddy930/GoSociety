package com.gosociety.server.common.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="places")
public class Place {

	@Id
	String pid;
	String name;
	String phone;
	Address address;
	String timezone;
	String [] societies;
	float [] latlong;
	
	public String getPid() {
		return pid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public static class Address {
		
		String street;
		String city;
		String state;
		
		public String getStreet() {
			return street;
		}
		
		public void setStreet(String street) {
			this.street = street;
		}
		
		public String getCity() {
			return city;
		}
		
		public void setCity(String city) {
			this.city = city;
		}
		
		public String getState() {
			return state;
		}
		
		public void setState(String state) {
			this.state = state;
		}
	}

	public float[] getLatlong() {
		return latlong;
	}

	public void setLatlong(float[] latlong) {
		this.latlong = latlong;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getSocieties() {
		return societies;
	}

	public void setSocieties(String[] societies) {
		this.societies = societies;
	}
}
