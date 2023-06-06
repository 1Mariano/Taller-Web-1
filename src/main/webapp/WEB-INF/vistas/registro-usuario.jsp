<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro</title>
    <link rel="stylesheet" href="../../css/estilos.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.misdeliver.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
</head>
<style>
    @import url('https://fonts.googleapis.com/css2?family=Work+Sans:wght@300;400&display=swap');

    body {
        font-family: 'Work Sans', sans-serif;
        font-size: larger;
        height: 100vh;
    }

    .bg-gradient1 {
        background: linear-gradient(to bottom, #BCC0ED, #EEC4EC);
    }

    .linea {
        border-left: 1px solid black;
        border-top: none;
        height: 270px;
        margin: 0 30px;
    }

    .w-180 {
        width: 180px;
    }
</style>
<body class="bg-gradient1 p-5 w-50 m-auto">
<div class="bg-light py-3 px-5 rounded">
    <div id="loginbox" class="mainbox">
        <h4 class="form-signin-heading text-center fw-semibold">Creación de cuenta nueva</h4>
        <hr>
        <form:form class="needs-validation" action="validar-registro" method="POST" modelAttribute="usuario">
            <div class="mb-2">
                <div class="w-50">
                    <label for="email" class="form-label">Email</label>
                    <div class="has-validation">
                        <form:input path="email" type="email" id="email" class="form-control" required="required"/>
                    </div>
                </div>
            </div>
            <div class="text-nowrap text-danger" id="errorEmail" style="height: 15px">
                <c:if test="${not empty errorEmail}">
                    <h5 class="error m-0"><span>${errorEmail}</span></h5>
                    <br></c:if>
            </div>
            <div class="mb-2 margen">
                <div class="w-50">
                    <label for="clave" class="form-label">Contraseña</label>
                    <div class="">
                        <form:input path="clave" type="password" id="clave" class="form-control" required="required"/>
                    </div>
                </div>
            </div>
            <div class="mb-2">
                <div class="w-50">
                    <label for="repiteClave" class="form-label text-nowrap">Confirmar contraseña</label>
                    <div class="">
                        <form:input path="repiteClave" type="password" id="repiteClave" class="form-control"
                                    required="required"/>
                    </div>
                </div>
            </div>
            <div class="text-nowrap text-danger" id="error" style="height: 15px">
                <c:if test="${not empty error}">
                    <h5 class="error m-0"><span>${error}</span></h5>
                    <br></c:if>
            </div>
            <div class="d-flex justify-content-between mt-4">
                <div class="">
                    <div class="text-nowrap mb-3">
                        <h5 class="fw-semibold">Datos personales</h5>
                    </div>
                    <div class="mb-2">
                        <div>
                            <label for="nombre" class="form-label">Nombre</label>
                            <div class="">
                                <form:input path="nombre" type="text" id="nombre" class="form-control w-180"/>
                            </div>
                        </div>
                    </div>
                    <div class="mb-2">
                        <div>
                            <label for="apellido" class="form-label">Apellido</label>
                            <div class="">
                                <form:input path="apellido" type="text" id="apellido" class="form-control w-180"/>
                            </div>
                        </div>
                    </div>
                    <div class="mb-2">
                        <div>
                            <label for="dni" class="form-label">DNI</label>
                            <div class="">
                                <form:input path="dni" type="text" id="dni" class="form-control w-180"
                                            required="required"/>
                            </div>
                        </div>
                    </div>
                </div>
                <hr class="linea">
                <div class="">
                    <div class="text-nowrap mb-3">
                        <h5 class="fw-semibold">Datos de domicilio</h5>
                    </div>
                    <div class="d-flex mb-2">
                        <div class="flex-fill me-3">
                            <label for="calle" class="form-label">Calle</label>
                            <div class="">
                                <form:input path="calle" type="text" id="calle" class="form-control w-180"/>
                            </div>
                        </div>
                        <div class="flex-fill w-25 me-3">
                            <label for="numero" class="form-label">Número</label>
                            <div class="">
                                <form:input path="numero" type="text" id="numero" class="form-control"/>
                            </div>
                        </div>
                        <div class="flex-fill w-25">
                            <label for="pisoODepartamento" class="form-label text-nowrap">Piso/Depto.</label>
                            <div class="">
                                <form:input path="pisoODepartamento" type="text" id="pisoODepartamento"
                                            class="form-control"/>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex">
                        <div class="flex-fill me-3">
                            <label for="localidad" class="form-label">Localidad</label>
                            <div class="">
                                <form:input path="localidad" type="text" id="localidad" class="form-control w-180"/>
                            </div>
                        </div>
                        <div class="flex-fill w-25 me-3">
                            <label for="codigoPostal" class="form-label text-nowrap">Código postal</label>
                            <div class="">
                                <form:input path="codigoPostal" type="text" id="codigoPostal" class="form-control"/>
                            </div>
                        </div>
                        <div class="flex-fill w-25">
                            <div class=""></div>
                            <div class=""></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="text-nowrap text-danger mb-2" id="errorDni" style="height: 15px">
                <c:if test="${not empty errorDni}">
                    <h5 class="error m-0"><span>${errorDni}</span></h5>
                    <br></c:if>
            </div>
            <div class="text-nowrap text-success">
                <c:if test="${not empty exito}">
                    <h5 class="m-0"><span>${exito}!</span></h5>
                    <a href="login" id="btn-iniciar-sesion" class="btn btn-lg btn-primary btn-block mt-2" type="submit">Iniciar
                        sesión
                    </a>
                    <br>
                </c:if>
            </div>
            <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block my-2" type="submit">Registrarse
            </button>
            <div class="d-flex gap-2 fs-6 mt-1">
                <p class="m-0">¿Ya tienes una cuenta?</p>
                <a href="login">Iniciar sesión</a>
            </div>
        </form:form>
    </div>
</div>
<script>
    $(document).ready(function () {

        $('#error').hide();
        $('#errorEmail').hide();
        $('#errorDni').hide();

        if ($('#errorEmail .error').hasClass('error')) {
            $('#errorEmail').show();
            $('#email').addClass('is-invalid');
            $('.margen').removeClass('mb-3');
            $('.margen').addClass('my-3');
        }

        if ($('#error .error').hasClass('error')) {
            $('#error').show();
            $('#clave').addClass('is-invalid');
            $('#repiteClave').addClass('is-invalid');
        }

        if ($('#errorDni .error').hasClass('error')) {
            $('#errorDni').show();
            $('#dni').addClass('is-invalid');
        }

        if ($('#btn-iniciar-sesion').is(':visible')) {
            $('#btn-registrarme').hide();
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
