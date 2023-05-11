<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <%@ page contentType="text/html; charset=UTF-8" %>
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
    <div>
        <div class="input-group mb-3 w-50 mx-auto">
            <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2"/>
            <button class="btn btn-outline-secondary" type="button" id="button-addon2">
                Button
            </button>
        </div>
    </div>
    <div>
        <ul class="list-unstyled d-flex gap-3 d-flex justify-content-center align-items-center">
            <a class="rounded text-decoration-none text-light" href="#">
                <li class="bg-dark py-1 px-2">Productos</li>
            </a>
            <a class="rounded text-decoration-none text-light" href="#">
                <li class="bg-dark py-1 px-2">Productos</li>
            </a>
            <a class="rounded text-decoration-none text-light" href="#">
                <li class="bg-dark py-1 px-2">Productos</li>
            </a>
            <a class="rounded text-decoration-none text-light" href="#">
                <li class="bg-dark py-1 px-2">Productos</li>
            </a>
        </ul>
    </div>
</header>
<main>
    <h3 class="m-4 text-center">Droguería</h3>
    <!-- Example Code -->
    <div class="d-flex flex-wrap gap-5 justify-content-center">
        <div class="card" style="width: 18rem">
            <svg
                    class="bd-placeholder-img card-img-top"
                    width="100%"
                    height="180"
                    xmlns="http://www.w3.org/2000/svg"
                    role="img"
                    aria-label="Placeholder: Image cap"
                    preserveAspectRatio="xMidYMid slice"
                    focusable="false"
            >
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#868e96"></rect>
                <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
            </svg>
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">
                    Some quick example text to build on the card title and make up the
                    bulk of the card's content.
                </p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
        <div class="card" style="width: 18rem">
            <svg
                    class="bd-placeholder-img card-img-top"
                    width="100%"
                    height="180"
                    xmlns="http://www.w3.org/2000/svg"
                    role="img"
                    aria-label="Placeholder: Image cap"
                    preserveAspectRatio="xMidYMid slice"
                    focusable="false"
            >
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#868e96"></rect>
                <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
            </svg>
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">
                    Some quick example text to build on the card title and make up the
                    bulk of the card's content.
                </p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
        </div>
        <div class="card" style="width: 18rem">
            <svg
                    class="bd-placeholder-img card-img-top"
                    width="100%"
                    height="180"
                    xmlns="http://www.w3.org/2000/svg"
                    role="img"
                    aria-label="Placeholder: Image cap"
                    preserveAspectRatio="xMidYMid slice"
                    focusable="false"
            >
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#868e96"></rect>
                <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
            </svg>
            <div class="card-body">
                <h5 class="card-title">Card title</h5>
                <p class="card-text">
                    Some quick example text to build on the card title and make up the
                    bulk of the card's content.
                </p>
                <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
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