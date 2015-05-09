package com.landers.mobile.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landers.mobile.beans.User;
import com.landers.mobile.util.Constants;

@Component
public class RestAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	private final ObjectMapper mapper;

	@Autowired
	RestAuthenticationSuccessHandler(
			MappingJackson2HttpMessageConverter messageConverter) {
		this.mapper = messageConverter.getObjectMapper();
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);

		LandersUserDetails userDetails = (LandersUserDetails) authentication
				.getPrincipal();
		User user = userDetails.getUser();
		user.setPassword(Constants.PASSWORD_MASK);
		userDetails.setUser(user);

		logger.info(userDetails.getUsername() + " got is connected ");
		logger.info("Authorities:" + userDetails.getAuthorities());
		PrintWriter writer = response.getWriter();
		response.setContentType(Constants.CONTENT_TYPE);
		mapper.writeValue(writer, user);
		writer.flush();
	}
}
