/**
 * 
 */
package com.landers.mobile.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.landers.mobile.beans.User;
import com.landers.mobile.dao.UserDao;

/**
 * @author USUARIO
 * 
 */
public class UserDaoJdbc implements UserDao {
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public User getUser(String document) {
		String sql = "select * from tb_user where document = :document";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("document", document);
		List<User> users=jdbcTemplate.query(sql, paramSource,
				new UserRowMapper());
		if(users.isEmpty()){
			return null;
		}
		//new Object[] { id }
		return users.get(0);
	}

	@Override
	public List<String> getUserRoles(String document) {
		String sql = "select * from tb_user_role where document = :document";
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("document", document);
		List<String> roles = jdbcTemplate.query(sql, paramSource,
				new RowMapper<String>() {
					public String mapRow(ResultSet resultSet, int i)throws SQLException {
						return resultSet.getString(2);
					}
				});
		return roles;
	}

}
