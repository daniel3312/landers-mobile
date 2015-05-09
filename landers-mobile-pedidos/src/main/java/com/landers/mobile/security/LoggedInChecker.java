package com.landers.mobile.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.landers.mobile.beans.User;

@Component
public class LoggedInChecker {
	public User getLoggedInUser() {
		User user = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();

			// principal can be "anonymousUser" (String)
			if (principal instanceof LandersUserDetails) {
				LandersUserDetails userDetails = (LandersUserDetails) principal;
				user = userDetails.getUser();
			}
		}

		return user;
	}
}
