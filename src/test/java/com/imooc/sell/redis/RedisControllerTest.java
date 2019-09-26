package com.imooc.sell.redis;

import com.imooc.sell.constant.CookieConstant;
import com.imooc.sell.constant.RedisConstant;
import com.imooc.sell.utils.CookieUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisControllerTest {

	@Autowired
	private StringRedisTemplate redisTemplate;
	@Test
	public void createToken(HttpServletResponse response) {

		//准备token
		String token = UUID.randomUUID().toString();
		Integer expire = RedisConstant.EXPIRE;
		//设置token
		redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), "theopenid", expire, TimeUnit.SECONDS);

		//设置token到cookie
//		Cookie cookie = new Cookie("token", token);
//		cookie.setPath("/");
//		cookie.setMaxAge(7200);
//		response.addCookie(cookie);
		CookieUtil.set(response, CookieConstant.TOKEN, token, expire);
	}
}