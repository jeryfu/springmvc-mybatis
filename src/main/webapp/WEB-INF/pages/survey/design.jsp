<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>设计调查</title>
	<%@ include file="/common/meta.jsp" %>
	<script type="text/javascript">
		$(function(){
			/* 删除问题 */
			del(".delete.question");
			
			/* 删除页面 */
			del(".delete.page");
		})
		
		function del(parameter){
			$(parameter).click(function(){
				var url = $(this).attr("name");
				$("#_method").val("DELETE");
				$("form").attr("action", url).submit();
				return false;
			});
		}
	</script>
</head>
<body>
	<%@ include file="/common/header.jsp" %>
	<form action="" method="post">
		<input type="hidden" name="surveyId" value="${survey.id }"/>
		<input type="hidden" name="_method" value="" id="_method"/>
	</form>
	<table>
		<tr>
			<td colspan="2" class="tdWhiteLine"></td>
		</tr>
		<tr>
			<td colspan="2" class="tdHeader">设计调查</td>
		</tr>
		<tr>
			<td colspan="2" class="tdWhiteLine"></td>
		</tr>
		<tr>
			<td class="tdSHeaderL">
				<c:if test="${!empty survey.logoUrl }">
					<img alt="not found" src="http://localhost:8290/${survey.logoUrl }" height="25px" width="50px">
				</c:if>
				<c:out value="${survey.title }" /> 
			</td>
			<td class="tdSHeaderR">
				<a href="${ctx }/survey/logo/${survey.id}">增加Logo</a>&nbsp;
				<a href="${ctx }/survey/edit/${survey.id}">编辑调查</a>&nbsp;
				<a href="${ctx }/page/edit/${survey.id}" >增加页</a>&nbsp;
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: left;vertical-align: top;">
				<table>
					<tr>
						<td width="30px"></td>
						<td width="*">
							<table>
								<c:forEach items="${survey.pages }" var="page">
								<tr>
									<td>
										<table>
											<tr>
												<!-- 页面标题 -->
												<td class="tdPHeaderL">${page.title }</td>
												<td class="tdPHeaderR">
													<a href="${ctx }/page/edit/${survey.id }?pageId=${page.id }" >编辑页标题</a>&nbsp;
													<a href="${ctx }/page/order/${page.id}">移动/复制页</a>&nbsp;
													<a href="${ctx }/question/type?surveyId=${survey.id}&pageId=${page.id}">增加问题</a>&nbsp;
													<a href="javascript:void()" name="${ctx }/page/${page.id}" class="delete page">删除页</a>&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table>
											<tr>
												<td width="30px"></td>
												<td width="*">
													<table>
														<tr>
															<td>
																<table>
																	<!-- 迭代问题集合 -->
																	<c:forEach items="${page.questions }" var="question">
																	<tr>
																		<!-- 问题题干 -->
																		<td class="tdQHeaderL">${question.title }</td>
																		<td class="tdQHeaderR">
																			<a href="${ctx }/question/edit?surveyId=${survey.id}&id=${question.id}">编辑问题</a>&nbsp;
																			<a href="javascript:void()" class="delete question" name="${ctx }/question/${question.id }">删除问题</a>&nbsp;
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" style="text-align: left;color: black;background-color: white">
																			<!-- 定义变量,设置第一大类的题型 -->
																			<!-- 判断当前题型是否属于第一大类(0,1,2,3) -->
																			<c:set var="qt" scope="request" value="${question.questionType }"></c:set>
																			<c:choose>
																				<c:when test="${qt < 4 }">
																					<c:forEach items="${question.optionArr }" var="option">
																						<input type="${qt < 2 ? 'radio' : 'checkbox'}" /><c:out value="${option }"></c:out> 
																						<c:if test="${qt==1 || qt==3 }"><br></c:if>
																					</c:forEach>
																					<c:if test="${question.other }">
																						<input type="${qt < 2 ? 'radio' : 'checkbox'}" />其他
																						<c:choose>
																							<c:when test="${question.otherStyle == 1 }">
																								<input type="text" />
																							</c:when>
																							<c:when test="${question.otherStyle == 2 }">
																								<select>
																									<c:forEach items="${question.otherSelectOptionArr }" var="oso">
																										<option><c:out value="${oso }"></c:out></option>
																									</c:forEach>
																								</select>
																							</c:when>
																						</c:choose>
																					</c:if>
																				</c:when>
																				<c:when test="${qt == 4 }">
																					<select>
																						<c:forEach items="${question.optionArr }" var="option">
																							<option><c:out value="${option }"></c:out></option>
																						</c:forEach>
																					</select>
																				</c:when>
																				<c:when test="${qt == 5 }">
																					<input type="text" />
																				</c:when>
																				<c:otherwise>
																					<table>
																						<!-- 列头 -->
																						<tr>
																							<td> </td>
																							<c:forEach items="${question.matrixColTitleArr }" var="coltitle">
																								<td><c:out value="${coltitle }"></c:out></td>
																							</c:forEach>
																						</tr>
																						<!-- 输出n多行 -->
																						<c:forEach items="${question.matrixRowTitleArr }" var="rowtitle">
																							<tr>
																								<td><c:out value="${rowtitle }"></c:out></td>
																								<!-- 套打控件 -->
																								<c:forEach items="${question.matrixColTitleArr }">
																									<td>
																										<!-- radio -->
																										<c:if test="${qt == 6 }"><input type="radio"></c:if>
																										<c:if test="${qt == 7 }"><input type="checkbox"></c:if>
																										<c:if test="${qt == 8 }">
																											<select>
																												<c:forEach items="${question.matrixSelectOptionArr }" var="mso">
																													<option><c:out value="${mso }"></c:out></option>
																												</c:forEach>
																											</select>
																										</c:if>
																									</td>
																								</c:forEach>
																							</tr>
																						</c:forEach>
																					</table>
																				</c:otherwise>
																			</c:choose>
																		</td>
																	</tr>
																	</c:forEach>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>	
								</c:forEach>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>