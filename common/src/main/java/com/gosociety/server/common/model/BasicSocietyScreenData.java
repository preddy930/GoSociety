package com.gosociety.server.common.model;

import java.util.List;

public class BasicSocietyScreenData {
	
	private String _id;
	private List <Society> societies;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public List<Society> getSocieties() {
		return societies;
	}
	public void setSocieties(List<Society> societies) {
		this.societies = societies;
	}
}
