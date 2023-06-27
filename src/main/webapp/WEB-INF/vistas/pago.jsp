<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container">

    <h1>Su numero de Compra es: ${numeroPedido}</h1>
    <div class="d-flex flex-wrap gap-5 row-gap-5 justify-content-center">
        <!-- Mostrar cajas -->

        <div class="d-flex flex-column">
            <h2>Bolsas:</h2>
        <c:forEach var="bolsa" items="${bolsas}">
            <p>Bolsa ID: ${bolsa.key}</p>
            <ul>
                <c:forEach var="producto" items="${bolsa.value}">
                    <li>${producto.nombre} - ${producto.img}</li>
                </c:forEach>
            </ul>
        </c:forEach>
        </div>


        <div class="d-flex flex-column">
            <h2>Cajas:</h2>
        <c:forEach var="caja" items="${cajas}">
            <p>Caja ID: ${caja.key}</p>
            <ul>
                <c:forEach var="producto" items="${caja.value}">
                    <li>${producto.nombre} - ${producto.img}</li>
                </c:forEach>
            </ul>
        </c:forEach>
        </div>

    </div>
    <form:form action="pagar" method="POST">
        <c:if test="${not empty error}">
            <h5><span>${error}</span></h5>
            <br>
        </c:if>
        <c:if test="${empty error}">
            <h5><span>${exito}</span></h5>
            <br>
        </c:if>

        <button class="btn btn-lg btn-primary btn-block my-2" type="submit">Pagar</button>
    </form:form>

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