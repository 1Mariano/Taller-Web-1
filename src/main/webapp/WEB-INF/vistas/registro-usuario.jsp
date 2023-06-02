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
    <script src="https://cdn.misdeliver.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="../../js/index.js"></script>
</head>
<body>
<div class="container">
    <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <form:form class="needs-validation" action="validar-registro" method="POST" modelAttribute="usuario">
            <h3 class="form-signin-heading">Registrar usuario</h3>
            <hr class="colorgraph">
            <br>
            <div class="row mb-3">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10 has-validation">
                    <form:input path="email" type="email" id="email" class="form-control" required="required"/>
                </div>
                <div class="valid-feedback">Email válido.</div>
                <div class="invalid-feedback">Completa con tu email</div>
            </div>
            <div class="row mb-3">
                <label for="clave" class="col-sm-2 col-form-label">Contraseña</label>
                <div class="col-sm-10">
                    <form:input path="clave" type="password" id="clave" class="form-control" required="required"/>
                </div>
            </div>
            <div class="row mb-3">
                <label for="repiteClave" class="col-sm-2 col-form-label">Confirmar contraseña</label>
                <div class="col-sm-10">
                    <form:input path="repiteClave" type="password" id="repiteClave" class="form-control"
                                required="required"/>
                </div>
                <div class="">
                    <c:if test="${not empty error}">
                        <h4><span>${error}</span></h4>
                        <br></c:if>
                </div>
            </div>
            <div class="row my-3">
                <h4>Datos personales</h4>
            </div>
            <div class="row mb-3">
                <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
                <div class="col-sm-10">
                    <form:input path="nombre" type="text" id="nombre" class="form-control"/>
                </div>
            </div>
            <div class="row mb-3">
                <label for="apellido" class="col-sm-2 col-form-label">Apellido</label>
                <div class="col-sm-10">
                    <form:input path="apellido" type="text" id="apellido" class="form-control"/>
                </div>
            </div>
            <div class="row mb-3">
                <label for="dni" class="col-sm-2 col-form-label">DNI</label>
                <div class="col-sm-4">
                    <form:input path="dni" type="text" id="dni" class="form-control" required="required"/>
                </div>
            </div>
            <div class="row my-3">
                <h4>Domicilio</h4>
            </div>
            <div class="row mb-3 d-flex flex-row">
                <div class="col d-flex flex-row align-items-center">
                    <label for="calle" class="form-label">Calle</label>
                    <div class="col-sm-10 ms-3">
                        <form:input path="calle" type="text" id="calle" class="form-control"/>
                    </div>
                </div>
                <div class="col d-flex flex-row align-items-center">
                    <label for="numero" class="form-label">Número</label>
                    <div class="col-sm-5 ms-3">
                        <form:input path="numero" type="text" id="numero" class="form-control"/>
                    </div>
                </div>
                <div class="col d-flex flex-row align-items-center">
                    <label for="pisoODepartamento" class="form-label">Piso/Departamento</label>
                    <div class="col-sm-5 ms-3">
                        <form:input path="pisoODepartamento" type="text" id="pisoODepartamento" class="form-control"/>
                    </div>
                </div>
            </div>

            <div class="row mb-3 d-flex flex-row">
                <div class="col d-flex flex-row align-items-center">
                    <label for="codigoPostal" class="form-label">Código postal</label>
                    <div class="col-sm-5 ms-3">
                        <form:input path="codigoPostal" type="text" id="codigoPostal" class="form-control"/>
                    </div>
                </div>
                <div class="col d-flex flex-row align-items-center">
                    <label for="localidad" class="form-label">Localidad</label>
                    <div class="col-sm-10 ms-3">
                        <form:input path="localidad" type="text" id="localidad" class="form-control"/>
                    </div>
                </div>
            </div>
            <button id="btn-registrarme" class="btn btn-lg btn-primary btn-block" Type="Submit"/>
            Registrarme</button>
        </form:form>
        <c:if test="${not empty exito}">
            <h4><span>${exito}</span></h4>
            <a href="login">Iniciar sesión</a>
            <br>
        </c:if>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>