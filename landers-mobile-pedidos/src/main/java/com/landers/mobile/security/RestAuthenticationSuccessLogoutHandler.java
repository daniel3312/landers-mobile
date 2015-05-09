/**
 * 
 */
package com.landers.mobile.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landers.mobile.util.Constants;

/**
 * @author dhernandez
 *
 */
@Component
public class RestAuthenticationSuccessLogoutHandler extends
		SimpleUrlLogoutSuccessHandler {
	private final ObjectMapper mapper;

	@Autowired
	RestAuthenticationSuccessLogoutHandler(
			MappingJackson2HttpMessageConverter messageConverter) {
		this.mapper = messageConverter.getObjectMapper();
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "Logout Success");
		PrintWriter writer = response.getWriter();
		response.setContentType(Constants.CONTENT_TYPE);
		mapper.writeValue(writer, map);
		writer.flush();
	}
}
