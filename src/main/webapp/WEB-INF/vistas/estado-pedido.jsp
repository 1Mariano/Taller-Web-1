<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container">
    <h1 class="my-4">Mis pedidos</h1>
    <table class="table">
        <thead>
        <tr class="table-warning">
            <th scope="col">Número</th>
            <th scope="col">Fecha de compra</th>
            <th scope="col">Fecha de envío</th>
            <th scope="col">Estado</th>
            <th scope="col">Vehículo</th>
            <th scope="col">Patente</th>
            <th scope="col">Total</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="pedido" items="${pedidos}">
        <tr>
            <td>${pedido.id}</td>
            <td>${fechaPedido}</td>
            <td>${fechaEnvio}</td>
            <td>${pedido.estado}</td>
            <td>${vehiculo.tipoVehiculo}</td>
            <td>${vehiculo.patente}</td>
            <td>$${pedido.costoTotal}</td>
        </tr>
        </c:forEach>

        </tbody>
    </table>
</main>
<script src="../../static/js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>