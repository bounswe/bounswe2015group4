package com.model;

import java.util.Arrays;

public class Shop {
	@Override
	public String toString() {
		return "Shop{" +
				"name='" + name + '\'' +
				", staffName=" + Arrays.toString(staffName) +
				'}';
	}

	String name;
	
	String staffName[];
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getStaffName() {
		return staffName;
	}
	public void setStaffName(String[] staffName) {
		this.staffName = staffName;
	}
	public Shop() {
	} 
	
}