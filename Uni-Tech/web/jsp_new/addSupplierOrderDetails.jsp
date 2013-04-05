
<%@ include file="/jsp_new/common/include.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="/jsp_new/common/head.jsp" %>

    <script language="JavaScript" type="text/javascript">

      //<![CDATA[
      $(function() {
      
        var myDate = new Date();
        var prettyDate = myDate.getDate() + '/' + (myDate.getMonth()+1) + '/' + myDate.getFullYear();
        $("#creationDate").val(prettyDate);
        
        $("#creationDate").datepicker({
          format: 'dd/mm/yyyy'
        });
        
        $("#deliveryDate").datepicker({
          format: 'dd/mm/yyyy'
        });
      
        $("#creationDate").datepicker('setStartDate', prettyDate);

      });
      //]]>
    </script>
  </head>
  <body>
    <%@ include file="/jsp_new/common/header.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">

        <jsp:include page="/jsp_new/common/menu.jsp">
          <jsp:param name="page" value="createSupplierOrder" />
        </jsp:include>

        <div class="span9 bs-main-area">
          <div class="row-fluid">
            <div class="span12">
              <ul class="nav nav-pills">
                <li>
                  <a id="new_product" href="#" onclick="$('#orderDetails').submit()">Potwierdź zamówienie</a>
                </li>
                <li>
                  <a id="new_product" href="createSupplierOrder.htm">Cofnij</a>
                </li>
                <li>
                  <a onclick="$('#newOrder').submit();">Wyczyśc zamówienie</a>
                </li>
              </ul>
              <!-- div class="alert alert-success"> <button type="button" class="close" data-dismiss="alert">&times; </button> 
                <strong>Success!</strong> Best check yo self, you're not looking too good. </div -->

              <c:if test="${not empty orderModel.supplierOrderModel}">
                <legend id="new_product_title">Szczegóły zamówienia</legend>

                <div id="new_product_box" class="row-fluid">
                  <div class="span12">
                    <div class="row-fluid">
                      <ul class="custom-content-box">
                        <li>
                          <legend class="tight muted">Wybierz daty</legend>

                          <!-- div class="alert alert-error"> <button type="button" class="close" data-dismiss="alert">&times; 
                            </button> <strong>Error!</strong> Best check yo self, you're not looking too good. </div -->

                          <form:form name="orderDetails" id="orderDetails" action="confirmSupplierOrder.htm"
                            method="post" modelAttribute="orderModel.supplierOrderModel">
                            <div class="control-group span3">
                              <label class="control-label" for="fittingType">Numer zamówienia</label>
                              <div class="controls">
                                 <form:input type="text" name="orderNumber" path="orderNumber" />
                              </div>
                            </div>
                            <div class="control-group span3">
                              <label class="control-label" for="fittingType">Data stworzenia zamówienia</label>
                              <div class="controls">
                                <form:input path="creationDateString" id="creationDate" type="text" class="datepicker"/>
                              </div>
                            </div>
                            <div id="tubeDim_d" class="control-group span3">
                              <label class="control-label" for="tubeDim">Przewidywana data dostarczenia</label>
                              <div class="controls">
                                <form:input path="estimatedDeliveryDateString" id="deliveryDate" type="text" class="datepicker"/>
                              </div>
                            </div>
                          </form:form>
                          <p class="small-spacer">&nbsp;
                          </p>
                          <div class="form-actions">
                            <button type="submit" class="btn btn-primary"  onclick="$('#orderDetails').submit()">Potwierdź zamówienie</button>
                            <button type="button" class="btn" onclick="window.location.href = 'createSupplierOrder.htm'">Cofnij</button>
                            <button type="button" class="btn" onclick="$('#newOrder').submit();">Wyczyść zamówienie</button>
                          </div>
                        </li>
                      </ul>
                    </div>
                    <p>&nbsp;
                    </p>
                  </div>
                </div>
              </c:if>
            </div>
          </div>
          <p>&nbsp;
          </p>

          <legend>Bieżące zamówienie</legend>

          <form:form name="changeOrder" id="changeOrder" action="changeSupplierOrder.htm" method="post">
            <table class="table table-condensed table-striped ">
              <thead>
                <tr>
                  <th>Produkt</th>
                  <th>Ilość</th>
                  <th>Cena</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${orderModel.supplierOrderModel.lineItems}" var="product" varStatus="i">
                  <tr>
                    <td>${product.product.formattedName}</td>
                    <td>${product.amount}</td>
                    <td>${product.totalPrice}</td>
                    <td>
                      <a class="btn btn-mini" href="#" data-toggle="tooltip" title="Edytuj"
                        onclick="$('#itemAction').val('Edit'); $('#itemModified').val('${i.index}'); $('#changeOrder').submit();">
                        <i class="icon-pencil"></i>
                      </a>
                      <a class="btn btn-mini" href="#" data-toggle="tooltip" title="Usuń"
                        onclick="$('#itemAction').val('Delete'); $('#itemModified').val('${i.index}'); $('#changeOrder').submit();">
                        <i class="icon-remove"></i>
                      </a>
                    </td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
            <input type="hidden" id="itemModified" name="itemModified" value="" />
            <input type="hidden" id="itemAction" name="itemAction" value="" />
          </form:form>
        </div>
      </div>
    </div>
    <form:form id="newOrder" name="newOrderForm" action="newOrder.htm" method="post" modelAttribute="orderModel">
    </form:form>
  </body>
</html>