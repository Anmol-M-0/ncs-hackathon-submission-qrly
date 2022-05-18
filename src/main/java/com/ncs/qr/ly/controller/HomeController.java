package com.ncs.qr.ly.controller;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ncs.qr.ly.repository.UrlRepository;

@Controller
public class HomeController {
	@Autowired
	private UrlRepository urlRepository;
	@GetMapping("/")
	public String getHome() {
		return "/WEB-INF/views/index.jsp";
	}
	@GetMapping("/qrly")
	public String getHqrly() {
		return "/static/qrly.html";
	}
//	@GetMapping("/shorturl/{id}")
//	private void redirectToUrl(@PathVariable("id") Integer id, HttpServletResponse httpServletResponse) {
//		String url = urlRepository.findById(id).get().url; 
//		httpServletResponse.setHeader("Location", url);
//	    httpServletResponse.setStatus(302);
//	}
//	@GetMapping("/shorturl/{id}")
//	private String redirectToUrl(@PathVariable("id") Integer id) {
//		String url = urlRepository.findById(id).get().url; 
//		return "redirect:"+url;
//	}
	
}
