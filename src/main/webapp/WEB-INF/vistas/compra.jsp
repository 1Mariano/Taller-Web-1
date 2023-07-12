<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container">
    <div class="d-flex align-items-center mt-5">
        <i class="fa-solid fa-basket-shopping fa-2xl" style="color: #212529;"></i>
        <h3 class="my-4 mx-3 fw-semibold">Productos para procesar la compra</h3>
    </div>
    <div class="d-flex gap-5 justify-content-center">
        <ol class="list-group list-group-numbered scrollable">
            <c:forEach var="item" items="${productos}">
                <li class="list-group-item">
                    <div class="d-flex gap-3">
                        <img src="${pageContext.request.contextPath}/static/img/${item.img}" alt=""
                             style="width: 80px"/>
                        <div class="card-title">
                            <c:out value="${item.nombre}"/>
                            <p class="precio fs-3 fw-bold">$${item.precioArs}</p>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ol>
        <div>
            <div class="text-nowrap mb-3">
                <h5 class="fw-semibold">Datos de envío</h5>
            </div>
            <form:form action="validar-datos-envio" method="POST" modelAttribute="datosEnvio">
                <div class="d-flex mb-5 gap-3">
                    <div class="flex-fill me-3">
                        <label for="calle" class="form-label">Calle</label>
                        <div class="">
                            <form:input path="calle" type="text" id="calle" class="form-control w-180"/>
                        </div>
                        <c:if test="${not empty errorCalle}">
                            <div class="text-nowrap text-danger" id="errorCalle" style="height: 15px">
                                <h5 class="error m-0 pt-2"><span>${errorCalle}</span></h5>
                                <br>
                            </div>
                        </c:if>
                    </div>
                    <div class="flex-fill w-25 me-3">
                        <label for="numero" class="form-label">Número</label>
                        <div class="">
                            <form:input path="numero" type="number" id="numero" class="form-control"/>
                        </div>
                        <c:if test="${not empty errorNumero}">
                            <div class="text-nowrap text-danger" id="errorNumero" style="height: 15px">
                                <h5 class="error m-0 pt-2"><span>${errorNumero}</span></h5>
                                <br>
                            </div>
                        </c:if>
                    </div>
                    <div class="flex-fill w-25">
                        <label for="pisoODepartamento" class="form-label text-nowrap">Piso/Depto.</label>
                        <div class="">
                            <form:input path="pisoODepartamento" type="text" id="pisoODepartamento"
                                        class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="d-flex gap-3 mb-3">
                    <div class="flex-fill me-3">
                        <label for="localidad" class="form-label">Localidad</label>
                        <div class="">
                            <form:input path="localidad" type="text" id="localidad" class="form-control w-180"/>
                        </div>
                        <c:if test="${not empty errorLocalidad}">
                            <div class="text-nowrap text-danger" id="errorLocalidad" style="height: 15px">
                                <h5 class="error m-0 pt-2"><span>${errorLocalidad}</span></h5>
                                <br>
                            </div>
                        </c:if>
                    </div>
                    <div class="flex-fill w-25 me-3">
                        <label for="codigoPostal" class="form-label text-nowrap">Código postal</label>
                        <div class="">
                            <form:input path="codigoPostal" type="text" id="codigoPostal" class="form-control"/>
                        </div>
                        <c:if test="${not empty errorCp}">
                            <div class="text-nowrap text-danger" id="errorCp" style="height: 15px">
                                <h5 class="error m-0 pt-2"><span>${errorCp}</span></h5>
                                <br>
                            </div>
                        </c:if>
                    </div>
                    <div class="flex-fill w-25">
                        <div class=""></div>
                        <div class=""></div>
                    </div>
                </div>

                <button class="btn btn-lg btn-primary btn-block my-2" type="submit">Calcular envío
                </button>
            </form:form>
        </div>
    </div>

</main>
<script>
    $(document).ready(function () {

        if ($('#errorCalle .error').hasClass('error')) {
            $('#calle').addClass('is-invalid');
        }

        if ($('#errorNumero .error').hasClass('error')) {
            $('#numero').addClass('is-invalid');
        }

        if ($('#errorLocalidad .error').hasClass('error')) {
            $('#localidad').addClass('is-invalid');
        }

        if ($('#errorCp .error').hasClass('error')) {
            $('#codigoPostal').addClass('is-invalid');
        }

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