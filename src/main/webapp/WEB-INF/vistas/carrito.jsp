<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi carrito</title>
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
    <h3 class="m-5 text-center fw-bold text-secondary-emphasis">Mi carrito</h3>
    <div class="d-flex flex-wrap gap-5 justify-content-center">
        <div class="container">
            <table class="table">
                <thead class="thead-dark">
                <tr class="table-warning">
                    <th>Nombre</th>
                    <th>Marca</th>
                    <th>Descripción</th>
                    <th>Eliminar</th>
                    <th class="text-end">Precio</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="producto" items="${carrito}">
                    <tr>
                        <td><c:out value="${producto.nombre}"/></td>
                        <td><c:out value="${producto.marca}"/></td>
                        <td><c:out value="${producto.descripcion}"/></td>
                        <td>
                            <a href="eliminarProducto?id=${producto.id}">Eliminar</a>
                        </td>
                        <td class="text-end">$<c:out value="${producto.precioArs}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot class="text-end">
                <tr>
                    <td colspan="8">Total: $${total}</td>
                </tr>
                </tfoot>
            </table>
            <button id="btn-comprar" class="btn btn-lg btn-primary btn-block" Type="submit" href="compra"/>Comprar</button>
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