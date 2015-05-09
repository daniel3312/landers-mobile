/**
 * 
 */
package com.landers.mobile.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.landers.mobile.beans.User;
import com.landers.mobile.services.UserService;

/**
 * @author USUARIO
 * 
 */
public class LandersUserDetailsService implements UserDetailsService {
	private final UserService userService;

	@Autowired
	LandersUserDetailsService(UserService userService) {
		this.userService = userService;
	}


	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		List<String> permissions = userService.getPermissions(user.getDocument());
		for (String permission : permissions) {
			grantedAuthorities.add(new SimpleGrantedAuthority(permission));
		}

		return new LandersUserDetails(user, grantedAuthorities);
	}

}
