<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container">

    <h3 class="my-4 fw-bold text-secondary-emphasis">Su número de compra es: <span
            class="precio fw-bold">${numeroPedido}</span></h3>

    <div>
        <table class="table">
            <tbody>
            <tr class="mt-2">
                <td>Subtotal</td>
                <td class="text-end">$${costoProductos}</td>
            </tr>
            <tr class="mb-2">
                <td>Costo de envío</td>
                <td class="text-end">$${costoEnvio}</td>
            </tr>
            <tr class="fw-bold table-warning">
                <td>Total</td>
                <td class="text-end">$${costoTotal}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <c:if test="${not empty error}">
        <div class="text-nowrap text-danger mb-2" id="error" style="height: 15px">
            <h5 class="error m-0"><span>${error}</span></h5>
            <br>
        </div>
    </c:if>


    <form:form action="pagar" method="POST" modelAttribute="datosPago">
        <button class="btn btn-lg btn-primary btn-block my-2" type="submit">Confirmar pago</button>
    </form:form>


    <div class="py-5 d-flex flex-column gap-5">

        <c:set var="contador" value="0"/>
        <c:if test="${not empty bolsas}">
            <div class="p-3 card-body rounded contenedorbc">
                <h2 class="mb-3 precio fw-semibold text-center">Bolsas</h2>
                <hr style="color: #AAADB0">
                <div class="d-flex flex-wrap justify-content-center gap-5">
                    <c:forEach var="bolsa" items="${bolsas}">
                        <c:set var="contador" value="${contador + 1}"/>
                        <div class="list-group">
                            <div class="list-group-item text-center p-auto">
                                <p class="m-2 fw-semibold">Bolsa n°${contador}</p>
                            </div>
                            <c:forEach var="producto" items="${bolsa.value}">
                                <ul class="list-unstyled m-0 list-group-item" style="width: 375px;">
                                    <div class="d-flex align-items-center gap-4" style="height: 85px">
                                        <img src="${pageContext.request.contextPath}/static/img/${producto.img}" alt=""
                                             style="width: 80px"/>
                                        <li>${producto.nombre}</li>
                                    </div>
                                </ul>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

        <c:set var="contador" value="0"/>
        <c:if test="${not empty cajas}">
            <div class="p-3 card-body rounded contenedorbc">
                <h2 class="mb-3 precio fw-semibold text-center">Cajas</h2>
                <hr style="color: #AAADB0">
                <div class="d-flex flex-wrap justify-content-center gap-5">
                    <c:forEach var="caja" items="${cajas}">
                        <c:set var="contador" value="${contador + 1}"/>
                        <div class="list-group">
                            <div class="list-group-item text-center p-auto">
                                <p class="m-2 fw-semibold">Caja n°${contador}</p>
                            </div>
                            <c:forEach var="producto" items="${caja.value}">
                                <ul class="list-unstyled m-0 list-group-item" style="width: 375px;">
                                    <div class="d-flex align-items-center gap-4" style="height: 85px">
                                        <img src="${pageContext.request.contextPath}/static/img/${producto.img}" alt=""
                                             style="width: 80px"/>
                                        <li>${producto.nombre}</li>
                                    </div>
                                </ul>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </c:if>

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
<script src="https://sdk.mercadopago.com/js/v2"></script>