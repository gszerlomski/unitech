
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
              <ul class="nav nav-pills">
                <li>
                  <a id="new_product" href="createCustomer.htm">Dodaj nowego klienta</a>
                </li>
              </ul>

              <c:set var="successMessages" value="${successMessages}" scope="request" />
              <c:set var="errorMessages" value="${errorMessages}" scope="request" />
              <c:set var="infoMessages" value="${infoMessages}" scope="request" />
              <jsp:include page="/jsp_new/common/message.jsp" />

              <c:if test="${not empty customer}">
                <!-- New customer box -->
                <legend id="new_product_title">Dodaj nowego klienta</legend>

                <div id="new_customer_box" class="row-fluid">
                  <div class="span12">
                    <div class="row-fluid">
                      <ul class="custom-content-box">
                        <li>
                          <legend class="tight muted">Podaj dane klienta</legend>

                          <form:form id="newCustomerForm" name="newCustomerForm" action="createCustomer.htm"
                            method="post" modelAttribute="customer" class="form form-inline">

                            <div class="control-group span2">
                              <label class="control-label" for="customerName">Nazwa</label>
                              <div class="controls">
                                <form:input id="customerName" path="customerName" type="text"
                                  disabled="${customer.customerName.disabled}" class="input-medium" placeholder="np. Wagony" />
                              </div>
                            </div>

                            <div class="control-group span2">
                              <label class="control-label" for="customerStreet">Ulica</label>
                              <div class="controls">
                                <form:input id="customerStreet" path="customerStreet" type="text"
                                  disabled="${customer.customerStreet.disabled}" class="input-medium" placeholder="np. Krupnicza" />
                              </div>
                            </div>

                            <div class="control-group span2">
                              <label class="control-label" for="customerNumber">Numer</label>
                              <div class="controls">
                                <form:input id="customerHomeNr" path="customerHomeNr" type="text"
                                  disabled="${customer.customerHomeNr.disabled}" class="input-small" placeholder="np. 13/26" />
                              </div>
                            </div>

                            <div class="control-group span2">
                              <label class="control-label" for="customerCode">Kod pocztowy</label>
                              <div class="controls">
                                <form:input id="customerPostCode" path="customerPostCode" type="text"
                                  disabled="${customer.customerPostCode.disabled}" class="input-small" placeholder="np. 32-061" />
                              </div>
                            </div>

                            <div class="control-group span2">
                              <label class="control-label" for="customerCity">Miasto</label>
                              <div class="controls">
                                <form:input id="customerCity" path="customerCity" type="text"
                                  disabled="${customer.customerCity.disabled}" class="input-medium" placeholder="np. Świdnica" />
                              </div>
                            </div>

                          </form:form>
                          <form:form id="cancelCustomerForm" name="cancelCustomerForm" action="cancelCustomer.htm"
                            method="get" class="form form-inline"></form:form>

                          <p class="small-spacer">&nbsp;
                          </p>
                          <div class="form-actions">
                            <button type="submit" class="btn btn-primary" onclick="$('#newCustomerForm').submit();">Zapisz klienta</button>
                            <button type="button" class="btn" onclick="window.location.href = 'cancelCustomer.htm'">Anuluj</button>
                          </div>
                        </li>
                      </ul>
                    </div>
                    <p>&nbsp;
                    </p>
                  </div>
                </div>
              </c:if>

              <legend id="new_product_title">Wybierz klienta</legend>

              <table class="table table-condensed table-striped ">
                <thead>
                  <tr>
                    <th>Nazwa klienta</th>
                    <th>Adres</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach items="${customers.customersList}" var="customer" varStatus="i">
                    <tr>
                      <td>
                        <a href="#">${customer.customerName}</a>
                      </td>
                      <td>
                        <a href="#">${customer.customerStreet} ${customer.customerHomeNr}, ${customer.customerPostCode}
                          ${customer.customerCity}</a>
                      </td>
                      <td>
                        <td>
                          <a class="btn btn-mini" href="#" data-toggle="tooltip" title="Edytuj" onclick="">
                            <i class="icon-pencil"></i>
                          </a>
                        </td>
                      </td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>