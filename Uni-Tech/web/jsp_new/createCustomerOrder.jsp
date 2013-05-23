
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


              <c:set var="successMessages" value="${successMessages}" scope="request" />
              <c:set var="errorMessages" value="${errorMessages}" scope="request" />
              <c:set var="infoMessages" value="${infoMessages}" scope="request" />
              <jsp:include page="/jsp_new/common/message.jsp" />

              <c:if test="${not empty orderModel.fitting}">
                <legend id="new_product_title">Dodaj produkt do zamówienia</legend>

                <div id="new_product_box" class="row-fluid">
                  <div class="span12">
                    <div class="row-fluid">
                      <ul class="custom-content-box">
                        <li>
                          <legend class="tight muted">Wybierz produkt</legend>

                          <form:form id="fittingForm" name="fittingForm" action="newCustomerProduct.htm" method="post"
                            modelAttribute="orderModel.fitting" class="form form-inline">
                            
                            <form:hidden path="gripNumber.value" />

                            <div class="control-group span2">
                              <label class="control-label" for="fittingType">Typ złaczki</label>
                              <div class="controls">
                                <form:select id="fittingType" data-placeholder="..." path="fittingType.value"
                                  disabled="${orderModel.fitting.fittingType.disabled}">
                                  <form:option value="" label="" />
                                  <form:options items="${orderModel.fittingDesc.fittingTypes}" itemValue="fittingTypeName"
                                    itemLabel="fittingTypeName" />
                                </form:select>
                              </div>
                            </div>

                            <c:if test="${orderModel.fitting.tubeDim != null}">
                              <div id="tubeDim_d" class="control-group span2">
                                <label class="control-label" for="tubeDim">Wymiar rury</label>
                                <div class="controls">
                                  <form:select id="tubeDim" data-placeholder="..." path="tubeDim.value"
                                    disabled="${orderModel.fitting.tubeDim.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${orderModel.fittingDesc.tubeDims}" itemValue="tubeDimName"
                                      itemLabel="tubeDimName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                            <c:if test="${orderModel.fitting.threadDim != null}">
                              <div id="threadDim_d" class="control-group span2">
                                <label class="control-label" for="threadDim">Gwint</label>
                                <div class="controls">
                                  <form:select id="threadDim" data-placeholder="..." path="threadDim.value"
                                    disabled="${orderModel.fitting.threadDim.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${orderModel.fittingDesc.threadDims}" itemValue="threadDimName"
                                      itemLabel="threadDimName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                            <c:if test="${orderModel.fitting.adaptor != null}">
                              <div id="adaptor_d" class="control-group span2">
                                <label class="control-label" for="adaptor">Redukcja</label>
                                <div class="controls">
                                  <form:select id="adaptor" data-placeholder="..." path="adaptor.value"
                                    disabled="${orderModel.fitting.adaptor.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${orderModel.fittingDesc.adaptors}" itemValue="adaptorName"
                                      itemLabel="adaptorName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                            <c:if test="${orderModel.fitting.oring != null}">
                              <div id="oring_d" class="control-group span2">
                                <label class="control-label" for="oring">Uszczelnienie</label>
                                <div class="controls">
                                  <form:select id="oring" data-placeholder="..." path="oring.value"
                                    disabled="${orderModel.fitting.oring.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${orderModel.fittingDesc.orings}" itemValue="oringName"
                                      itemLabel="oringName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                            <c:if test="${orderModel.fitting.grip != null}">
                              <div id="grip_d" class="control-group span2">
                                <label class="control-label" for="grip">Pierscień</label>
                                <div class="controls">
                                  <form:select id="grip" data-placeholder="..." path="grip.value"
                                    disabled="${orderModel.fitting.grip.disabled}">
                                    <form:option value="" label="" />
                                    <form:options items="${orderModel.fittingDesc.grips}" itemValue="gripName"
                                      itemLabel="gripName" />
                                  </form:select>
                                </div>
                              </div>
                            </c:if>
                          </form:form>
                          <p class="small-spacer" ">&nbsp;
                          </p>
                        </li>
                      </ul>
                    </div>
                    <p>&nbsp;
                    </p>
                  </div>
                </div>

                <c:if test="${not empty orderModel.fitting.pricing}">
                  <div class="row-fluid" id="order_details">
                    <ul class="custom-content-box">
                      <li>
                        <legend class="tight muted">Wpisz ilości i ceny</legend>

                        <!-- div class="alert"> <button type="button" class="close" data-dismiss="alert">&times; </button> 
                          <strong>Warning!</strong> Best check yo self, you're not looking too good. </div -->

                        <form:form id="pricingDetails" name="pricingDetails" action="pricingDetails.htm"
                          method="post" modelAttribute="orderModel.fitting" class="form form-inline">

                          <div class="control-group span3">
                            <label class="control-label" for="amount">Ilość sztuk</label>
                            <div class="controls">
                              <form:input id="amount" path="pricing.amount.value" type="text"
                                disabled="${orderModel.fitting.pricing.amount.disabled}" class="input-small"
                                placeholder="np. 120" />
                            </div>
                          </div>
                          <div class="control-group span2">
                            <label class="control-label" for="price">Cena podstawowa</label>
                            <div class="controls">
                              <div class="input-append">
                                <form:input id="price" type="text" path="pricing.fittingPrice.value"
                                  disabled="${orderModel.fitting.pricing.fittingPrice.disabled}" class="input-small"
                                  placeholder="np. 50" />
                                <span class="add-on">€</span>
                              </div>

                            </div>
                          </div>
                          <div class="control-group span2">
                            <label class="control-label" for="priceDisc">Cena z upustem</label>
                            <div class="controls">
                              <div class="input-append">
                                <form:input id="priceDisc" type="text" path="pricing.discountedPrice.value"
                                  disabled="${orderModel.fitting.pricing.discountedPrice.disabled}" class="input-small"
                                  placeholder="np. 50" />
                                <span class="add-on">€</span>
                              </div>
                            </div>
                          </div>
                          <div class="control-group span2">
                            <label class="control-label" for="gridPrice">Cena pierścienia</label>
                            <div class="controls">
                              <div class="input-append">
                                <form:input id="gripPrice" type="text" path="pricing.gripPrice.value"
                                  disabled="${orderModel.fitting.pricing.gripPrice.disabled}" class="input-small"
                                  placeholder="np. 50" />
                                <span class="add-on">€</span>
                              </div>
                            </div>
                          </div>
                          <div class="control-group span2">
                            <label class="control-label" for="gripNumber">Ilość pierścieni</label>
                            <div class="controls">
                              <form:input id="gripNumber" type="text" path="gripNumber.value"
                                disabled="${orderModel.fitting.gripNumber.disabled}" class="input-small" placeholder="np. 2" />
                            </div>
                          </div>
                          <input type="hidden" id="pricingAction" name="pricingAction" value="" />
                        </form:form>
                        <p class="small-spacer">&nbsp;
                        </p>
                        <div class="form-actions">
                          <button type="submit" class="btn btn-primary"
                            onclick="$('#pricingAction').val('Add'); $('#pricingDetails').submit();">Dodaj do zamówienia</button>
                          <c:choose>
                            <c:when test="${orderModel.fitting.pricing.fittingPrice.disabled}">
                              <button type="button" class="btn"
                                onclick="$('#pricingAction').val('Edit'); $('#pricingDetails').submit();">Edytuj ceny</button>
                            </c:when>
                            <c:otherwise>
                              <button type="button" class="btn"
                                onclick="$('#pricingAction').val('Save'); $('#pricingDetails').submit();">Zapisz ceny</button>
                            </c:otherwise>
                          </c:choose>
                        </div>
                      </li>
                    </ul>
                  </div>
                </c:if>
              </c:if>
            </div>
          </div>
          <p>&nbsp;
          </p>

          <legend>Bieżące zamówienie - ${orderModel.customer.customerName} ${orderModel.customer.customerCity}</legend>

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
                <c:forEach items="${orderModel.lineItems}" var="product" varStatus="i">
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
                <c:if test="${not empty orderModel.lineItems}">
                  <tr>
                    <td colspan="2">
                      <div class="text-right">
                        <h5 class="text-right">Całkowita cena zamówienia</h5>
                      </div>
                    </td>
                    <td colspan="2">
                      <h4>${orderModel.totalPrice}</h4>
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
      modelAttribute="orderModel">
    </form:form>
    <form:form id="newOrder" name="newOrderForm" action="newCustomerOrder.htm" method="post" modelAttribute="orderModel">
    </form:form>
    <form:form id="fittingTypesForm" name="fittingTypesForm" action="addNewCustomerProduct.htm" method="post"
      modelAttribute="orderModel">
      <input id="chosenFittingType" name="chosenFittingType" type="hidden" />
    </form:form>
  </body>
</html>