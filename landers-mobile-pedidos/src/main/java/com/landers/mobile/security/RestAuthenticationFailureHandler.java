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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landers.mobile.util.Constants;

/**
 * @author dhernandez
 *
 */
@Component
public class RestAuthenticationFailureHandler extends
		SimpleUrlAuthenticationFailureHandler {

	private final ObjectMapper mapper;

	@Autowired
	RestAuthenticationFailureHandler(
			MappingJackson2HttpMessageConverter messageConverter) {
		this.mapper = messageConverter.getObjectMapper();
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException ex)
			throws IOException, ServletException {

		Map<String, String> error = new HashMap<String, String>();
		error.put("error", ex.getMessage());
		PrintWriter writer = response.getWriter();
		response.setContentType(Constants.CONTENT_TYPE);
		mapper.writeValue(writer, error);
		writer.flush();
	}

}
