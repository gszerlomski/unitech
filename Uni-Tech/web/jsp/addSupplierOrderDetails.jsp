<%@ include file="/jsp/common/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <%@ include file="/jsp/common/head.jsp" %>
  <script language="JavaScript" type="text/javascript">

    //<![CDATA[
    
        var fittingTypes = [
          <c:forEach var="fittingType" items="${orderModel.fittingDesc.fittingTypes}" varStatus="status">
            "<c:out value="${fittingType.fittingTypeName}"/>"
            <c:if test="${!status.last}">,</c:if>
          </c:forEach>
        ];
    
        var tubeDim = [
          <c:forEach var="tubeDim" items="${orderModel.fittingDesc.tubeDims}" varStatus="status">
            '<c:out value="${tubeDim.tubeDimName}" escapeXml="false"/>'
            <c:if test="${!status.last}">,</c:if>
          </c:forEach>
        ];
        
        var threadDim = [
          <c:forEach var="threadDim" items="${orderModel.fittingDesc.threadDims}" varStatus="status">
            '<c:out value="${threadDim.threadDimName}" escapeXml="false"/>'
            <c:if test="${!status.last}">,</c:if>
          </c:forEach>
        ];
        
        var adaptor = [
          <c:forEach var="adaptor" items="${orderModel.fittingDesc.adaptors}" varStatus="status">
            '<c:out value="${adaptor.adaptorName}" escapeXml="false"/>'
            <c:if test="${!status.last}">,</c:if>
          </c:forEach>
        ];
    
        $(function() {

        $( "input#fittingTypes" ).autocomplete({
          source: function( request, response ) {
                    var matches = $.map( fittingTypes, function(tag) {
                      if ( tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0 ) {
                        return tag;
                      }
                    });
                    response(matches);
          },
          minLength: 2,
          select: function(event, ui) { 
            $("#fittingTypes").val(ui.item.value);
            $("#fittingTypesForm").submit(); }
        });
        
        $( "input#tubeDim" ).autocomplete({
        source: tubeDim
        });
        
        $( "input#threadDim" ).autocomplete({
        source: threadDim
        });

        $( "input#adaptor" ).autocomplete({
        source: adaptor
        });

        $( "#priceWarn" ).hide();
        $( "#type" ).hide();
        
        $( "#orderCreationDate" ).datepicker({ dateFormat: 'dd/mm/yy' });
        
        $( "#orderDeliveryDate" ).datepicker({ dateFormat: 'dd/mm/yy' });
        
        var myDate = new Date();
        var prettyDate = myDate.getDate() + '/' + (myDate.getMonth()+1) + '/' + myDate.getFullYear();
        $("#orderCreationDate").val(prettyDate);

        });
      //]]>
  </script>
  <body>
    <div>
      <span class="title">UNI-TECH</span>
    </div>
    <div id="container">
      <div id="body">
        <div id="left">
          <jsp:include page="/jsp/common/menu.jsp">
            <jsp:param name="page" value="createSupplierOrder" />
          </jsp:include>
        </div>
        <div id="right">

          <div class="textbox">
            <span class="tab">Szczegóły zamówienia</span>
            <div class="text">

              <c:if test="${not empty orderModel.supplierOrderModel}">
                <form:form name="orderDetails" id="orderDetails" action="confirmSupplierOrder.htm" method="post"
                  modelAttribute="orderModel.supplierOrderModel">
                  <div id="orderModelDetails">
                    <p>
                      Data stworzenia zamówienia:
                      <form:input type="text" id="orderCreationDate" path="creationDateString" />
                    </p>

                    <p>
                      Przewidywana data dostarczenia:
                      <form:input type="text" id="orderDeliveryDate" path="estimatedDeliveryDateString" />
                    </p>
                    
                    <p>
                      Podaj numer zamówienia:
                      <form:input type="text" name="orderNumber" id="orderNumber" disabled="false" path="orderNumber" />
                    </p>

                    <p>
                      <a href="#" onclick="$('#orderDetails').submit()">Zatwierdź zamówienie</a>
                    </p>
                    <hr class="strong"/>
                  </div>
                </form:form>
              </c:if>

              <div class="section">

                <table>
                  <tr>
                    <th>Produkt</th>
                    <th>Ilosc</th>
                    <th>Cena</th>
                  </tr>
                  <c:forEach items="${orderModel.supplierOrderModel.lineItems}" var="product">
                    <tr>
                      <td>${product.product.formattedName}</td>
                      <td>${product.amount}</td>
                      <td>${product.totalPrice}</td>
                    </tr>
                  </c:forEach>
                  <tr>
                      <td colspan="3">Całkowita cena zamówienia</td>
                      <td>${orderModel.supplierOrderModel.totalPrice}</td>
                  </tr>
                </table>

                <a href="#">Edytuj zamówienie</a>

              </div>

              <div id="type" class="section">
                <form:form id="fittingTypesForm" name="fittingTypesForm" action="newProductList.htm" method="post"
                  modelAttribute="orderModel">
                  <span>Typ </span>
                  <input id="fittingTypes" name="fittingTypes" type="text" />
                </form:form>
              </div>
            </div>
          </div>
          <%@ include file="/jsp/common/copyright.jsp" %>
        </div>
      </div>
    </div>
  </body>
</html>
