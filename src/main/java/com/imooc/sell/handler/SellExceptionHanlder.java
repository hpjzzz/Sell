package com.imooc.sell.handler;

import com.imooc.sell.config.ProjectUrlConfig;
import com.imooc.sell.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHanlder {

	@Autowired
	private ProjectUrlConfig projectUrlConfig;
	//拦截登录异常
	@ExceptionHandler(value = SellerAuthorizeException.class)
	public ModelAndView handlerSellerAuthorizeException() {
		return new ModelAndView("redirect:".concat(projectUrlConfig.getSell()
				.concat("/redis/login")));
	}
}
