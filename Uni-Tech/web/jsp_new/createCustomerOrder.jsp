
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
                <li>
                  <a id="new_product" href="deleteCustomer.htm">Usuń istniejacego klienta</a>
                </li>
              </ul>

              <c:set var="successMessages" value="${successMessages}" scope="request" />
              <c:set var="errorMessages" value="${errorMessages}" scope="request" />
              <c:set var="infoMessages" value="${infoMessages}" scope="request" />
              <jsp:include page="/jsp_new/common/message.jsp" />


              <!-- New customer box -->
              <legend id="new_product_title">Dodaj nowego klienta</legend>

              <div id="new_customer_box" class="row-fluid">
                <div class="span12">
                  <div class="row-fluid">
                    <ul class="custom-content-box">
                      <li>
                        <legend class="tight muted">Podaj dane klienta</legend>

                        <form:form id="newCustomerForm" name="newCustomerForm" action="newCustomerForm.htm"
                          method="post" class="form form-inline">

                          <div class="control-group span2">
                            <label class="control-label" for="customerName">Nazwa</label>
                            <div class="controls">
                              <input type="text" id="customerName" placeholder="Wagony Świdnica" class="input-medium">
                            </div>
                          </div>

                          <div class="control-group span2">
                            <label class="control-label" for="customerStreet">Ulica</label>
                            <div class="controls">
                              <input type="text" id="customerStreet" placeholder="Krupnicza" class="input-medium">
                            </div>
                          </div>

                          <div class="control-group span2">
                            <label class="control-label" for="customerNumber">Numer</label>
                            <div class="controls">
                              <input type="text" id="customerNumber" placeholder="13/26" class="input-small">
                            </div>
                          </div>

                          <div class="control-group span2">
                            <label class="control-label" for="customerCode">Kod pocztowy</label>
                            <div class="controls">
                              <input type="text" id="customerCode" placeholder="32-061" class="input-small">
                            </div>
                          </div>

                          <div class="control-group span2">
                            <label class="control-label" for="customerCity">Miasto</label>
                            <div class="controls">
                              <input type="text" id="customerCity" placeholder="Świdnica" class="input-medium">
                            </div>
                          </div>

                        </form:form>
                        <p class="small-spacer">&nbsp;
                        </p>
                        <div class="form-actions">
                          <button type="submit" class="btn btn-primary">Zapisz klienta</button>
                          <button type="button" class="btn">Anuluj</button>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <p>&nbsp;
                  </p>
                </div>
              </div>



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
                  <tr>
                    <td>
                      <a href="#">Wagony Świdnica</a>
                    </td>
                    <td>
                      <a href="#">ul Portowa 34, 24-715 Świdnica</a>
                    </td>
                    <td>
                      <td>
                        <a class="btn btn-mini" href="#" data-toggle="tooltip" title="Edytuj" onclick="">
                          <i class="icon-pencil"></i>
                        </a>
                      </td>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <a href="#">Wagony Świdnica</a>
                    </td>
                    <td>
                      <a href="#">ul Portowa 34, 24-715 Świdnica</a>
                    </td>
                    <td>
                      <td>
                        <a class="btn btn-mini" href="#" data-toggle="tooltip" title="Edytuj" onclick="">
                          <i class="icon-pencil"></i>
                        </a>
                      </td>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <a href="#">Wagony Świdnica</a>
                    </td>
                    <td>
                      <a href="#">ul Portowa 34, 24-715 Świdnica</a>
                    </td>
                    <td>
                      <td>
                        <a class="btn btn-mini" href="#" data-toggle="tooltip" title="Edytuj" onclick="">
                          <i class="icon-pencil"></i>
                        </a>
                      </td>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <a href="#">Wagony Świdnica</a>
                    </td>
                    <td>
                      <a href="#">ul Portowa 34, 24-715 Świdnica</a>
                    </td>
                    <td>
                      <td>
                        <a class="btn btn-mini" href="#" data-toggle="tooltip" title="Edytuj" onclick="">
                          <i class="icon-pencil"></i>
                        </a>
                      </td>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <a href="#">Wagony Świdnica</a>
                    </td>
                    <td>
                      <a href="#">ul Portowa 34, 24-715 Świdnica</a>
                    </td>
                    <td>
                      <td>
                        <a class="btn btn-mini" href="#" data-toggle="tooltip" title="Edytuj" onclick="">
                          <i class="icon-pencil"></i>
                        </a>
                      </td>
                    </td>
                  </tr>
                </tbody>
              </table>

            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>