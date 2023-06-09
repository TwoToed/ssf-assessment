package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.ssf.frontcontroller.model.Login;

@Repository
public class AuthenticationRepository {


	@Autowired 
	@Qualifier("login")
	private RedisTemplate<String, Object> template;

	public void disable(String username){
		template.opsForValue()
			.set(username, "banned", 30, TimeUnit.MINUTES);
	}

	public String checkdisabled(String username){
		return (String) template.opsForValue().get(username);
	}

	}