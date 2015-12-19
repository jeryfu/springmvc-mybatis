<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="clear"></div>
<c:if test="${page.totalPages > 1 }">
	<div class="dia-stand-f">
		<div class="left">
	    	<span>每页 ${page.pageSize } 条，总数 ${page.totalCount } 条</span>
	    </div>
	    <div class="right">
	        <c:if test="${page.pageNo>1}"><a href="javascript:nextPage(${page.pageNo-1})">&laquo;上一页</a></c:if>
	        <c:forEach begin="${page.pageNo-5<1?1:(page.pageNo-5)}" end="${page.totalPages>(page.pageNo+4)?(page.pageNo+4):page.totalPages}" var="numi">
	       		<c:if test="${page.pageNo==numi}"><a href="javascript:nextPage(${numi})" class="zszw_active">${numi}</a></c:if>
	       		<c:if test="${page.pageNo!=numi}"><a href="javascript:nextPage(${numi})">${numi}</a></c:if>
	       	</c:forEach>
	      	<c:if test="${page.totalPages>page.pageNo}">
	      		<a href="javascript:nextPage(${page.pageNo+1})">下一页&raquo;</a>
	      	</c:if> 
	        <span>第${page.pageNo}/${page.totalPages }页</span>
	    </div>
	</div>
</c:if>