
<%@ include file="/jsp_new/common/include.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="/jsp_new/common/head.jsp" %>

    <script language="JavaScript" type="text/javascript">

      //<![CDATA[
      $(function() {

        $("#fittingType").select2({});
        $("#tubeDim").select2({});
        $("#threadDim").select2({});
        $("#adaptor").select2({});
        $("#oring").select2({});
        $("#grip").select2({});

        function checkFilled() {
          var ftype = $("#fittingType").select2("val");
          var tudim = $("#tubeDim").select2("val");
          var thdim = $("#threadDim").select2("val");
          var adap = $("#adaptor").select2("val");
          var ori = $("#oring").select2("val");
          var gri = $("#grip").select2("val");

          if(! ( /^\s*$/.test(ftype) || /^\s*$/.test(tudim) || /^\s*$/.test(thdim) || /^\s*$/.test(adap) || /^\s*$/.test(ori) || /^\s*$/.test(gri) ) ) {
            $('#fittingForm').submit();
          }
        }

        $("#fittingType").on("change", function(e) { 
          $("#chosenFittingType").val(e.val);
          $("#fittingTypesForm").submit();
         });
        
        $("#tubeDim").on("change", function() { checkFilled(); });
        $("#threadDim").on("change", function() { checkFilled(); });
        $("#adaptor").on("change", function() { checkFilled(); });
        $("#oring").on("change", function() { checkFilled(); });
        $("#grip").on("change", function() { checkFilled(); });

      });
      //]]>
    </script>
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
              <div class="span12">
                <div class="span9">
                  <form:form name="order" id="order" action="addCustomerOrderDetails.htm" method="post"
                    modelAttribute="product">
                    <ul class="nav nav-pills">
                      <li>
                        <a id="new_product" href="#" onclick="$('#addNewProduct').submit();">Nowy produkt</a>
                      </li>
                      <li>
                        <a id="new_product" href="#" onclick="$('#order').submit()">Stwórz zamówienie</a>
                      </li>
                      <li>
                        <a id="new_product_clear" href="#" onclick="$('#newOrder').submit();">Nowe zamówienie</a>
                      </li>
                    </ul>
                  </form:form>
                </div>
                <div class="span3">
                  <legend>${customerOrderModel.customer.customerName} ${customerOrderModel.customer.customerCity}</legend>
                </div>
              </div>

              <c:set var="successMessages" value="${successMessages}" scope="request" />
              <c:set var="errorMessages" value="${errorMessages}" scope="request" />
              <c:set var="infoMessages" value="${infoMessages}" scope="request" />
              <jsp:include page="/jsp_new/common/message.jsp" />

              <c:if test="${not empty customerOrderModel.fitting}">
                <legend id="new_product_title">Dodaj produkt do zamówienia</legend>

                <div id="new_product_box" class="row-fluid">
                  <div class="span12">
                    <div class="row-fluid">
                      <ul class="custom-content-box">
                        <li>
                          <legend class="tight muted">Wybierz produkt</legend>

                          <form:form id="fittingForm" name="fittingForm" action="newCustomerProduct.htm"
                            method="post" modelAttribute="customerOrderModel.fitting" class="form form-inline">

                            <form:hidden path="gripNumber.value" />

                            <div class="control-group span2">
                              <label class="control-label" for="fittingType">Typ złaczki</label>
                              <div class="controls">
                                <form:select id="fittingType" data-placeholder="..." path="fittingType.value"
                                  disabled="${customerOrderModel.fitting.fittingType.disabled}">
                                  <form:option value="" label="" />
                                  <form:options items="${customerOrderModel.fittingDesc.fittingTypes}" itemValue="fittingTypeName"
                                    itemLabel="fittingTypeName" />
                                </form:select>
                              </div>
                            </div>

                            <c:if test="${customerOrderModel.fitting.tubeDim != null}">
                              <div id="tubeDim_d" class="control-group span2">
                                <label class="control-label" for="tubeDim">Wymiar rury</label>
                                <div class="controls">
                                  <form:select id="tubeDim" data-placeholder="..." path="tubeDim.value"
                                    disabled="${customerOrderModel.fitting.tubeDim.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${customerOrderModel.fittingDesc.tubeDims}" itemValue="tubeDimName"
                                      itemLabel="tubeDimName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                            <c:if test="${customerOrderModel.fitting.threadDim != null}">
                              <div id="threadDim_d" class="control-group span2">
                                <label class="control-label" for="threadDim">Gwint</label>
                                <div class="controls">
                                  <form:select id="threadDim" data-placeholder="..." path="threadDim.value"
                                    disabled="${customerOrderModel.fitting.threadDim.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${customerOrderModel.fittingDesc.threadDims}" itemValue="threadDimName"
                                      itemLabel="threadDimName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                            <c:if test="${customerOrderModel.fitting.adaptor != null}">
                              <div id="adaptor_d" class="control-group span2">
                                <label class="control-label" for="adaptor">Redukcja</label>
                                <div class="controls">
                                  <form:select id="adaptor" data-placeholder="..." path="adaptor.value"
                                    disabled="${customerOrderModel.fitting.adaptor.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${customerOrderModel.fittingDesc.adaptors}" itemValue="adaptorName"
                                      itemLabel="adaptorName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                            <c:if test="${customerOrderModel.fitting.oring != null}">
                              <div id="oring_d" class="control-group span2">
                                <label class="control-label" for="oring">Uszczelnienie</label>
                                <div class="controls">
                                  <form:select id="oring" data-placeholder="..." path="oring.value"
                                    disabled="${customerOrderModel.fitting.oring.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${customerOrderModel.fittingDesc.orings}" itemValue="oringName"
                                      itemLabel="oringName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                            <c:if test="${customerOrderModel.fitting.grip != null}">
                              <div id="grip_d" class="control-group span2">
                                <label class="control-label" for="grip">Pierscień</label>
                                <div class="controls">
                                  <form:select id="grip" data-placeholder="..." path="grip.value"
                                    disabled="${customerOrderModel.fitting.grip.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${customerOrderModel.fittingDesc.grips}" itemValue="gripName"
                                      itemLabel="gripName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                          </form:form>
                          <p class="small-spacer">&nbsp;
                          </p>
                        </li>
                      </ul>
                    </div>
                    <p>&nbsp;
                    </p>
                  </div>
                </div>

                <!-- c:if test="${not empty customerOrderModel.fitting.pricing}" -->
                <div class="row-fluid" id="order_details">
                  <div class="span5">
                    <ul class="custom-content-box">
                      <li>
                        <div class="span12">
                          <legend class="tight muted">Wpisz ilości i ceny</legend>

                          <form id="mainPricingDetails" name="mainPricingDetails" action="mainPricingDetails.htm"
                            method="post" class="form form-inline">

                            <div class="control-group span4">
                              <label class="control-label" for="amount">Ilość sztuk</label>
                              <div class="controls">
                                <input id="amount" type="text" disabled="false" class="input-small" placeholder="np. 120" />
                              </div>
                            </div>
                            <div class="control-group span4">
                              <label class="control-label" for="price">Cena</label>
                              <div class="controls">
                                <div class="input-append">
                                  <input id="price" type="text" disabled="true" class="input-small" placeholder="np. 50" />
                                  <span class="add-on">€</span>
                                </div>
                              </div>
                            </div>
                          </form>
                        </div>
                        <p class="small-spacer"></p>
                        <div class="form-actions">
                          <button type="submit" class="btn btn-primary"
                            onclick="$('#pricingAction').val('Add'); $('#pricingDetails').submit();">Dodaj do zamówienia</button>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="span7">
                    <ul class="custom-content-box">
                      <li>
                        <legend class="tight muted">Wpisz ilości i ceny</legend>
                        <div class="span12">
                          <form id="pricingDetails" name="pricingDetails" action="pricingDetails.htm" method="post"
                            class="form form-inline">

                            <div class="span12">
                              <div class="control-group span4">
                                <label class="control-label" for="priceDisc">Cena z cennika</label>
                                <div class="controls">
                                  <div class="input-append">
                                    <input id="priceDisc" type="text" disabled="true" class="input-small"
                                      placeholder="np. 50" />
                                    <span class="add-on">€</span>
                                  </div>
                                </div>
                                <a href="#">Wybierz</a>
                              </div>
                              <div class="control-group span4">
                                <label class="control-label" for="priceDisc">
                                  Cena z
                                  <a href="#">poprzedniej faktury</a>
                                </label>
                                <div class="controls">
                                  <div class="input-append">
                                    <input id="priceDisc" type="text" disabled="true" class="input-small"
                                      placeholder="np. 50" />
                                    <span class="add-on">€</span>
                                  </div>
                                </div>
                                <a href="#">Wybierz</a>
                              </div>
                              <div class="control-group span4">
                                <label class="control-label" for="marginPercent">Procent prowizji</label>
                                <div class="controls">
                                  <div class="input-append">
                                    <input id="marginPercent" type="text" disabled="true" class="input-small"
                                      placeholder="np. 0" />
                                    <span class="add-on">%</span>
                                  </div>
                                </div>
                              </div>
                            </div>
                            <p class="small-spacer"></p>
                            <div>
                              <div class="control-group span4">
                                <label class="control-label" for="gridPrice">Cena pierścienia</label>
                                <div class="controls">
                                  <div class="input-append">
                                    <input id="gripPrice" type="text" disabled="true" class="input-small"
                                      placeholder="np. 50" />
                                    <span class="add-on">€</span>
                                  </div>
                                </div>
                              </div>
                              <div class="control-group span4">
                                <label class="control-label" for="gripNumber">Ilość pierścieni</label>
                                <div class="controls">
                                  <input id="gripNumber" type="text" disabled="true" class="input-small"
                                    placeholder="np. 2" />
                                </div>
                              </div>
                            </div>
                          </form>
                        </div>
                        <p class="small-spacer"></p>
                        <div class="form-actions">
                          <c:choose>
                            <c:when test="${customerOrderModel.fitting.pricing.fittingPrice.disabled}">
                              <button type="button" class="btn"
                                onclick="$('#pricingAction').val('Edit'); $('#pricingDetails').submit();">Edytuj</button>
                            </c:when>
                            <c:otherwise>
                              <button type="button" class="btn"
                                onclick="$('#pricingAction').val('Save'); $('#pricingDetails').submit();">Zapisz</button>
                            </c:otherwise>
                          </c:choose>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <!-- /c:if -->
              </c:if>
            </div>
          </div>
          <p>&nbsp;
          </p>

          <legend>Bieżące zamówienie - ${customerOrderModel.customer.customerName} ${customerOrderModel.customer.customerCity}</legend>

          <form:form name="changeOrder" id="changeOrder" action="changeCustomerOrder.htm" method="post">
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
                <c:forEach items="${customerOrderModel.lineItems}" var="product" varStatus="i">
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
                <c:if test="${not empty customerOrderModel.lineItems}">
                  <tr>
                    <td colspan="2">
                      <div class="text-right">
                        <h5 class="text-right">Całkowita cena zamówienia</h5>
                      </div>
                    </td>
                    <td colspan="2">
                      <h4>${customerOrderModel.totalPrice}</h4>
                    </td>
                  </tr>
                </c:if>
              </tbody>
            </table>
            <input type="hidden" id="itemModified" name="itemModified" value="" />
            <input type="hidden" id="itemAction" name="itemAction" value="" />
          </form:form>
        </div>
      </div>
    </div>
    <form:form id="addNewProduct" name="addNewProductForm" action="addNewCustomerProduct.htm" method="post"
      modelAttribute="customerOrderModel">
    </form:form>
    <form:form id="newOrder" name="newOrderForm" action="newCustomerOrder.htm" method="post" modelAttribute="customerOrderModel">
    </form:form>
    <form:form id="fittingTypesForm" name="fittingTypesForm" action="addNewCustomerProduct.htm" method="post"
      modelAttribute="customerOrderModel">
      <input id="chosenFittingType" name="chosenFittingType" type="hidden" />
    </form:form>
  </body>
</html>