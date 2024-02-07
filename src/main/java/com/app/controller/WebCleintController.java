package com.app.controller;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import reactor.core.publisher.Mono;

@RestController
public class WebCleintController {
	
	@GetMapping("/getsecuredata")
	public Mono<String> getData() {
		String apiUrl="http://localhost:8081/getdata";
		WebClient wc= WebClient.create(); 
		String plainCreds="root:root@123";
		
		Mono<String> bodyToMono=(Mono<String>) wc.get()
		  .uri(apiUrl)
		  .header("Authorization", "Basic "+ Base64.encodeBase64String(plainCreds.getBytes()))
		  .retrieve()
		  .bodyToMono(String.class);
		  
		return bodyToMono;
	}

}
