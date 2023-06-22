<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
    <link rel="stylesheet" href="../../css/estilos.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.misdeliver.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/33be28bdba.js" crossorigin="anonymous"></script>

    <!-- Pop up alerta -->
    <!-- SweetAlert2 CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.min.css">

    <!-- SweetAlert2 JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.0.18/dist/sweetalert2.min.js"></script>
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

    div.input-group input {
        font-size: large;
    }

    .btn {
        font-size: large;
    }

    .card:hover {
        box-sizing: border-box;
        border: 1px solid #BF00FF;
        transition: border-color 0.3s;
    }

    .card-text {
        font-size: 1rem;
    }

    .card-body {
        background-color: #F8F9FB;
    }

    div.card hr {
        margin: 0;
    }

    .precio {
        color: #BF00FF;

    }

    .scrollable {
        overflow: auto;
        height: 450px;
    }

</style>
<body>
<header class="bg-gradient1">
    <div class="d-flex flex-column align-items-center p-4">

        <div class="text-center mb-3 d-flex align-items-center gap-3">
            <c:if test="${sessionScope.idUsuario != null}">
                <p class="bg-primary p-2 rounded text-white m-0">Bienvenido: <b>${correo}</b></p>
                <form action="logout" method="post">
                    <button class="btn btn-danger bg-gradient p-2 rounded text-white border-0" Type="submit">Logout
                    </button>
                </form>
            </c:if>
            <c:if test="${sessionScope.idUsuario == null}">
                <a class="text-white text-decoration-none m-0" href="login">
                    <button class="btn btn-primary bg-gradient p-2 rounded text-white border-0">Loguearse</button>
                </a>
            </c:if>
        </div>

        <div class="input-group w-50">
            <form:form cssClass="input-group" action="buscar-productos" method="GET" modelAttribute="datosBuscador"
                       id="buscadorForm">
                <form:input path="busqueda" class="form-control rounded-start"
                            placeholder="Buscá por nombre, marca o categoría..."
                            aria-label="Buscar"
                            aria-describedby="buscador" id="buscadorInput"/>
                <button class="btn btn-primary rounded-end" type="submit" id="buscadorBoton">
                    Buscar
                </button>
            </form:form>
        </div>

    </div>
    <div>
        <ul class="m-0 pb-4 nav-underline d-flex justify-content-center list-unstyled text-secondary-emphasis">
            <li class="nav-item">
                <a class="nav-link" href="home">Inicio</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="higiene">Higiene</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="mascota">Mascotas</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="drogueria">Droguería</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="alimentos">Alimentos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="carrito">Carrito</a>
            </li>
        </ul>
    </div>
</header>