package com.stackroute.oauth2authentication;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableEurekaClient
//@RequestMapping("/")
public class OAuth2Authentication{




	@GetMapping("/googleuser")
	public String googleUser(@AuthenticationPrincipal OAuth2User user1){
		String email=user1.getAttribute("email");
		String name=user1.getAttribute("name");
		System.out.println(email);
		System.out.println(name);
		return email;
	}


		public static void main (String[]args){
			SpringApplication.run(OAuth2Authentication.class, args);
		}






}
