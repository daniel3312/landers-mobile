/**
 * 
 */
package com.landers.mobile.dao;

import java.util.List;

import com.landers.mobile.beans.User;

/**
 * @author USUARIO
 *
 */
public interface UserDao {
	User getUser(String document);
	
	List<String> getUserRoles(String document);
}
