package com.ncs.qr.ly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Names")
public class Names extends BaseEntity {
	@Column(unique = true, nullable = false)
	public String name;
	public Names(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
