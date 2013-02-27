
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
            <jsp:param name="page" value="ordersNotCompleted" />
          </jsp:include>
        </div>
        <div id="right">
          <div class="textbox">
            <span class="tab">Zamówienia niezrealizowane</span>

            <div class="text">
              <form:form id="notRealizedOrders" name="notRealizedOrders" action="notRealizedOrders.htm"
                method="post" modelAttribute="orderList">
                <div id="non_realized" class="section">
                  <c:forEach items="${orders}" var="order" varStatus="i">
                    <h3>
                      <div class="order-list-table">
                        <div class="table-row">
                          <div class="table-cell">${order.orderId}</div>
                          <div style="table-cell">${order.supplierName}</div>
                          <div class="right-table-cell">${order.creationDateString}</div>
                        </div>
                      </div>
                    </h3>
                    <div>
                      Realizacja całości zamówienia:
                      <input type="checkbox" path="orders[${i.index}].completed" />
                      <hr />
                      <div class="product-list-table">
                        <div class="product-list-table-row">
                          <div class="product-list-table-cell-left">Produkt</div>
                          <div class="product-list-table-cel">Ilość</div>
                          <div class="product-list-table-cel">Cena</div>
                          <div class="product-list-table-cel">Dostarczone</div>
                        </div>
                        <c:forEach items="${orders[${i.index}].lineItems}" var="product" varStatus="j">
                          <div class="product-list-table-row">
                            <div class="product-list-table-cell-left">${product.product.formattedName}</div>
                            <div class="product-list-table-cel">${product.amount}</div>
                            <div class="product-list-table-cel">${product.totalPrice}</div>
                            <div class="product-list-table-cel">
                              <input type="checkbox" path="orders[${i.index}].lineItems[${j.index}].delivered" />
                            </div>
                          </div>
                        </c:forEach>
                      </div>
                    </div>
                  </c:forEach>
                </div>
              </form:form>
            </div>

            <div style="margin-top:20px;margin-left:10px;margin-bottom:5px;" class="section">
              Zmienione
              <hr style="width:200px;margin-left:0px;text-align:left;margin-top:0px;margin-bottom:0px;" />
            </div>

            <div class="text">
              <form:form id="realizedOrders" name="realizedOrders" action="realizedOrders.htm" method="post"
                modelAttribute="orderList">
                <div id="realized" class="section">
                  <c:forEach items="${orderList.ordersChanged}" var="order" varStatus="i">
                    <h3>
                      <div class="order-list-table">
                        <div class="table-row">
                          <div class="table-cell">${order.orderId}</div>
                          <div style="table-cell">${order.supplierName}</div>
                          <div class="right-table-cell">${order.creationDateString}</div>
                        </div>
                      </div>
                    </h3>
                    <div>
                      Realizacja całości zamówienia:
                      <input type="checkbox" path="ordersChanged[${i.index}].completed" />
                      <hr />
                      <div class="product-list-table">
                        <div class="product-list-table-row">
                          <div class="product-list-table-cell-left">Produkt</div>
                          <div class="product-list-table-cel">Ilość</div>
                          <div class="product-list-table-cel">Cena</div>
                          <div class="product-list-table-cel">Dostarczone</div>
                        </div>
                        <c:forEach items="${orders[${i.index}].lineItems}" var="product" varStatus="j">
                          <div class="product-list-table-row">
                            <div class="product-list-table-cell-left">${product.product.formattedName}</div>
                            <div class="product-list-table-cel">${product.amount}</div>
                            <div class="product-list-table-cel">${product.totalPrice}</div>
                            <div class="product-list-table-cel">
                              <input type="checkbox" path="orders[${i.index}].lineItems[${j.index}].delivered" />
                            </div>
                          </div>
                        </c:forEach>
                      </div>
                    </div>
                  </c:forEach>
                </div>
              </form:form>
            </div>
            <div class="section">
              <a href="#">Zapisz</a>
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
