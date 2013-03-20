
<%@ include file="/jsp/common/include.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <%@ include file="/jsp/common/head.jsp" %>
  <script language="JavaScript" type="text/javascript">

    //<![CDATA[
      $(function() {
        $( "#realized" ).accordion({ header: "h3", collapsible: true, active: false, heightStyle:
        "content" });
        $( "#non_realized" ).accordion({ header: "h3", collapsible: true, active: false, heightStyle:
        "content" });
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
            <jsp:param name="page" value="ordersCompleted" />
          </jsp:include>
        </div>
        <div id="right">
          <div class="textbox">
            <span class="tab">Zamówienia zrealizowane</span>

            <div class="text">
              <form:form id="realizedOrders" name="realizedOrders" action="realizedOrders.htm"
                method="post" modelAttribute="orderList">
                <div id="realized" class="section">
                  <c:forEach items="${orderList.orders}" var="order" varStatus="i">
                    <h3>
                      <div class="order-list-table">
                        <div class="table-row">
                          <div class="table-cell">${order.orderId}</div>
                          <div style="table-cell">${order.supplierName}</div>
                          <div class="table-cell">${order.orderNumber}</div>
                          <div class="right-table-cell">${order.creationDateString}</div>
                        </div>
                      </div>
                    </h3>
                    <div>
                      Realizacja całości zamówienia:
                      <form:checkbox path="orders['${i.index}'].completed" />
                      <hr />
                      <div class="product-list-table">
                        <div class="product-list-table-header-row">
                          <div class="product-list-table-cell-left">Produkt</div>
                          <div class="product-list-table-cell">Ilość</div>
                          <div class="product-list-table-cell">Cena</div>
                          <div class="product-list-table-cell">Dostarczone</div>
                        </div>
                        <c:forEach items="${orderList.orders[i.index].lineItems}" var="product" varStatus="j">
                          <div class="product-list-table-row">
                            <div class="product-list-table-cell-left">${product.product.formattedName}</div>
                            <div class="product-list-table-cell">${product.amount}</div>
                            <div class="product-list-table-cell">${product.totalPrice}</div>
                            <div class="product-list-table-cell">
                              <form:checkbox path="orders['${i.index}'].lineItems['${j.index}'].delivered" />
                            </div>
                          </div>
                        </c:forEach>
                      </div>
                    </div>
                  </c:forEach>
                </div>
                <div class="section">
                  <a href="#" onclick="$('#realizedOrders').submit()">Zapisz</a>
                </div>
              </form:form>
            </div>
          </div>
          <div id="copyright">
            Copyright � 2006 Greg.
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
