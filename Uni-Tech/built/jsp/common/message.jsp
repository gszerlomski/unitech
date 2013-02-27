
<%@ include file="/jsp/common/include.jsp" %>
<c:choose>
  
  <c:when test="${style == 'success'}">
    <div class=" section success-text">
      <div class="ui-state-success ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
        <p>
          <span class="ui-icon ui-icon-check" style="float: left; margin-right: .3em;"></span>
          <strong>Sukces!</strong>
          ${messageVar.messageText}
          <c:forEach items="${messageVar.links}" var="link">
            <a class="message_link" href="${link.linkTarget}">${link.linkText}</a>
          </c:forEach>
        </p>
      </div>
    </div>
  </c:when>
  
  <c:when test="${style == 'info'}">
    <div class=" section warn-text">
      <div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
        <p>
          <span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
          <strong>Uwaga!</strong>
          ${messageVar.messageText}
          <c:forEach items="${messageVar.links}" var="link">
            <a class="message_link" href="${link.linkTarget}">${link.linkText}</a>
          </c:forEach>
        </p>
      </div>
    </div>
  </c:when>
  
  <c:otherwise>
    <div class=" section error-text">
      <div class="ui-state-error ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
        <p>
          <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
          <strong>Błąd!</strong>
          ${messageVar.messageText}
          <c:forEach items="${messageVar.links}" var="link">
            <a class="message_link" href="${link.linkTarget}">${link.linkText}</a>
          </c:forEach>
        </p>
      </div>
    </div>
  </c:otherwise>
  
</c:choose>




