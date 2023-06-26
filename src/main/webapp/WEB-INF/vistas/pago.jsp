<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container">

    <h1>Su numero de Compra es: ${numeroPedido}</h1>
    <div class="d-flex flex-wrap gap-1 row-gap-5 justify-content-left">
        <div>
            <p>Distancia: ${distanciaEnvio}</p>
            <p>Costo envío: ${costoEnvio}</p>
        </div>
        <form:form action="pagar" method="POST">
            <c:if test="${not empty error}">
                <h5><span>${error}</span></h5>
                <br>
            </c:if>
            <c:if test="${empty exito}">
                <h5><span>${exito}</span></h5>
                <br>
            </c:if>

            <button class="btn btn-lg btn-primary btn-block my-2" type="submit">Pagar</button>
        </form:form>
        <%--  <c:forEach var="item" items="${contenedores}">

            <c:if test="${item.tipoContenedor == 'BOLSA'}">
                <p>Bolsa</p>
                <div class="flex flex-col">
                   <c:forEach var="productos" items="${item.listaProductos}">
                        <li class="list-group-item">
                            <div class="d-flex gap-3">
                                <img src="${pageContext.request.contextPath}/static/img/${productos.img}" alt=""
                                     style="width: 80px"/>
                                <h5 class="card-title">
                                        <c:out value="${productos.nombre}"/>
                                    <p class="precio fs-3 fw-bold">$${productos.precioArs}</p>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${item.tipoContenedor == 'CAJA'}">
                <p>Caja</p>
                <div class="flex flex-col">
                    <c:forEach var="productosCaja" items="${item.listaProductos}">
                        <li class="list-group-item">
                            <div class="d-flex gap-3">
                                <img src="${pageContext.request.contextPath}/static/img/${productosCaja.img}" alt=""
                                     style="width: 80px"/>
                                <h5 class="card-title">
                                        <c:out value="${productosCaja.nombre}"/>
                                    <p class="precio fs-3 fw-bold">$${productosCaja.precioArs}</p>
                            </div>
                        </li>
                    </c:forEach>
                </div>
            </c:if>
        </c:forEach>--%>
    </div>

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