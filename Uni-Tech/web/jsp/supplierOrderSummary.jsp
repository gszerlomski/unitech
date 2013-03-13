<%@ include file="/jsp/common/include.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
  <head>
    <title>Order Summary</title>
    <link href="css/raw.css" rel="stylesheet" type="text/css" />
    <link href="css/print.css" rel="stylesheet" type="text/css" media="print" />
  </head>
  <body>
    <div name="header">
      <div>Uni-Tech</div>
      <div class="dateBox">${orderModel.supplierOrderModel.creationDateString}</div>
      <div class="title" name="title">Zamówienie</div>
      <div name="supplierDetails" class="titleBox">
        <p>
          <span class="title" name="supplierName">${orderModel.supplierOrderModel.supplier.supplierName}</span>
          <span>${orderModel.supplierOrderModel.supplier.supplierAddress}</span>
          <span>tel. ${orderModel.supplierOrderModel.supplier.supplierContactNum}</span>
        </p>
      </div>
      <div name="productList" class="mainBox">
        <table>
          <tr>
            
            <th>Produkt</th>
            <th>Ilosć sztuk</th>
            <th>Cena za sztukę</th>
            <th>Cena całkowita</th>
          </tr>
          
          <c:forEach items="${orderModel.supplierOrderModel.lineItems}" var="product">
            <tr>
              
              <td>${product.product.formattedName}</td>
              <td>${product.amount}</td>
              <td>${product.singleProductPrice}</td>
              <td>${product.totalPrice}</td>
            </tr>
          </c:forEach>
            <tr>
              <td colspan="3">Całkowita cena zamówienia</td>
              <td>${orderModel.supplierOrderModel.totalPrice}</td>
            </tr>
        </table>
      </div>
      <div name="otherDetails" class="mainBox">
        Termin dostarczenia:<span class="importantNote">${orderModel.supplierOrderModel.estimatedDeliveryDateString}</span>
      </div>
      <div class="mainBox">
        <a href="javascript:window.print()"><img src="images/print.jpg" alt="print this page" id="print-button" /></a>
      </div>
  </body>
</html>