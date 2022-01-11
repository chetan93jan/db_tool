var dbUrl = sContextPath;
$(document).ready(function(){
	$("#execute").click(function(){
		 var sqlVal  = $('#sqlEditor')[0];
    	 var start = sqlVal.selectionStart;
    	 var finish = sqlVal.selectionEnd;
    	 var executeQry = sqlVal.value.substring(start,finish);
	    	$.ajax({                                          
					url: dbUrl+"/execute",
					type: "post", 
					data: 'executeQry='+executeQry,                      
				    dataType: "JSON",                 
				    success: function(data) {
//				    	console.log("Value ::= "+JSON.stringify(data));
				    	var displayData = '<table><tbody>';
				    	if(!$.isEmptyObject(data)){
				    		if(data.header != null){
					    		var header 	= data.header;
					    		var content = data.content;
					    		displayData += '<tr class="column-headings">';
					    		for(item of header){
					    			displayData += '<th>'+item+'</th>';
					    		}
					    		
					    		displayData += '</tr>';
					    		var i = 1;
					    		for(item of content) {
					    			displayData += (i % 2) == 0 ? '<tr style="background-color: #e0e0d1;">' : '<tr>';
					    			i++;
					    			for(element of header){
					    				$.each(item, function(key, value) {
					    					if(element == key){
//					    						console.log("Element:= "+element+" Key:= "+key+" Value:= "+value);
					    						displayData += '<td>'+value+'</td>';
					    						console.log(displayData);
					    						return;
					    					}
					    				});
					    			}
					    			displayData += '</tr>';
					    		}
					    		displayData += '</tbody></table>';
					    		$("#outputContainer").html("").append(displayData);
				    		}else if(data.recordsModified != null){
				    			$("#outputContainer").html("").append("<b>"+data.recordsModified+"</b>");
				    		}else if(data.SqlException != null){
				    			$("#outputContainer").html("").append("<b>"+data.SqlException+"</b>");
				    		}
				    	}
					},error: function() {
						$("#outputContainer").html("").append("<b>Error</b>");
					}
			});
		});
	
	$("#rollback").click(function(){
		$.ajax({
			url: dbUrl+"/rollback",
			type: "post",
			success: function(data) {
				$("#outputContainer").html("").append("<b>"+data+"</b>");
			},error: function(){
				$("#outputContainer").html("").append("<b>Rollback error</b>");
			}
		});
	});
	
	$("#commit").click(function(){
		$.ajax({
			url: dbUrl+"/commit",
			type: "post",
			success: function(data) {
				$("#outputContainer").html("").append("<b>"+data+"</b>");
			},error: function(){
				$("#outputContainer").html("").append("<b>Commit error</b>");
			}
		});
	});
	
});