
<%@ include file="/jsp/common/include.jsp" %>

<div id="menu">
  <a class="activelist" href="index.htm">
    <span class="tab">HOME</span>
  </a>
  <span class="tab">Zamowienia</span>
  <span class="tag">Dostawcy</span>
  <ul>
    <c:choose>
      <c:when test="${param.page == 'createSupplierOrder'}">
        <li class="activelist">
          <a href="createSupplierOrder.htm">Stworz zamowienie</a>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <a href="createSupplierOrder.htm">Stworz zamowienie</a>
        </li>
      </c:otherwise>
    </c:choose>
    <c:choose>
      <c:when test="${param.page == 'ordersNotCompleted'}">
        <li class="activelist">
          <a href="ordersNotCompleted.htm">Niezrealizowane</a>
        </li>
      </c:when>
      <c:otherwise>
        <li>
          <a href="ordersNotCompleted.htm">Niezrealizowane</a>
        </li>
      </c:otherwise>
    </c:choose>
    <li>
      <a href="ordersCompleted.htm">Zrealizowane</a>
    </li>
  </ul>
  <span class="tag">Odbiorcy</span>
  <ul>
    <li>
      <a href="createSupplierOrder.htm">Wprowadz zamowienie</a>
    </li>
    <li>
      <a href="listOrdersNotCompleted.htm">Niezrealizowane</a>
    </li>
    <li>
      <a href="listOrdersCompleted.htm">Zrealizowane</a>
    </li>
  </ul>
  <span class="tab">Magazyn</span>
  <ul>
    <li>
      <a href="http://www.freewebsitetemplates.com">Stan magazynu</a>
    </li>
    <li>
      <a href="http://www.freewebsitetemplates.com">Zmien stan</a>
    </li>
  </ul>
</div>