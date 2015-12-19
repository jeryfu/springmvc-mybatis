<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/meta.jsp" %>
<title>DEMO</title>
<style type="text/css">
	a {
		text-decoration: none;
		color: red;
	}
	
	a.edit{
		color: blue;
	}
	
	a.add{
		color: #930000;
		font-weight: bold;
	}
	.container{
		width: 500px;
	}
	div .left{
		float: left;
		display: inline;
	}
	
	div .right{
		float: right;
		display: inline;
	}
	
	
</style>

<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var name = $(this).attr("name");
			$("form").attr("action",name).submit();
			return false;
		});
	})
	
	function nextPage(_pageNo){
		var _url = $("#url").val();
		window.location.href = _url + _pageNo;
	}
</script>
</head>
<body>

	<form action="" method="post">
		<input type="hidden" value="DELETE" name="_method" />
	</form>
	
	<input type="hidden" value="${ctx }/demo?pageNo=" id="url" />
	
	<br>
	<a class="add" href="${ctx }/demo/input">Add</a> 
	<br>
	<br>
	
	<div class="container">
		<table cellpadding="0" cellspacing="0" border="1">
			<thead>
				<tr>
					<th width="120px">No</th>
					<th width="120px">Key</th>
					<th width="120px">Value</th>
					<th width="140px">Operate</th>
				</tr>
			</thead>
			<c:forEach items="${page.list }" var="demo" varStatus="status">
				<tr align="center">
					<td>${status.index + 1 }</td>
					<td>${demo.key }</td>
					<td>${demo.value }</td>
					<td>
						<a class="edit" href="${ctx }/demo/input/${demo.id }">edit</a> | 
						<a class="delete" href="javascript:void()" name="demo/${demo.id }">delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="/common/page.jsp" %>
	</div>
</body>
</html>