<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>编辑页面</title>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<table>
		<tr>
			<td class="tdHeader">增加/编辑页内容:</td>
		</tr>
		<tr>
			<td style="vertical-align: top;">
				<table>
					<tr>
						<td>
							<form action="${ctx }/page" method="post">
								<input type="hidden" value="${page.surveyId }" name="surveyId">
								<input type="hidden" value="${page.id }" name="id">
								<c:if test="${!empty page.id }">
									<input type="hidden" value="PUT" name="_method"/>
								</c:if>
								<table>
									<tr>
										<td class="tdFormLabel">页面标题:</td>
										<td class="tdFormControl">
											<input type="text" class="text" name="title" value="${page.title }" />
										</td>
									</tr>
									<tr>
										<td class="tdFormLabel">页面描述:</td>
										<td class="tdFormControl">
											<input type="text" class="text" name="description" value="${page.description }" />
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