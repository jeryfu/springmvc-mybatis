<input type="hidden" name="surveyId" value="${surveyId }" />
<input type="hidden" name="pageId" value="${question.pageId }" />
<input type="hidden" name="id" value="${question.id }" />
<c:if test="${!empty question.id }">
	<input type="hidden" name="_method" value="PUT">
</c:if>
<input type="hidden" name="questionType" value="${question.questionType }" />