
<%@ include file="/jsp/common/include.jsp" %>


<c:if test="${not empty successMessages}">
  <c:forEach items="${successMessages.messages}" var="message">
    <div class="alert alert-success">
      <button type="button" class="close" data-dismiss="alert">&times;
      </button>
      <strong>Sukces!</strong>
      ${message.messageText}
      <c:forEach items="${message.links}" var="link">
        <a class="message_link" href="${link.linkTarget}">${link.linkText}</a>
      </c:forEach>
    </div>
  </c:forEach>
</c:if>

<c:if test="${not empty errorMessages}">
  <c:forEach items="${errorMessages.messages}" var="message">
    <div class="alert alert-error">
      <button type="button" class="close" data-dismiss="alert">&times;
      </button>
      <strong>Błąd!</strong>
      ${message.messageText}
      <c:forEach items="${message.links}" var="link">
        <a class="message_link" href="${link.linkTarget}">${link.linkText}</a>
      </c:forEach>
    </div>
  </c:forEach>
</c:if>

<c:if test="${not empty infoMessages}">
  <c:forEach items="${infoMessages.messages}" var="message">
    <div class="alert">
      <button type="button" class="close" data-dismiss="alert">&times;
      </button>
      <strong>Uwaga!</strong>
      ${message.messageText}
      <c:forEach items="${message.links}" var="link">
        <a class="message_link" href="${link.linkTarget}">${link.linkText}</a>
      </c:forEach>
    </div>
  </c:forEach>
</c:if>