package com.ncs.qr.ly.controller;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ncs.qr.ly.model.Names;
import com.ncs.qr.ly.model.UrlModel;
import com.ncs.qr.ly.repository.NameRepository;
import com.ncs.qr.ly.repository.UrlRepository;

import dto.UrlRequest;

@RestController
@RequestMapping("/shorturl")
public class RestEndpoints {
	@Autowired
	private NameRepository nameRepository;
	@Autowired
	private UrlRepository urlRepository;
	String response = "{\"%s\":\"%s\"}";
	String shorturlResponse = "{\"%s\":\"%s\",\"%s\":\"%s\"}";
	class ShortResponse {
		public String info;
		public String id;
		public String url;
		public ShortResponse(String info,Integer id, String url) {
			this.info = info;
			this.id = id.toString();
			this.url = url;
		}
	}
	@PostMapping("/hello-world")
	@ResponseBody
	public String getHelloWorld(@RequestParam Optional<String> name) {
		try {
			return getHelloWorldHelper(name);
		} catch (Exception e) {
			return String.format(response, "responseString", " " + name.orElseGet(() -> ""));
		}
	}

	private String getHelloWorldHelper(Optional<String> name) throws SQLIntegrityConstraintViolationException {
		if (name.isPresent()) {
			nameRepository.save(new Names(name.get()));
			return String.format(response, "responseString", "hello, world " + name.get());
		}
		return String.format(response, "responseString", "hello, world");
	}
	public class UrlBody {
		public String url;
	}
	
	@PostMapping("/")
	@ResponseBody
	public ShortResponse postUrlShorten(@RequestBody Optional<UrlRequest> url) {
		try {
			return postUrlShortenHelper(url);
		} catch (SQLIntegrityConstraintViolationException e) {
			return new ShortResponse("the url is already present", -1, url.get().url);
		} catch (Exception e) {
			return new ShortResponse("an error occured, message -> " + e.getMessage(), -1, url.get().url);
		}
	}

	private ShortResponse postUrlShortenHelper(Optional<UrlRequest> url) throws SQLIntegrityConstraintViolationException{
		UrlModel urlmodel = new UrlModel(url.orElseGet(() -> new UrlRequest("")).url);
		if (urlmodel.url != "") {
			urlmodel = urlRepository.save(urlmodel);
			return new ShortResponse("success", urlmodel.getId(), urlmodel.url);
		} else {
			return new ShortResponse("The url is empty", -1, "empty");
		}
	}
//	@PostMapping(value = "/redirect")
//	 public ResponseEntity<Void> redirect(@RequestParam Map<String,String> input){
//	 
//	     System.out.println(input);
//	 
//	     return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("https://fullstackdeveloper.guru")).build();
//	 }
	@GetMapping("/{id}")
	private ResponseEntity<Void> redirectToUrl(@PathVariable("id") Integer id) {
		String url = urlRepository.findById(id).get().url; 
		System.out.println("url = " + url);
		return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
	}
	
}
