package com.eden.intercepor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.eden.utils.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginCheckintercepor implements HandlerInterceptor {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		String url = request.getRequestURI().toString();
		log.info("リクエストURL:{}:",url);
		
		if(url.contains("login")||url.contains("regist")
				||url.contains("css")
				||url.contains("script")
				||url.contains("img"))
		{
			log.info("ログイン許可");
			return true;
		}
		String jwt=null;
		HttpSession session=request.getSession();
		if(session.getAttribute("token")!=null) {
			jwt=session.getAttribute("token").toString();
		}
		if(!StringUtils.hasLength(jwt)||jwt.isEmpty())	{
			log.info("トークンが空になる");
			//response.sendRedirect("http://localhost:10000/empthymeleaf/login");
			response.sendRedirect(request.getContextPath()+"/login");
			return true;
		}
		try {
			JwtUtils.parseJWT(jwt);	
			}catch (Exception e) {
				e.printStackTrace();
				log.info("再度ログインしてください");
				return true;
			}
			log.info("ログイン成功");
		return true;
	}

}
