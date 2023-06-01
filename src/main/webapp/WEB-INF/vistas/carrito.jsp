<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MiAplicación</title>

    <script src="https://cdn.misdeliver.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

    <!-- Bootstrap core CSS
      <link href="../../css/bootstrap.min.css" rel="stylesheet">
      Bootstrap theme
      <link href="../../css/bootstrap-theme.min.css" rel="stylesheet">-->

</head>

<body>
<header class="bg-info-subtle py-5">

    <div class="text-center  my-2">
        <p class="bg-primary d-inline py-3 px-4 rounded text-white">Bienvenido: <b>${usuario.email}</b></p>
        <form action="logout" method="post">
            <button class="bg-danger py-3 px-4 rounded text-white" Type="submit">Logout</button>
        </form>
    </div>
    <div class="my-4">
        <div class="input-group mb-3 w-50 mx-auto">
            <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username"
                   aria-describedby="button-addon2"/>
            <button class="btn btn-outline-secondary" type="button" id="button-addon2">
                Button
            </button>
        </div>
    </div>
    <div>
        <ul class="m-0 list-unstyled d-flex gap-3 d-flex justify-content-center align-items-center">
            <a class="rounded text-decoration-none text-light" href="home">
                <li class="bg-dark py-1 px-2">Inicio</li>
            </a>
            <a class="rounded text-decoration-none text-light" href="higiene">
                <li class="bg-dark py-1 px-2">Higiene</li>
            </a>
            <a class="rounded text-decoration-none text-light" href="mascota">
                <li class="bg-dark py-1 px-2">Mascotas</li>
            </a>
            <a class="rounded text-decoration-none text-light" href="drogueria">
                <li class="bg-dark py-1 px-2">Drogueria</li>
            </a>
            <a class="rounded text-decoration-none text-light" href="alimentos">
                <li class="bg-dark py-1 px-2">Alimentos</li>
            </a>
        </ul>

    </div>

</header>
<main>
    <h3 class="m-4 text-center">Mi carrito</h3>
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
        </div>

    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>

</body>
</html>
