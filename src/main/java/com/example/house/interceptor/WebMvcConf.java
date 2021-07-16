package com.example.house.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConf implements WebMvcConfigurer {
	
	@Autowired
	private AuthInterceptor authInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry){
		/**
		 * 添加的顺序表示拦截顺序
		 */
		registry.addInterceptor(authInterceptor)
				.excludePathPatterns("/html")//authInterceptor这个拦截器不拦截static，除此之外的其他都拦截
				.addPathPatterns("/houseUser/likeHouse").addPathPatterns("/user/inf")
				.addPathPatterns("/houseUser/myLikeHouses").addPathPatterns("/house/rentHouse")
				.addPathPatterns("/order/list").addPathPatterns("/house/myHouse")
				.addPathPatterns("/house/addHouse");
	}
	
}
