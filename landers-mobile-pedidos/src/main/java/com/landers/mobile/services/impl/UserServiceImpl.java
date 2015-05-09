package com.landers.mobile.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.landers.mobile.beans.User;
import com.landers.mobile.dao.UserDao;
import com.landers.mobile.security.LoggedInChecker;
import com.landers.mobile.services.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final LoggedInChecker loggedInChecker;
    
    @Autowired
    UserServiceImpl(LoggedInChecker loggedInChecker) {
        this.loggedInChecker = loggedInChecker;

    }
    
    @Autowired 
    UserDao userDao;
    @Override
    public User getUserByUsername(String username) {
        // DAO to load user from the database
    	User user=userDao.getUser(username);
        if (user!=null) {
            //user.setPassword(new ShaPasswordEncoder().encodePassword("password", null));
            //getPermissions(username).add("ROLE_ADMIN");
            return user;
        } else {
            return null;
        }
    }
    
    @Override
    public List<String> getPermissions(String username) {
        return userDao.getUserRoles(username);
    }

	@Override
    public Boolean isCurrentUserLoggedIn() {
        return loggedInChecker.getLoggedInUser() != null;
    }
}
