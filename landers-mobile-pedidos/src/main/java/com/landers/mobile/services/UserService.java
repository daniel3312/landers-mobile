package com.landers.mobile.services;

import java.util.List;

import com.landers.mobile.beans.User;

public interface UserService {
	User getUserByUsername(String username);

	List<String> getPermissions(String username);

	Boolean isCurrentUserLoggedIn();
}
