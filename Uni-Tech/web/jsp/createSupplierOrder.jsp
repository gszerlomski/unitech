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

        });


       function submitForm ( buttonID,  formName) { 
        document[formName].buttonid.value = buttonID; 
        document[formName].submit();
      }
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
            <span class="tab">Nowe zamówienie</span>
            <div class="text">

              <c:if test="${not empty successMessages}">
                <c:forEach items="${successMessages.messages}" var="message">
                  <div class="section">
                    <c:set var="messageVar" value="${message}" scope="request"/>
                    <c:set var="style" value="success" scope="request"/>
                    <jsp:include page="/jsp/common/message.jsp" />
                  </div>
                </c:forEach>
              </c:if>
              
              <c:if test="${not empty errorMessages}">
                <c:forEach items="${errorMessages.messages}" var="message">
                  <div class="section">
                    <c:set var="messageVar" value="${message}" scope="request"/>
                    <c:set var="style" value="error" scope="request"/>
                    <jsp:include page="/jsp/common/message.jsp" />
                  </div>
                </c:forEach>
              </c:if>
              
              <c:if test="${not empty infoMessages}">
                <c:forEach items="${infoMessages.messages}" var="message">
                  <div class="section">
                    <c:set var="messageVar" value="${message}" scope="request"/>
                    <c:set var="style" value="info" scope="request"/>
                    <jsp:include page="/jsp/common/message.jsp" />
                  </div>
                </c:forEach>
              </c:if>

              <c:if test="${not empty orderModel.fitting}">
                <div id="newProduct">
                  <form:form id="fittingForm" name="fittingForm" action="newProduct.htm" method="post"
                    modelAttribute="orderModel.fitting">
                    <div>
                      <table>
                        <tr>
                          <c:if test="${orderModel.fitting.fittingType != null}">
                            <th>Typ</th>
                          </c:if>
                          <c:if test="${orderModel.fitting.adaptor != null}">
                            <th>Redukcja</th>
                          </c:if>
                          <c:if test="${orderModel.fitting.tubeDim != null}">
                            <th>Wymiar rury</th>
                          </c:if>
                          <c:if test="${orderModel.fitting.threadDim != null}">
                            <th>Wymiar gwintu</th>
                          </c:if>
                          <c:if test="${orderModel.fitting.oring != null}">
                            <th>Uszczelnienie</th>
                          </c:if>
                          <c:if test="${orderModel.fitting.grip != null}">
                            <th>Pierścienie</th>
                          </c:if>
                        </tr>
                        <tr>
                          <c:if test="${orderModel.fitting.fittingType != null}">
                            <td>
                              <form:input path="fittingType.value" type="text" size="7"
                                disabled="${orderModel.fitting.fittingType.disabled}" />
                            </td>
                          </c:if>
                          <c:if test="${orderModel.fitting.adaptor != null}">
                            <td>
                              <form:input id="adaptor" path="adaptor.value" type="text" size="10"
                                disabled="${orderModel.fitting.adaptor.disabled}" />
                            </td>
                          </c:if>
                          <c:if test="${orderModel.fitting.tubeDim != null}">
                            <td>
                              <form:input id="tubeDim" path="tubeDim.value" type="text" size="10"
                                disabled="${orderModel.fitting.tubeDim.disabled}" />
                            </td>
                          </c:if>
                          <c:if test="${orderModel.fitting.threadDim != null}">
                            <td>
                              <form:input id="threadDim" path="threadDim.value" type="text" size="10"
                                disabled="${orderModel.fitting.threadDim.disabled}" />
                            </td>
                          </c:if>
                          <c:if test="${orderModel.fitting.oring != null}">
                            <td>
                              <form:select path="oring.value" disabled="${orderModel.fitting.oring.disabled}">
                                <form:option value="-- Wybierz --" label="-- Wybierz --" />
                                <form:options items="${orderModel.fittingDesc.orings}" itemValue="oringName"
                                  itemLabel="oringName" />
                              </form:select>
                            </td>
                          </c:if>
                          <c:if test="${orderModel.fitting.grip != null}">
                            <td>
                              <form:select path="grip.value" disabled="${orderModel.fitting.grip.disabled}">
                                <form:option value="-- Wybierz --" label="-- Wybierz --" />
                                <form:options items="${orderModel.fittingDesc.grips}" itemValue="gripName"
                                  itemLabel="gripName" />
                              </form:select>
                            </td>
                          </c:if>
                        </tr>
                      </table>
                      <a href="#" onclick="$('#fittingForm').submit()">Sprawdz cene</a>
                    </div>
                  </form:form>

                  <c:if test="${not empty orderModel.fitting.pricing}">
                    <form:form id="pricingDetails" name="pricingDetails" action="pricingDetails.htm" method="post"
                      modelAttribute="orderModel.fitting">

                      <div id="otherDetails" class="section">
                        <table>
                          <tr>
                            <th>Ilosc sztuk</th>
                            <th>Cena złączki</th>
                            <th>Cena z upustem</th>
                            <th>Ilość pierścieni</th>
                            <th>Cena pierścienia</th>
                          </tr>
                          <tr>
                            <td>
                              <form:input id="amount" path="pricing.amount.value" type="text"
                                disabled="${orderModel.fitting.pricing.amount.disabled}" />
                            </td>
                            <td>
                              <form:input id="price" type="text" path="pricing.fittingPrice.value" size="8"
                                disabled="${orderModel.fitting.pricing.fittingPrice.disabled}" />
                            </td>
                            <td>
                              <form:input id="priceRed" type="text" path="pricing.discountedPrice.value"
                                size="8" disabled="${orderModel.fitting.pricing.discountedPrice.disabled}" />
                            </td>
                            <td>
                              <form:input id="gripNumber" type="text" path="gripNumber.value" size="2"
                                disabled="${orderModel.fitting.gripNumber.disabled}" />
                            </td>
                            <td>
                              <form:input id="gripPrice" type="text" path="pricing.gripPrice.value" size="6"
                                disabled="${orderModel.fitting.pricing.gripPrice.disabled}" />
                            </td>
                            <td>
                              <c:choose>
                                <c:when test="${orderModel.fitting.pricing.fittingPrice.disabled}">
                                  <a href="#" onclick="$('#pricingAction').val('Edit'); $('#pricingDetails').submit();">Edytuj</a>
                                </c:when>
                                <c:otherwise>
                                  <a href="#" onclick="$('#pricingAction').val('Save'); $('#pricingDetails').submit();">Zapisz</a>
                                </c:otherwise>
                              </c:choose>
                            </td>
                          </tr>
                        </table>
                        <a href="#" onclick="$('#pricingAction').val('Add'); $('#pricingDetails').submit();">Dodaj do zamówienia</a>
                      </div>
                      <input type="hidden" id="pricingAction" name="pricingAction" value="" />
                    </form:form>
                  </c:if>
                  <hr class="strong"/>
                </div>
              </c:if>

              <div class="section">
                <form:form name="order" id="order" action="addSupplierOrderDetails.htm" method="post"
                  modelAttribute="product">
                  <table>
                    <tr>
                      <th>Produkt</th>
                      <th>Ilosc</th>
                      <th>Cena</th>
                      <th>Usun/Edytuj</th>
                    </tr>
                    <c:forEach items="${orderModel.supplierOrderModel.lineItems}" var="product">
                      <tr>
                        <td>${product.product.formattedName}</td>
                        <td>${product.amount}</td>
                        <td>${product.totalPrice}</td>
                        <td>
                          <a href="#">Edytuj</a>
                          |
                          <a href="#">Usun</a>
                        </td>
                      </tr>
                    </c:forEach>
                  </table>

                  <a href="#" onclick="$('#type').show();">Dodaj</a>
                  |
                  <a href="#" onclick="$('#order').submit()">Stworz zamowienie</a>
                  |
                  <a href="#" onclick="$('#newOrder').submit()">Nowe zamowienie</a>
                </form:form>
              </div>

              <div id="type" class="section">
                <form:form id="fittingTypesForm" name="fittingTypesForm" action="newProductList.htm" method="post"
                  modelAttribute="orderModel">
                  <span>Typ </span>
                  <input id="fittingTypes" name="fittingTypes" type="text" />
                </form:form>
              </div>
              <form:form name="newOrder" id="newOrder" action="newOrder.htm" method="post">
              </form:form>

            </div>
          </div>
          <%@ include file="/jsp/common/copyright.jsp" %>
        </div>
      </div>
    </div>
  </body>
</html>
