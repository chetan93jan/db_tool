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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/dashboard.css" />
<script src="${pageContext.request.contextPath}/resources/js/db.js"></script>

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