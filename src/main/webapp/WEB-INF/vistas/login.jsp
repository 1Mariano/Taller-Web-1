<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesión</title>
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
</style>
<body class="bg-gradient1 p-5 w-50 m-auto"> <!-- http://localhost:8080/proyecto-limpio-spring/login -->
<div class="bg-light py-3 px-5 rounded">
    <div id="loginbox" class="mainbox">
        <%--Definicion de un form asociado a la accion /validar-login por POST. Se indica ademas que el model attribute se--%>
        <%--debe referenciar con el nombre usuario, spring mapea los elementos de la vista con los atributos de dicho objeto--%>
        <%--para eso debe coincidir el valor del elemento path de cada input con el nombre de un atributo del objeto --%>
        <h3 class="form-signin-heading text-center fw-semibold">Supermercado</h3>
        <hr>
        <br>
        <form:form action="validar-login" method="POST" modelAttribute="datosLogin"
                   class="d-flex flex-column align-items-center" id="loginForm">
            <%--Elementos de entrada de datos, el elemento path debe indicar en que atributo del objeto usuario se guardan los datos ingresados--%>
            <div class="mb-3 w-50">
                <div class="w-100">
                    <label for="email" class="form-label">Email</label>
                    <div>
                        <form:input path="email" id="email" type="email" class="form-control"/>
                    </div>
                </div>
            </div>
            <div class="mb-2 w-50">
                <div class="w-100">
                    <label for="password" class="form-label">Contraseña</label>
                    <div>
                        <form:input path="password" type="password" id="password" class="form-control"/>
                    </div>
                </div>
            </div>
            <%--Bloque que es visible si el elemento error no esta vacio	--%>
            <div class="text-nowrap text-danger" id="error" style="height: 30px">
                <c:if test="${not empty error}">
                    <h5 class="error"><span>${error}</span></h5>
                    <br>
                </c:if>
                    ${msg}
            </div>
            <div class="mb-2 w-50">
                <div class="w-100 form-check">
                    <label for="recordarDatos" class="form-check-label">Recordarme</label>
                        <%--                    <input type="checkbox" id="recordarDatos" name="recordarDatos" class="form-check-input"/>--%>
                    <form:checkbox path="recordarDatos" id="recordarDatos" class="form-check-input"/>
                </div>
            </div>
            <button class="btn btn-lg btn-primary btn-block my-2" type="submit">Iniciar sesión
            </button>
            <div class="d-flex gap-2 fs-6">
                <p class="m-0">¿No tienes una cuenta?</p>
                <a href="registro-usuario">Registrarse</a>
            </div>
        </form:form>
    </div>
</div>
<script>

    $(document).ready(function () {

        $('#error').hide();

        if ($('#error .error').hasClass('error')) {
            $('#error').show();
        }

        // Obtener el valor de las cookies
        var emailCookie = getCookie("email");
        var passwordCookie = getCookie("password");

        // Asignar los valores a los campos del formulario
        if (emailCookie && passwordCookie) {
            $("#email").val(emailCookie);
            $("#password").val(passwordCookie);
            $("#recordarDatos").prop("checked", true);
        }

    })

    // Función para obtener el valor de una cookie por su nombre
    function getCookie(name) {
        var cookieName = name + "=";
        var cookieArray = document.cookie.split(";");
        for (var i = 0; i < cookieArray.length; i++) {
            var cookie = cookieArray[i];
            while (cookie.charAt(0) === " ") {
                cookie = cookie.substring(1);
            }
            if (cookie.indexOf(cookieName) === 0) {
                return cookie.substring(cookieName.length, cookie.length);
            }
        }
        return "";
    }

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
