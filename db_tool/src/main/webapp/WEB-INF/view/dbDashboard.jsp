<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String sContextPath = request.getContextPath(); 
System.out.println("sContextPath::="+sContextPath);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
<script type="text/javascript">
	var sContextPath  = "<%=sContextPath%>";
</script>
<script src="${pageContext.request.contextPath}/resources/js/db.js"></script>

<style type="text/css">
.dbContainer{
	display: flex;
	flex-direction: column;
	height: 100%;
	border: 1px solid black;
}
.welcomeContainer{
	display: flex;
	height: 35px;
	align-items: center;
	background-color: #e0e0d1;
}
.mainContainer{
	display: flex;
	border: 1px solid black;
	padding: 10px;
	height: 618px; 
}
.inputContainer{
	flex-grow: 1;
	display: flex;
	flex-direction: column;
	min-height: 50%;
	width: 600px;
}
.userContainer{
	display: flex;
	width: 300px;
	height: 100%;
	align-items: center;
	justify-content: center;
	font-family: Times New Roman;
}
.aprovalContainer{
	display: flex;
	width: 850px;
	height: 100%;
	align-items: center;
	justify-content: flex-end;
	font-family: Times New Roman;
}
.logoutContainer{
	display: flex;
	width: 200px;
	height: 100%;
	align-items: center;
	justify-content: flex-end;
	font-family: Times New Roman;
}
.queryConnections{
	display: flex;
	height: 250px;
	align-items: center;
	border-top: 1px solid black;
	flex-direction: row;
	border-left: 1px solid black;
}
.connectionContainer{
	width: 160px;
	height: 100%;
	padding: 1px;
	border-right: 1px solid black;
	overflow: scroll;
	overflow-x: hidden;
}
.container-items{
	/* display: flex; */
	padding: 1px;
	align-items: center;
	/* flex-direction: column; */
}
.header-item{
	display: flex;
	justify-content: center;
	font-family: Times New Roman;
	background-color: #e0e0d1;
	border-bottom: 1px solid black;
}
.connection-item{
	display: flex;
	padding: 1px;
	cursor: pointer;
	border-bottom: 1px solid black;
	justify-content: center;
	font-family: Times New Roman;
}
.sqlContainer{
	display: flex;
	width: 100%;
	height: 100%;
	align-items: center;
	justify-content: center;
	flex-grow: 2;
}
.actionContainer{
	display: flex;
	align-items: center;
	height: 35px;
	border-top: 1px solid black;
	flex-direction: row;
	background-color: #e0e0d1;
	border-left: 1px solid black;
	/* width: 150px; */
}
.execute-query .commit-query .rollback-query .history .exportFormats .export{
	text-align: center;
	/* padding: 30px; */
	/* font-size: 20px; */
	width: 20px;
}
.queryContainer{
	display: flex;
	width: 160px;
	height: 100%;
	justify-content: center;
	align-items: center;
	border-right: 1px solid black;
}
.exportContainer{
	display: flex;
	width: 100%;
	height: 35px;
	justify-content: flex-end;
	align-items: center;
	border-right: 1px solid black;
	flex-direction: row;
}
.outputContainer{
	/* display: flex; */
	height: 335px;
	/* align-items: center;
	flex-direction: row;
	justify-content: center; */
	width: 100%;
	border-top: 2px solid black;
	border-left: 1px solid black;
	border-right: 1px solid black;
	border-bottom: 1px solid black;
	overflow: scroll;
	/* overflow-x: hidden; */
	overflow-y: auto;
	flex-grow: 1;
}
textarea {
	width: 100%;
	height: 100%;
	font-size: small;
	font-family: Times New Roman;
}
table, th, td {
	 border: 1px solid #CCC;
	 border-collapse: collapse;
	 text-align: center;
}
th{
padding: 8px;
}
.column-headings {
	 background: #375A82;
     color: white;
}

</style>

</head>
<body>

<div class="dbContainer">
	<form method="post" action="" name="dbForm">
		<input type="hidden" name="connectionName" value="" id="connectionName" />
		<div class="welcomeContainer">
			<div class="userContainer"><b>Welcome User</b></div>
			<div class="aprovalContainer"><b>Send commands for approval</b></div>
			<div class="logoutContainer"><b>Logout</b></div>
		</div>
		
		<div class="mainContainer"> 
		
			<div class="inputContainer">
			
				<div class="queryConnections">
					<div class="connectionContainer"><!-- Connections -->
						<div class="container-items">
							<div class="header-item"><b>CONNECTIONS</b></div>
							<c:forEach var="con" items="${connectionInfo }">
								<div class="connection-item"><b>
									<c:out value="${con}" />
								</b></div>
							</c:forEach>
						</div>
					</div>
					<div class="sqlContainer">
						<textarea id="sqlEditor" name="sqlEditor"></textarea>
					</div>
				</div>
				
				<div class="actionContainer">
					<div class="queryContainer">
							<div class="execute-query">
								<button id="execute" name="execute" type="button" class="btn btn-default btn-sm">
			          				<span>
			          					<%-- <img alt="Can't display" title="Execute" src="${pageContext.request.contextPath}/resources/images/execute.png" height="25px" width="25px"/> --%>
			          					<i title="Execute" class="fa fa-play-circle-o" aria-hidden="true" style="font-size:20px;color:blue;"></i>
			          				</span>
			        			</button>&nbsp;
			        		</div>
			        		
			        		<div class="commit-query">
			        			<button id="commit" type="button" class="btn btn-default btn-sm">
				          			<span>
				          				<i title="Commit" class="fa fa-check-circle-o" aria-hidden="true" style="font-size:20px;color:green;"></i>
				          			</span>
			        			</button>&nbsp;
			        		</div>
			        		
							<div class="rollback-query">
								<button id="rollback" type="button" class="btn btn-default btn-sm">
				          			<span>
				          				<i title="Rollback" class="fa fa-undo" aria-hidden="true" style="font-size:20px;color:blue;"></i>
				          			</span>
				        		</button>
				        	</div>
					</div>
					
					<div class="exportContainer">
							<div class="history">
								<button type="button" class="btn btn-default btn-sm">
				          			<span>
				          				<i title="Query history" class="fa fa-list" aria-hidden="true" style="font-size:20px;color:green;"></i>
				          			</span>
				        		</button>&nbsp;
				        	</div>
		
						<div class="exportFormats">
							<select style="font-size:17px;font-family: Times New Roman;" class="form-select form-select-sm" aria-label=".form-select-sm example">
								<option selected="selected">SELECT</option>
								<option value="XLSX">XLSX</option>
								<option value="TXT">TXT</option>
							</select>&nbsp;
						</div>
						
						<div class="export">
							<button type="button" class="btn btn-default btn-sm">
				          		<span>
				          			<i title="Export" class="fa fa-download" aria-hidden="true" style="font-size:20px;color:green;"></i>
				          		</span>
				        	</button>
				        </div>
					</div>
				</div>
				
				<div class="outputContainer" id="outputContainer">
					<!-- <table>
						<tbody>
							<tr>
								<th>NAME</th>
								<th>NAME</th>
								<th>NAME</th>
								<th>NAME</th>
							</tr>
							<tr class="column-headings">
								<td>VALUE</td>
								<td>VALUE</td>
								<td>VALUE</td>
								<td>VALUE</td>
							</tr>
							<tr class="column-headings">
								<td>VALUE</td>
								<td>VALUE</td>
								<td>VALUE</td>
								<td>VALUE</td>
							</tr>
						</tbody>
					</table> -->
				</div>
				
			</div>
		</div>
	</form>
</div>

</body>
</html>