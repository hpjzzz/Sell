package com.imooc.sell.redis;

import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.constant.CookieConstant;
import com.imooc.sell.constant.RedisConstant;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/redis")
public class RedisController {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Autowired
	private ProjectUrlConfig projectUrlConfig;

	@RequestMapping("/create")
	public ModelAndView createToken(HttpServletResponse response) {
		//准备token
		String token = UUID.randomUUID().toString();
		Integer expire = RedisConstant.EXPIRE;
		//设置token到redis
		redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), "theopenid2", expire, TimeUnit.SECONDS);

		CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

		return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/seller/order/list");
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request,
							   HttpServletResponse response,
							   Map<String, Object> map) {

		//1.从cookie里查询
		Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
		if (cookie != null) {
			//2.清除redis
			redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
			//3。清除cookie
			CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
		}
		map.put("msg", ResultEnum.LOGOUT_SUCCESS.getMessage());
		map.put("url", "/sell/seller/order/list");
		return new ModelAndView("common/success", map);
	}

	@GetMapping("/login")
	public ModelAndView login(Map<String, Object> map) {
		return new ModelAndView("redis/zzz");
	}
}
