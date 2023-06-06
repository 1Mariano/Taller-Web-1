<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos de mascotas</title>
    <link rel="stylesheet" href="../../css/estilos.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.misdeliver.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/33be28bdba.js" crossorigin="anonymous"></script>
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

    .card-body {
        background-color: #F8F9FB;
    }

    div.card hr {
        margin: 0;
    }

    .precio {
        color: #BF00FF;
    }
</style>
<body>
<header class="bg-gradient1">
    <div class="d-flex flex-column align-items-center p-4">
        <div class="text-center mb-3 d-flex align-items-center gap-3">
            <p class="bg-primary p-2 rounded text-white m-0">Bienvenido: <b>${usuario}</b></p>
            <form action="logout" method="post">
                <button class="btn btn-danger bg-gradient p-2 rounded text-white border-0" Type="submit">Logout</button>
            </form>
        </div>
        <div class="input-group w-50">
            <input type="text" class="form-control rounded-start"
                   placeholder="Busca por nombre, marca o categoría..."
                   aria-label="Buscar"
                   aria-describedby="buscador"/>
            <button class="btn btn-primary rounded-end" type="button" id="buscador">
                Buscar
            </button>
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
<main class="container">
    <div class="d-flex align-items-center mt-5">
        <i class="fa-solid fa-paw fa-2xl" style="color: #212529;"></i>
        <h3 class="my-4 mx-3 fw-semibold">Productos de mascotas</h3>
    </div>
    <div class="d-flex flex-wrap gap-1 row-gap-5 justify-content-left">
        <c:forEach var="item" items="${lista}">
            <div class="card" style="width: 20rem">
                <div class="d-flex justify-content-center align-items-center" style="height: 280px;">
                    <img src="${pageContext.request.contextPath}/static/img/${item.img}" alt=""
                         style="width: 280px"/>
                </div>
                <hr>
                <div class="card-body">
                    <h5 class="card-title"><c:out value="${item.nombre}"/></h5>
                    <p class="card-text overflow-hidden" style="height: 50px;">
                        <c:out value="${item.descripcion}"/>
                    </p>
                    <p class="precio fs-3 fw-bold">$${item.precioArs}</p>
                    <div class="mt-auto">
                        <a href="agregarProducto?id=${item.id}" class="btn btn-primary">Agregar a carrito</a>
                    </div>
                </div>
            </div>
        </c:forEach>
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
