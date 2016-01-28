<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/common/meta.jsp"%>
	<title>用户注册</title>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<form:form action="${ctx }/sign/register" method="POST" modelAttribute="user">
		<table>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td colspan="2" class="tdHeader">注册新用户</td>
			</tr>
			<tr>
				<td colspan="2" class="tdWhiteLine"></td>
			</tr>
			<tr>
				<td class="tdFormLabel"></td>
				<td class="tdFormControl">
					<font class="fonterror">${regError }</font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">邮箱:</td>
				<td class="tdFormControl">
					<input type="text" name="email" class="text" value="${user.email}">
					<font class="fonterror"><form:errors path="email" ></form:errors></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">密  &nbsp;&nbsp;码:</td>
				<td class="tdFormControl">
					<input type="password" name="password" class="text" value="${user.password }" />
					<font class="fonterror"><form:errors path="password" /></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">确认密码:</td>
				<td class="tdFormControl">
					<input type="password" name="rePwd" class="text" />
					<font class="fonterror">
						<span>${rePwdError }</span>
					</font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel">昵称:</td>
				<td class="tdFormControl">
					<input name="nickname" class="text" value="${user.nickname }"/>
					<font class="fonterror"><form:errors path="nickname" /></font>
				</td>
			</tr>
			<tr>
				<td class="tdFormLabel"></td>
				<td class="tdFormControl"><input type="submit" class="btn" value="注册" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>