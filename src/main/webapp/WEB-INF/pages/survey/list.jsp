<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>我的调查</title>
	<%@ include file="/common/meta.jsp" %>
	<script type="text/javascript">
		$(function(){
			/* 清除调查 */
			methodHandler(".clear.answer.aList", "DELETE");
			
			/* 删除调查 */
			methodHandler(".delete.survey.aList", "DELETE");
			
			/* 修改状态 */
			methodHandler(".update.toggle.aList", "PUT");
		})
		
		function methodHandler(parameter, method){
			$(parameter).click(function(){
				var url = $(this).attr("name");
				$("#_method").val(method);
				$("form").attr("action", url).submit();
				return false;
			});
		}
		
	</script>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<c:if test="${empty surveys}">
		您还没有创建调查
	</c:if>
	<c:if test="${!empty surveys }">
		<form action="" method="post">
			<input type="hidden" name="_method" value="" id="_method"/>
		</form>
		<table>
			<tr>
				<td colspan="10" style="height: 5px"></td>
			</tr>
			<tr>
				<td colspan="10" class="tdHeader">我的调查:</td>
			</tr>
			<tr>
				<td colspan="10" style="height: 5px"></td>
			</tr>
			<tr>
				<td class="tdListHeader">ID</td>
				<td class="tdListHeader">调查标题</td>
				<td class="tdListHeader">创建时间</td>
				<td class="tdListHeader">状态</td>
				<td class="tdListHeader">设计</td>
				<td class="tdListHeader">收集信息</td>
				<td class="tdListHeader">分析</td>
				<td class="tdListHeader">打开/关闭</td>
				<td class="tdListHeader">清除调查</td>
				<td class="tdListHeader">删除</td>
			</tr>
			<c:forEach items="${surveys }" var="survey">
				<tr>
					<td>${survey.id }</td>
					<td>${survey.title }</td>
					<td><fmt:formatDate value="${survey.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<c:choose>
							<c:when test="${survey.closed == true }">关闭</c:when>
							<c:otherwise>开放</c:otherwise>
						</c:choose>
					</td>
					<td><a href="${ctx }/survey/${survey.id}" class="aList"> 设计 </a></td>
					<td>收集信息</td>
					<td>分析</td>
					<td><a href="javascript:void()" class="update toggle aList" name="${ctx }/survey/toggle/${survey.id}">打开/关闭</a></td>
					<td><a href="javascript:void()" class="clear answer aList" name="${ctx }/survey/clear/${survey.id}">清除调查</a></td>
					<td><a href="javascript:void()" class="delete survey aList" name="${ctx }/survey/${survey.id}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
</body>
</html>