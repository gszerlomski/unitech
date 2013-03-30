
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
          <jsp:param name="page" value="ordersNotCompleted" />
        </jsp:include>

        <div class="span9 bs-main-area">
          <div class="row-fluid">
            <div class="span12">
              <ul class="nav nav-pills">
                <li>
                  <a id="new_product" href="#">Zapisz</a>
                </li>
                <li>
                  <a id="new_product" href="#">Porzuć</a>
                </li>
              </ul>

              <!-- div class="alert alert-success"> <button type="button" class="close" data-dismiss="alert">&times; </button> 
                <strong>Success!</strong> Best check yo self, you're not looking too good. </div -->

              <legend id="new_product_title">Zamówienia niezrealizowane</legend>
              <form:form id="notRealizedOrders" name="notRealizedOrders" action="notRealizedOrders.htm"
                method="post" modelAttribute="orderList">
                <div class="accordion" id="ordersNotCompleted">
                  <c:forEach items="${orderList.orders}" var="order" varStatus="i">
                    <div class="accordion-group">
                      <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#ordersNotCompleted" href="#collapse${i.index}">
                          <div class="row-fluid">
                            <div class="span1">
                              <strong>${order.orderNumber}</strong>
                            </div>
                            <div class="span2 offset2">
                              <strong>${order.supplierName}</strong>
                            </div>
                            <div class="span1 offset6">
                              <strong>${order.creationDateString}</strong>
                            </div>
                          </div>
                        </a>
                      </div>
                      <div id="collapse${i.index}" class="accordion-body collapse">
                        <div class="accordion-inner accordion-inner-outline">
                          <ul class="nav nav-tabs">
                            <li class="active">
                              <a href="#nonCompleted${i.index}" data-toggle="tab">Pozycje niezrealizowane</a>
                            </li>
                            <li>
                              <a href="#completed${i.index}" data-toggle="tab">Pozycje zrealizowane</a>
                            </li>
                            <li>
                              <a href="#wholeOrder${i.index}" data-toggle="tab">Całość zamówienia</a>
                            </li>
                            <li>
                              <a href="#details${i.index}" data-toggle="tab">Szczegóły zamówienia</a>
                            </li>
                          </ul>

                          <div class="tab-content">
                            <div class="tab-pane active" id="nonCompleted${i.index}">
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
                                  <c:forEach items="${orderList.orders[i.index].lineItems}" var="product"
                                    varStatus="j">
                                    <tr>
                                      <td>${product.product.formattedName}</td>
                                      <td>${product.amount}</td>
                                      <td>${product.totalPrice}</td>
                                      <td>
                                        <form:checkbox path="orders['${i.index}'].lineItems['${j.index}'].delivered" />
                                      </td>
                                    </tr>
                                  </c:forEach>
                                  <tr>
                                    <td colspan="2">
                                      <div class="text-right">
                                        <h4 class="text-right">Suma</h4>
                                      </div>
                                    </td>
                                    <td>
                                      <h4>15840</h4>
                                    </td>
                                    <td>
                                      <a href="#">Zaznacz wszystkie</a>
                                    </td>
                                  </tr>
                                </tbody>
                              </table>

                            </div>
                            <div class="tab-pane" id="completed${i.index}">
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
                                  <c:forEach items="${orderList.orders[i.index].lineItems}" var="product"
                                    varStatus="j">
                                    <tr>
                                      <td>${product.product.formattedName}</td>
                                      <td>${product.amount}</td>
                                      <td>${product.totalPrice}</td>
                                      <td>
                                        <form:checkbox path="orders['${i.index}'].lineItems['${j.index}'].delivered" />
                                      </td>
                                    </tr>
                                  </c:forEach>
                                  <tr>
                                    <td colspan="2">
                                      <div class="text-right">
                                        <h4 class="text-right">Suma</h4>
                                      </div>
                                    </td>
                                    <td>
                                      <h4>15840</h4>
                                    </td>
                                    <td>
                                      <a href="#">Zaznacz wszystkie</a>
                                    </td>
                                  </tr>
                                </tbody>
                              </table>

                            </div>
                            <div class="tab-pane" id="wholeOrder${i.index}">
                              <table class="table table-condensed table-striped ">
                                <thead>
                                  <tr>
                                    <th>Produkt</th>
                                    <th>Ilość</th>
                                    <th>Cena</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <c:forEach items="${orderList.orders[i.index].lineItems}" var="product"
                                    varStatus="j">
                                    <tr>
                                      <td>${product.product.formattedName}</td>
                                      <td>${product.amount}</td>
                                      <td>${product.totalPrice}</td>
                                    </tr>
                                  </c:forEach>
                                  <tr>
                                    <td colspan="2">
                                      <div class="text-right">
                                        <h4 class="text-right">Suma</h4>
                                      </div>
                                    </td>
                                    <td>
                                      <h4>${orderList.orders[i.index].totalPrice}</h4>
                                    </td>
                                  </tr>
                                </tbody>
                              </table>
                            </div>
                            <div class="tab-pane" id="details${i.index}">Nooo, tu pewnie coś trzeba bedzie wyświetlić. Może daty?</div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </c:forEach>
                </div>
              </form:form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>