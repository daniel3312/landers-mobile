/**
 * 
 */
package com.landers.mobile.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.landers.mobile.beans.User;

/**
 * @author USUARIO
 * 
 */
public class UserRowMapper implements RowMapper<User> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public User mapRow(ResultSet rs, int row) throws SQLException {
		User user = new User();
		user.setDocument(rs.getString("document"));
		user.setFirstName(rs.getString("firstname"));
		user.setLastName(rs.getString("lastname"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		return user;
	}

}
