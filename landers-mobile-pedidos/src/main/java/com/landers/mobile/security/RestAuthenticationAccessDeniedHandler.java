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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landers.mobile.util.Constants;

/**
 * @author dhernandez
 *
 */
@Component
public class RestAuthenticationAccessDeniedHandler extends
		AccessDeniedHandlerImpl {
	private final ObjectMapper mapper;

	@Autowired
	RestAuthenticationAccessDeniedHandler(
			MappingJackson2HttpMessageConverter messageConverter) {
		this.mapper = messageConverter.getObjectMapper();
	}

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response, AccessDeniedException ex)
			throws IOException, ServletException {

		Map<String, String> error = new HashMap<String, String>();
		error.put("error", ex.getMessage());
		PrintWriter writer = response.getWriter();
		response.setContentType(Constants.CONTENT_TYPE);
		mapper.writeValue(writer, error);
		writer.flush();
	}
}
