package com.hwei.learning.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


import com.hwei.learning.model.Circle;

public class JdbcTemplateImpl extends JdbcDaoSupport {
	
	public Circle queryCircleWithId(int id) {
		String sql = "select * from Circle where id = ?";
		return this.getJdbcTemplate().queryForObject(sql, new Object[] {id}, new CircleMapper());
		
	}
	
	private static final class CircleMapper implements RowMapper<Circle>{

		@Override
		public Circle mapRow(ResultSet rs, int rowNum) throws SQLException {
			Circle circle = new Circle();
			circle.setId(rs.getInt("id"));
			circle.setName(rs.getString("name"));
			
			return circle;
			
		}
		
	}

}
