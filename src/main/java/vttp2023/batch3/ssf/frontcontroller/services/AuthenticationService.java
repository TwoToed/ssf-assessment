package vttp2023.batch3.ssf.frontcontroller.services;


import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.ssf.frontcontroller.model.Captcha;
import vttp2023.batch3.ssf.frontcontroller.respositories.AuthenticationRepository;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationRepository repo;
	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public int authenticate(String username, String password)throws Exception{
		String url = "https://authservice-production-e8b2.up.railway.app";
		JsonObject json = Json.createObjectBuilder()
			.add("username", username)
			.add("password", password).build();
		System.out.println(json.toString());
		RequestEntity<String> req = RequestEntity
		.post(url + "/api/authenticate")
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(json.toString(), String.class);

		RestTemplate template = new RestTemplate();
		try {
			ResponseEntity<String> resp = template.exchange(req, String.class);
			System.out.println(resp.getStatusCode());
			System.out.println(resp.getBody());
			return 1;
		} catch (Exception e) {
			return 0;
			// TODO: handle exception
		}
		

	}
	
	public Captcha captcha(){
		int num1 = ThreadLocalRandom.current().nextInt(1, 50 + 1);
		int num2 = ThreadLocalRandom.current().nextInt(1, 50 + 1);
		int ran4 = ThreadLocalRandom.current().nextInt(1, 4 + 1);
		String operation = "";
		int answer = 0;
		switch (ran4) {
			case 1:
			operation = "+";
			answer = num1 + num2;
			break;
			case 2:
			operation = "-";
			answer = num1 - num2;
			break;
			case 3:
			operation = "/";
			answer = Math.round(num1 / num2);
			break;
			case 4:
			operation = "*";
			answer = num1 + num2;
			break;
		}
		String question = "What is " + num1 + operation + num2 + "?";
		Captcha captcha = new Captcha(question, answer);
		return captcha;
	}
	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		repo.disable(username);
	}

	public String checkdisabled(String username){
		return repo.checkdisabled(username);
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
