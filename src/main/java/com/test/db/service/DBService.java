package com.test.db.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.test.db.dao.DBDao;
import com.test.db.utils.Utils;

@Component
public class DBService {
	
	@Autowired
	public DBDao dbDao;
	
	@Autowired
	public HttpSession session;

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
		int iRecords = 0;
		String exeQry = executeQry.toUpperCase();
		JSONObject json = new JSONObject();
		Object obj = dbDao.executeUpdate(executeQry);
		if(obj != null) {
			if (obj instanceof String) {
				String sQryResult = (String) obj;
				json = new JSONObject();
				json.put("SqlException", sQryResult);
			}else if(obj instanceof Integer) {
				iRecords = (int) obj;
				session.setAttribute("IsExecuted", "Executed");
				if(exeQry.contains("INSERT")) {
					json.put("recordsModified", "Total records inserted:= "+iRecords);
				}else if(exeQry.contains("UPDATE")) {
					json.put("recordsModified", "Total records updated:= "+iRecords);
				}else if(exeQry.contains("DELETE")) {
					json.put("recordsModified", "Total records deleted:= "+iRecords);
				} 
			}
		}
		return json.toString();
	}

	@SuppressWarnings("unchecked")
	private String execute(String executeQry) {
		JSONObject json = null;
		List<String> mHeaderKeys = null;
		List<Map<String,Object>> lRec = null;
		Object obj = dbDao.executeQry(executeQry);
		if(obj != null) {
			if (obj instanceof String) {
				String sQryResult = (String) obj;
				json = new JSONObject();
				json.put("SqlException", sQryResult);
			}else if(obj instanceof List<?>) {
				lRec = (List<Map<String, Object>>) obj;
			}
		}

		if(lRec!= null && !lRec.isEmpty()) {
			mHeaderKeys = lRec.get(0).entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toCollection(ArrayList::new));
			json =  new JSONObject();
			json.put("header", mHeaderKeys);
			json.put("content", lRec); 
		}
		return json.toString();
	}
	
	public String rollBack() {
		boolean bResult = false;
		if(!Utils.isEmpty(Utils.getValue(session, "IsExecuted", ""))) {
			bResult = dbDao.rollback();
			session.removeAttribute("IsExecuted");
		} 
		return bResult ? "Rollback Success" : "Rollback Falied";
	}

	public String commit() {
		boolean bResult = false;
		if(!Utils.isEmpty(Utils.getValue(session, "IsExecuted", ""))) {
			bResult = dbDao.commit();
			session.removeAttribute("IsExecuted");
		} 
		return bResult ? "Commit Success" : "Commit Falied";
	}

}
