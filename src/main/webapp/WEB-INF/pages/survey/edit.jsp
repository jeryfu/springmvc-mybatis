<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>编辑调查</title>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<table>
		<tr>
			<td class="tdHeader">编辑调查标题:</td>
		</tr>
		<tr>
			<td style="vertical-align: top;">
				<table>
					<tr>
						<td>
							<form action="${ctx }/survey" method="post">
								<input type="hidden" name="_method" value="PUT">
								<input type="hidden" name="id" value="${survey.id }" />
								<table>
									<tr>
										<td class="tdFormLabel">调查标题:</td>
										<td class="tdFormControl">
											<input type="text" name="title" class="text" value="${survey.title }" />
										</td>
									</tr>
									<tr>
										<td class="tdFormLabel">"下一步"提示文本:</td>
										<td class="tdFormControl">
											<input type="text" name="nextText" class="text" value="${survey.nextText }" />
										</td>
									</tr>
									<tr>
										<td class="tdFormLabel">"上一步"提示文本:</td>
										<td class="tdFormControl">
											<input type="text" name="preText" class="text" value="${survey.preText }" />
										</td>
									</tr>
									<tr>
										<td class="tdFormLabel">"完成"提示文本:</td>
										<td class="tdFormControl">
											<input type="text" name="doneText" class="text" value="${survey.doneText }" />
										</td>
									</tr>
									<tr>
										<td class="tdFormLabel">"退出"提示文本:</td>
										<td class="tdFormControl">
											<input type="text" name="exitText" class="text" value="${survey.exitText }" />
										</td>
									</tr>
									<tr>
										<td class="tdFormLabel"></td>
										<td class="tdFormControl">
											<input type="submit" class="btn" value="确定" />
										</td>
									</tr>
								</table>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table> 
</body>
</html>