
<%@ include file="/jsp/common/include.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <%@ include file="/jsp/common/head.jsp" %>
  <body>
    <div>
      <span class="title">UNI-TECH</span>
    </div>
    <div id="container">
      <div id="body">
        <div id="left">
          <jsp:include page="/jsp/common/menu.jsp">
            <jsp:param name="page" value="home"/>
          </jsp:include>
        </div>
        <div id="right">

          <div class="textbox">
            <span class="tab">Witaj</span>
            <div class="text">
              Witaj w portalu CRM firmy UNI-TECH.
            </div>
          </div>
          <%@ include file="/jsp/common/copyright.jsp" %>
        </div>
      </div>
    </div>
  </body>
</html>