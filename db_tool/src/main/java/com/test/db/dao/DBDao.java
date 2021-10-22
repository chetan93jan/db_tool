package com.test.db.dao;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.db.datasource.DBDataSource;
import com.test.db.datasource.DBDataSource.Type;

@Repository
public class DBDao {

	@Autowired
	public DBDataSource  dBDataSource;
	
	public List<String> getConnectionInfo() {
		StringBuilder sbQuery = new StringBuilder("SELECT DISTINCT DB_CONNECTION FROM CONNECTION_PROFILES ");
		sbQuery.append("WHERE DB_DATASOURCE LIKE '%'||DB_CONNECTION||'%' AND DB_CONNECTION IN ('CON1','CON2','CON3','CON4') ORDER BY DB_CONNECTION ASC ");
		return dBDataSource.executeSQL(sbQuery.toString(), Type.LIST);
	}

	public List<Map<String,Object>> executeQry(String executeQry) {
		return dBDataSource.executeSQL(executeQry, Type.LIST_MAP);
	}

	public int executeUpdate(String executeQry) {
		return dBDataSource.executeSQL(executeQry, Type.INTEGER);
	}

	public void commit() {
		dBDataSource.commit();
	}

	public void rollback() {
		dBDataSource.rollback();
//		dBDataSource.defaultReadOnly();
	}

	
}
