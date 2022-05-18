package com.ncs.qr.ly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ncs.qr.ly.model.UrlModel;

public interface UrlRepository extends JpaRepository<UrlModel, Integer> {
	

}
