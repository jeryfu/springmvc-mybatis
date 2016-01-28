<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>添加logo</title>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<table>
		<tr>
			<td class="tdHeader">增加Logo:</td>
		</tr>
		<tr>
			<td style="vertical-align: top;">
				<table>
					<tr>
						<td>
							<form action="${ctx }/survey/logo" method="post" enctype="multipart/form-data">
							<input type="hidden" name="surveyId" value="${surveyId }"/>
							<table>
								<tr>
									<td class="tdFormLabel">选择Logo:</td>
									<td class="tdFormControl">
										<input type="file" name="file" />
									</td>
								</tr>
								<tr>
									<td class="tdFormLabel"></td>
									<td class="tdFormControl">
										<input type="submit" class="btn" value="上传" />
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