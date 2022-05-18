package com.ncs.qr.ly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "urls")
public class UrlModel extends BaseEntity {
	@Column(unique = true, nullable = false)
	public String url;
	public UrlModel() {
		
	}
	public UrlModel(String url) {
		this.url = url;
	}
}
