package com.test.db.datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBDataSource {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public enum Type {
		INTEGER,
		LIST_MAP,
		LIST
	}
	
	@SuppressWarnings("unchecked")
	public <T>T executeSQL(String sqlQuery, Type type) {
		Object oResult = null;
		if(jdbcTemplate != null) {
			switch(type) {
			case INTEGER: 
				{
					oResult = jdbcTemplate.execute(sqlQuery, (PreparedStatement ps) -> {
						return ps.executeUpdate();
					});
				}
				break;
			case LIST_MAP:
				{
					List<Map<String,Object>> lRet = new ArrayList<Map<String,Object>>();
					oResult = jdbcTemplate.query(sqlQuery, (ResultSet rs) -> {
						Object oRes = null;
						ResultSetMetaData metaData = rs.getMetaData();
						while(rs.next()) {
							Map<String,Object> mRetData = new LinkedHashMap<String,Object>();
							for(int i = 1; i <= metaData.getColumnCount() ;i++) {
								String sKey = metaData.getColumnName(i);
								mRetData.put(sKey.toUpperCase(), rs.getObject(sKey)!= null ? rs.getObject(sKey) : "--");
							}
							lRet.add(mRetData);
						}
						oRes = lRet;
						return oRes;
					});
				}
				break;
			case LIST:
				{
					List<Object> lRet = new ArrayList<Object>();
					oResult = jdbcTemplate.query(sqlQuery, (ResultSet rs) -> {
						Object oRes = null;
						ResultSetMetaData metaData = rs.getMetaData();
						while(rs.next()) {
							if(metaData.getColumnCount() > 0) {
								lRet.add(rs.getObject(metaData.getColumnName(1)));
							}
						}
						oRes = lRet;
						return oRes;
					});
				}
				break;
			default:
				oResult = "";
				break;
			}
		}

		if(oResult == null) {
			switch(type) {
			case INTEGER: 
				oResult = 0;
			break;
			case LIST_MAP:
			case LIST:
				oResult = Collections.EMPTY_LIST;
				break;
			default:
				oResult = "";
				break;
			}
		}
		return (T) oResult;
	}

	public void commit() {
		DataSource dataSource = (BasicDataSource) jdbcTemplate.getDataSource();
		try {
			dataSource.getConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollback() {
		DataSource dataSource = (BasicDataSource) jdbcTemplate.getDataSource();
		try {
			dataSource.getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
