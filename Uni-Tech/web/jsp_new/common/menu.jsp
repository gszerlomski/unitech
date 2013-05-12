
<%@ include file="/jsp/common/include.jsp" %>

<div class="span2">
  <ul class="nav nav-list bs-docs-sidenav affix sidenav-menu pull-right">
    <li class="nav-header">Dostawcy</li>

    <c:choose>
      <c:when test="${param.page == 'createSupplierOrder'}">
        <li class="active">
          <a href="createSupplierOrder.htm">Stwórz zamówienie</a>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <a href="createSupplierOrder.htm">Stwórz zamówienie</a>
        </li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${param.page == 'ordersNotCompleted'}">
        <li class="active">
          <a href="ordersNotCompleted.htm">Niezrealizowane</a>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <a href="ordersNotCompleted.htm">Niezrealizowane</a>
        </li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${param.page == 'ordersCompleted'}">
        <li class="active">
          <a href="ordersCompleted.htm">Zrealizowane</a>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <a href="ordersCompleted.htm">Zrealizowane</a>
        </li>
      </c:otherwise>
    </c:choose>
    
    
    <li class="nav-header">Odbiorcy</li>
    
    <c:choose>
      <c:when test="${param.page == 'createCustomerOrder'}">
        <li class="active">
          <a href="createCustomerOrder.htm">Wprowadz zamowienie</a>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <a href="createCustomerOrder.htm">Wprowadz zamowienie</a>
        </li>
      </c:otherwise>
    </c:choose>
    <li>
      <a href="listOrdersNotCompleted.htm">Niezrealizowane</a>
    </li>
    <li>
      <a href="listOrdersCompleted.htm">Zrealizowane</a>
    </li>
    <li class="nav-header">Magazyn</li>
    <li>
      <a href="#">Stan magazynu</a>
    </li>
    <li>
      <a href="#">Zmien stan</a>
    </li>
  </ul>
</div>