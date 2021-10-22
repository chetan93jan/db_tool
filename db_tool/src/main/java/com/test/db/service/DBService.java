package com.test.db.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.test.db.dao.DBDao;

@Component
public class DBService {
	
	@Autowired
	public DBDao dbDao;
	
//	@Autowired
//	public DBScope dbScope;
//	
//	@Autowired
//	public TransactionDefinition transactionDefinition;

	public List<String> getConnectionInfo() {
		return dbDao.getConnectionInfo();
	}

	public String executeQry(String executeQry) {
		String exeQry = executeQry.toUpperCase();
		if(exeQry.contains("INSERT") || executeQry.contains("UPDATE") || exeQry.contains("DELETE")) {
			return executeUpdate(executeQry);
		}else {
			return execute(executeQry);
		}
	}
	
	private String executeUpdate(String executeQry) {
		String exeQry = executeQry.toUpperCase();
		JSONObject json = new JSONObject();
		int iRecords = dbDao.executeUpdate(executeQry);
		if(exeQry.contains("INSERT")) {
			json.put("recordsModified", "Total records inserted:= "+iRecords);
		}else if(exeQry.contains("UPDATE")) {
			json.put("recordsModified", "Total records updated:= "+iRecords);
		}else if(exeQry.contains("DELETE")) {
			json.put("recordsModified", "Total records deleted:= "+iRecords);
		} 
		return json.toString();
	}

	private String execute(String executeQry) {
		JSONObject json = new JSONObject();
		List<String> mHeaderKeys = null;
		List<Map<String,Object>> lRec = dbDao.executeQry(executeQry);

		if(lRec!= null && !lRec.isEmpty()) {
			mHeaderKeys = lRec.get(0).entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toCollection(ArrayList::new));
			json =  new JSONObject();
			json.put("header", mHeaderKeys);
			json.put("content", lRec); 
		}
		return json.toString();
	}
	
	public String rollBack() {
		dbDao.rollback();
		return "Rollback Success";
	}

	public String commit() {
		dbDao.commit();
		return "Commit Success";
	}

}
