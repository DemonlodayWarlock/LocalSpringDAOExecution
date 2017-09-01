package com.hwei.learning.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.hwei.learning.model.Circle;

@Component
public class CircleResposity {
	
	
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	

	public DataSource getDatasource() {
		return datasource;
	}

	@Autowired
	public void setDatasource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}



	/*public Circle queryCircleById(String value,int id) {
		
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
	}*/
	
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


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


}
