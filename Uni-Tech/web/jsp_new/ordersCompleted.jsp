
<%@ include file="/jsp_new/common/include.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="/jsp_new/common/head.jsp" %>

    <script language="JavaScript" type="text/javascript">

      //<![CDATA[
      $(function() {
      
      });
      //]]>
    </script>
  </head>
  <body>
    <%@ include file="/jsp_new/common/header.jsp" %>

    <div class="container-fluid">
      <div class="row-fluid">

        <jsp:include page="/jsp_new/common/menu.jsp">
          <jsp:param name="page" value="ordersCompleted" />
        </jsp:include>

        <div class="span9 bs-main-area">
          <div class="row-fluid">
            <div class="span12">
              <ul class="nav nav-pills">
                <li>
                  <a href="#" onclick="$('#realizedOrders').submit()">Zapisz wszystkie</a>
                </li>
                <li>
                  <a href="ordersCompleted.htm">Porzuć</a>
                </li>
              </ul>

              <!-- div class="alert alert-success"> <button type="button" class="close" data-dismiss="alert">&times; </button> 
                <strong>Success!</strong> Best check yo self, you're not looking too good. </div -->

              <legend id="new_product_title">Zamówienia niezrealizowane</legend>
              <form:form id="realizedOrders" name="realizedOrders" action="realizedOrders.htm"
                method="post" modelAttribute="orderList">
                <div class="accordion" id="ordersCompleted">
                  <c:forEach items="${orderList.orders}" var="order" varStatus="i">
                    <div class="accordion-group">
                      <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#ordersCompleted" href="#collapse${i.index}">
                          <div class="row-fluid">
                            <div class="span2">
                              <strong>${order.orderNumber}</strong>
                            </div>
                            <div class="span2 offset1">
                              <strong>${order.supplierName}</strong>
                            </div>
                            <div class="span1 offset1">
                              <strong>${order.creationDateString}</strong>
                            </div>
                          </div>
                        </a>
                      </div>
                      <div id="collapse${i.index}" class="accordion-body collapse">
                        <div class="accordion-inner accordion-inner-outline">
                          <ul class="nav nav-tabs">
                            <li class="active">
                              <a href="#completed${i.index}" data-toggle="tab">Całosć zamówienia</a>
                            </li>
                            <li>
                              <a href="#details${i.index}" data-toggle="tab">Szczegóły zamówienia</a>
                            </li>
                          </ul>

                          <div class="tab-content">
                            <div class="tab-pane active" id="completed${i.index}">
                              <table class="table table-condensed table-striped ">
                                <thead>
                                  <tr>
                                    <th>Produkt</th>
                                    <th>Ilość</th>
                                    <th>Cena</th>
                                    <th>Dostarczone</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <c:forEach items="${orderList.orders[i.index].completedLineItems}" var="product"
                                    varStatus="j">
                                    <tr>
                                      <td>${product.product.formattedName}</td>
                                      <td>${product.amount}</td>
                                      <td>${product.totalPrice}</td>
                                      <td>
                                        <form:checkbox path="orders['${i.index}'].completedLineItems['${j.index}'].delivered" />
                                      </td>
                                    </tr>
                                  </c:forEach>
                                  <c:if test="${not empty orderList.orders[i.index].completedLineItems}">
                                    <tr>
                                      <td colspan="2">
                                        <div class="text-right">
                                          <h4 class="text-right">Suma</h4>
                                        </div>
                                      </td>
                                      <td>
                                        <h4>${orderList.orders[i.index].completedLineItemsTotalPrice}</h4>
                                      </td>
                                      <td>
                                        <a >Zaznacz wszystkie</a>
                                      </td>
                                    </tr>
                                  </c:if>
                                </tbody>
                              </table>
                              <button type="submit" onclick="$('#notRealizedOrders').submit()" class="btn">Zapisz wszystkie</button>
                            </div>
                            <div class="tab-pane" id="details${i.index}">
                              <c:if test="${not empty orderList.orders[i.index].orderFilesList}"> 
                                Pliki dołączone do zamówienia:
                                 <ul>
                                   <c:forEach items="${orderList.orders[i.index].orderFilesList}" var="file">
                                      <li>${file} <a class="btn btn-mini" href="#" data-toggle="tooltip" title="Usuń"
                        onclick="$('#itemAction').val('Delete'); $('#itemRemoved').val('${orderList[i.index].orderId}'); $('#itemName').val('${file}'); $('#removeFile').submit();">
                        <i class="icon-remove"></i>
                      </a></li>
                                   </c:forEach>
                                 </ul>                              
                              </c:if>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </c:forEach>
                </div>
              </form:form>
               <form:form name="removeFile" id="removeFile" action="removeCompletedOrderFile.htm" method="post">
                <input type="hidden" id="itemRemoved" name="itemRemoved" value="" />
                <input type="hidden" id="itemAction" name="itemAction" value="" />
                <input type="hidden" id="itemName" name="itemName" value="" />
              </form:form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>