<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>移动/复制页面</title>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<table>
		<tr>
			<td colspan="3" class="tdWhiteLine"></td>
		</tr>
		<tr>
			<td colspan="3" class="tdHeader">移动/复制页:[同一调查内是移动,不同调查间是复制]</td>
		</tr>
		<tr>
			<td colspan="3" class="tdWhiteLine"></td>
		</tr>
		<c:forEach items="${surveys }" var="survey">
			<tr>
				<td colspan="3" class="tdSHeaderL"><c:out value="${survey.title }" /></td>
			</tr>
			<c:forEach items="${survey.pages }" var="page">
				<c:choose>
					<c:when test="${page.id == srcPid }">
						<c:set var="bgcolor" value="#E3A869" />
					</c:when>
					<c:otherwise>
						<c:set var="bgcolor" value="white" />
					</c:otherwise>
				</c:choose>
				<tr bgcolor='<c:out value="${bgcolor }"/>'>
					<td style="width:30px;border-width:0;background-color: white"></td>
					<td><c:out value="${page.title }" /></td>
					<td>
						<c:if test="${page.id != srcPid }">
							<form action="${ctx }/page/order" method="post" >
								<input type="hidden" name="srcPid" value="${srcPid }" />
								<input type="hidden" name="targPid" value="${page.id }" />
								<input type="hidden" name="surveyId" value="${survey.id }" />
								<input type="radio" name="pos" value="0" checked="checked"/>之前
								<input type="radio" name="pos" value="1"/>之后
								<input type="submit" class="btn" value="确定" />
							</form>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
	</table>
</body>