<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container">

    <h1 class="my-4">Su número de compra es: <span class="precio fw-bold">${numeroEnvio}</span></h1>

    <div style="position: relative">
        <form:form action="pagar" method="POST" modelAttribute="datosPago">

            <div class="mb-3 w-25">
                <label class="form-label">Opción de pago</label>
                <form:select path="opcionDePago" class="form-select" id="opcionDePago">
                    <form:option value="">Seleccione una opción</form:option>
                    <form:option value="debito">Tarjeta de débito</form:option>
                    <form:option value="credito">Tarjeta de crédito</form:option>
                </form:select>
            </div>

            <div class="mb-3 w-25" id="tipoDeTarjeta">

                <div class="p-0 d-flex gap-3 justify-content-around align-items-center form-check"
                     style="height: 135px">

                    <div class="d-flex flex-column align-items-center gap-3">
                        <label for="visa" class="form-check-label">
                            <img src="${pageContext.request.contextPath}/static/img/visa.jpg" alt=""
                                 style="width: 85px; height: 65px"/>
                        </label>
                        <form:radiobutton path="tipoDeTarjeta" id="visa" value="visa" class="form-check-input m-0"/>
                    </div>

                    <div class="d-flex flex-column align-items-center gap-3">
                        <label for="mastercard" class="form-check-label">
                            <img src="${pageContext.request.contextPath}/static/img/mastercard.jpg" alt=""
                                 style="width: 85px; height: 65px"/>
                        </label>
                        <form:radiobutton path="tipoDeTarjeta" id="mastercard" value="mastercard"
                                          class="form-check-input m-0"/>
                    </div>

                    <div class="d-flex flex-column align-items-center gap-3">
                        <label for="amex" class="form-check-label">
                            <img src="${pageContext.request.contextPath}/static/img/amex.jpg" alt=""
                                 style="width: 85px; height: 65px"/>
                        </label>
                        <form:radiobutton path="tipoDeTarjeta" id="amex" value="amex" class="form-check-input m-0"/>
                    </div>

                </div>

            </div>

            <div class="mb-3 w-25">
                <label for="nombreYApellidoDeTitular" class="form-label">Nombre y apellido del titular</label>
                <div>
                    <form:input path="nombreYApellidoDeTitular" id="nombreYApellidoDeTitular" type="text"
                                class="form-control"/>
                </div>
            </div>

            <div class="mb-3 w-25">
                <label for="numeroDeTarjeta" class="form-label">Número de tarjeta</label>
                <div>
                    <form:input path="numeroDeTarjeta" id="numeroDeTarjeta" type="number"
                                class="form-control"/>
                </div>
            </div>

            <div class="w-25 d-flex justify-content-between gap-5">
                <div class="mb-3 w-50">
                    <label for="vencimiento" class="form-label">Fecha de vencimiento</label>
                    <div>
                        <form:input path="vencimiento" id="vencimiento" type="month" class="form-control"/>
                    </div>
                </div>

                <div class="mb-3 w-50">
                    <label for="cvv" class="form-label">Código de seguridad</label>
                    <div>
                        <form:input path="cvv" id="cvv" type="number" class="form-control"/>
                    </div>
                </div>
            </div>

            <div class="text-nowrap text-danger mb-2" id="errorDni" style="height: 15px">
                <c:if test="${not empty error}">
                    <h5 class="error m-0"><span>${error}</span></h5>
                    <br></c:if>
            </div>

            <button class="btn btn-lg btn-primary btn-block my-2" type="submit">Confirmar pago</button>
        </form:form>

        <div style="position: absolute; top: 0; right: 0; width: 50%">
            <table class="table">
                <tbody>
                <tr class="mt-2">
                    <td>Subtotal</td>
                    <td>$${costoProductos}</td>
                </tr>
                <tr class="mb-2">
                    <td>Costo de envío</td>
                    <td>$${costoEnvio}</td>
                </tr>
                <tr class="fw-bold table-warning">
                    <td>Total</td>
                    <td>$${costoTotal}</td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>


    <div class="my-5 d-flex flex-column gap-5">

        <c:if test="${not empty bolsas}">
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
        </c:if>

        <c:if test="${not empty cajas}">
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
        </c:if>

    </div>


</main>
<script>

    $(document).ready(function () {

        $('#tipoDeTarjeta').hide();

        var selectElement = $("#opcionDePago");
        var divElement = $("#tipoDeTarjeta");

        selectElement.on("change", function () {
            if (selectElement.val() === "credito") {
                divElement.show();
            } else {
                divElement.hide();
            }
        });
    })

</script>
<script src="../../static/js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>