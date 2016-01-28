<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>登陆页面</title>
	<%@ include file="/common/meta.jsp" %>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<c:if test="${!empty sessionScope.survey_user }">
		<div class="divNavigatorOuterFrame">
			<div class="divNavigatorInnerFrame" style="text-align: right;">
				欢迎${sessionScope.survey_user.nickname }&nbsp;&nbsp;
			</div>
		</div>
		<div class="divWhiteLine"></div>
	</c:if>
	<form action="${ctx }/sign/in" method="post">
	<table>
		<tr>
			<td colspan="2" class="tdWhiteLine"></td>
		</tr>
		<tr>
			<td class="tdHeader" colspan="2">用户登陆</td>
		</tr>
		<tr>
			<td colspan="2" class="tdWhiteLine"></td>
		</tr>
		<tr>
			<td class="tdFormLabel" width="40%">E-mail:</td>
			<td class="tdFormControl">
				<input type="text" name="email" class="text" value="fuqiang_it@qq.com">
				<font class="fonterror"><br><form:errors path="email"></form:errors></font>
			</td>
		</tr>
		<tr>
			<td class="tdFormLabel">密码:</td>
			<td class="tdFormControl">
				<input type="password" name="password" class="text" value="123456">
				<font class="fonterror"></font>
			</td>
		</tr>
		<tr>
			<td class="tdFormLabel"></td>
			<td class="tdFormControl"><input type="submit" value="登录" class="btn"></td>
		</tr>
		<tr>
			<td class="tdFormLabel"></td>
			<td class="tdFormControl">
				<font class="fonterror">${errors }</font>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>