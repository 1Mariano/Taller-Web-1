<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container">

    <h1 class="my-4">Su número de compra es: <span class="precio fw-bold">${numeroEnvio}</span></h1>

    <form:form action="pagar" method="POST" modelAttribute="datosPago">
        <c:if test="${empty error}">
            <h5><span>${exito}</span></h5>
            <br>
        </c:if>
        <c:if test="${not empty error}">
            <h5><span>${error}</span></h5>
            <br>
        </c:if>
        <div class="mb-3 w-50">
            <div class="w-100">
                <label for="nombreYApellidoDeTitular" class="form-label">Nombre y Apellido</label>
                <div>
                    <form:input path="nombreYApellidoDeTitular" id="nombreYApellidoDeTitular" type="text" class="form-control"/>
                </div>
            </div>
        </div>


        <button class="btn btn-lg btn-primary btn-block my-2" type="submit">Pagar</button>
    </form:form>



    <div class="d-flex flex-column gap-5">

        <div class="p-3 card-body rounded contenedorbc">
            <h2 class="mb-3 precio fw-semibold text-center">Bolsas</h2>
            <hr style="color: #AAADB0">
            <div class="d-flex justify-content-between gap-5">
                <c:forEach var="bolsa" items="${bolsas}">
                    <div class="list-group">
                        <div class="list-group-item text-center p-auto">
                            <p class="m-2 fw-semibold">Bolsa ID: ${bolsa.key}</p>
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

        <div class="p-3 card-body rounded contenedorbc">
            <h2 class="mb-3 precio fw-semibold text-center">Cajas</h2>
            <hr style="color: #AAADB0">
            <div class="d-flex justify-content-between gap-5">
                <c:forEach var="caja" items="${cajas}">
                    <div class="list-group">
                        <div class="list-group-item text-center p-auto">
                            <p class="m-2 fw-semibold">Bolsa ID: ${caja.key}</p>
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