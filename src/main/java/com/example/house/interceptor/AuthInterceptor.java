package com.example.house.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.house.constants.SessionConstants;
import com.example.house.error.BusinessException;
import com.example.house.error.EmBusinessError;
import com.example.house.service.model.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	    String reqUri =	request.getRequestURI();
	    //判断是否是静态资源请求或者error请求
	    if (reqUri.startsWith("/html") || reqUri.startsWith("/error") ) {
			return true;
		}
	    //参数true表示不存在session时自动创建
	    HttpSession session = request.getSession(true);
	    UserModel userModel = (UserModel)session.getAttribute(SessionConstants.USER);
	    if (userModel == null) {
			throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	

}
