package com.hwei.learning.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.hwei.learning.annotation.TransactionAnnotation;
import com.hwei.learning.model.Circle;

@Component
public class CircleResposity {
	
	@Autowired
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedTemplate;
	

	public DataSource getDatasource() {
		return datasource;
	}

	@Autowired
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
		this.namedTemplate = new NamedParameterJdbcTemplate(datasource);
	}


	/**
	 * Just use spring jdbc
	 * 
	 */
	public Circle queryCircleById(String value,int id) {
		
		Circle circle=null;
		Connection conn=null;
		String strSelect = "select * from Circle where name=? and id = ?";
		
		try {
			
			//实例化JDBC Driver
			//String driver = "com.mysql.jdbc.Driver";
			//Class.forName(driver).newInstance();
			
			//打开数据库连接
			conn = datasource.getConnection();
			
			//SQL语句查询
			PreparedStatement ps = conn.prepareStatement(strSelect);
			ps.setString(1, value);
			ps.setInt(2, id);
			ResultSet rset = ps.executeQuery();
			
			//如果查出结果，返回数据类型
			while(rset.next()) {
				String name = rset.getString("name");
				circle = new Circle(id,name); 
			}
			
			//关闭SQL语句
			ps.close();
			//关闭查询结果
			rset.close();
			
		} catch (SQLException e) {
			System.out.println("SQLException: "+e.getMessage());
		}finally {
			try {
				//关闭数据库连接
				conn.close();
			} catch (SQLException e) {
				System.out.println("Exception: "+e.getMessage());
			}
		}
		
		return circle;	
	}
	
	/**
	 * Use spring jdbc template
	 * 
	 */
	public Circle queryCircleById(int id) {
		String sql = "select * from Circle where id = ?";
		Circle circle = null;
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, new Object[] {id});
		while(rowSet.next()) {
			String name = rowSet.getString("name");
			circle = new Circle(id,name); 
		}
		
		return circle;		
	}
	
	public Circle queryCircleWithId(int id) {
		String sql = "select * from Circle where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {id}, new CircleMapper());
		
	}
	
	public List<Circle> queryCircles(){
		
		String sql = "select * from Circle";
			
		return jdbcTemplate.query(sql, new CircleMapper());	
	}
	
	public void updateCircleName(String name,int id) {
		String sql = "UPDATE circle set name=? where id = ?";
		jdbcTemplate.update(sql, new Object[] {name,id});
		
	}
	
	@TransactionAnnotation
	public void insertCircle(Circle circle) {
		String sql = "insert into circle (id,name) values (:id,:name)";
		Map map = new HashMap<String,Object>();
		map.put("id", circle.getId());
		map.put("name", circle.getName());
		namedTemplate.update(sql, map);
		
		//jdbcTemplate.update(sql, new Object[] {circle.getId(),circle.getName()});
		
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


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	


}
