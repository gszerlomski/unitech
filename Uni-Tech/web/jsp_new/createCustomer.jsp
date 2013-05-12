
<%@ include file="/jsp_new/common/include.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="/jsp_new/common/head.jsp" %>
  </head>
  <body>
    <%@ include file="/jsp_new/common/header.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">

        <jsp:include page="/jsp_new/common/menu.jsp">
          <jsp:param name="page" value="createCustomerOrder" />
        </jsp:include>

        <div class="span9 bs-main-area">
          <div class="row-fluid">
            <div class="span12">
            
              <c:set var="successMessages" value="${successMessages}" scope="request" />
              <c:set var="errorMessages" value="${errorMessages}" scope="request" />
              <c:set var="infoMessages" value="${infoMessages}" scope="request" />
              <jsp:include page="/jsp_new/common/message.jsp" />

              <legend id="new_product_title">Wprowadź dane klienta</legend>

              

            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>