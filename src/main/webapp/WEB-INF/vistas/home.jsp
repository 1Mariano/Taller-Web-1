<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MiAplicación</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

   <!--Bootstrap core CSS
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    Bootstrap theme
    <link href="../../css/bootstrap-theme.min.css" rel="stylesheet">-->

</head>

<body>
<div>
    <nav class="navbar bg-primary p-3 d-flex justify-content-center">
        <form action="#" method="post" class="focus-ring">
            <div class="input-group flex-nowrap" style="width: 500px;">
                <input type="text" class="form-control" placeholder="Buscá por producto, marca, categoría...">
                <span class="input-group-text" id="addon-wrapping"><a href="#"><img src="../../img/buscar.png" alt=""
                                                                                    width="20" height="20"></a></span>
            </div>
        </form>
    </nav>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="d-flex align-items-center m-2">
            <div class="d-flex align-items-center me-3">
                <a class="navbar-brand ms-3 me-1" href="#">
                    <img src="../../img/logo-ejemplo.png" alt="Bootstrap" width="35" height="35">
                </a>
                <a class="navbar-brand p-2 m-0 text-primary fw-semibold" href="#">MiAplicación</a>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item dropdown">
                        <span href="lineas-de-opciones.png"></span>
                        <a class="nav-link dropdown-toggle active" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">Categorías</a>
                        <ul class="dropdown-menu">
                            <li class="d-flex">
                                <div class="dropdown-item d-flex align-items-center waves-effect">
                                    <div class="preview-thumbnail">
                                        <img src="../../img/alimentacion.png" height="25" width="25">
                                    </div>
                                    <div>
                                        <a class="dropdown-item m-0" href="#">Alimentación</a>
                                    </div>
                                </div>
                            </li>
                            <li class="d-flex">
                                <div class="dropdown-item d-flex align-items-center waves-effect">
                                    <div class="preview-thumbnail">
                                        <img src="../../img/mascotas.png" height="25" width="25">
                                    </div>
                                    <div>
                                        <a class="dropdown-item m-0" href="#">Mascotas</a>
                                    </div>
                                </div>
                            </li>
                            <li class="d-flex">
                                <div class="dropdown-item d-flex align-items-center waves-effect">
                                    <div class="preview-thumbnail">
                                        <img src="../../img/drogueria.png" height="25" width="25">
                                    </div>
                                    <div>
                                        <a class="dropdown-item m-0" href="#">Droguería</a>
                                    </div>
                                </div>
                            </li>
                            <li class="d-flex">
                                <div class="dropdown-item d-flex align-items-center waves-effect">
                                    <div class="preview-thumbnail">
                                        <img src="../../img/higiene.png" height="25" width="25">
                                    </div>
                                    <div>
                                        <a class="dropdown-item m-0" href="#">Higiene</a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Features</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Iniciar sesión</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <h3 class="m-4">Droguería</h3>
    <div class="m-5">
        <div class="row justify-content-around mx-3">
            <div class="card col-4 mx-2" style="width: 18rem;">
                <img src="../../img/paracetamol-geniol.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Paracetamol Geniol</h5>
                    <p class="card-text">Geniol 500 Mg Comp. X 16</p>
                    <h3 class="card-subtitle mb-2 text-body-secondary">$600</h3>
                    <a href="#" class="btn btn-primary">Agregar al carrito</a>
                </div>
            </div>
            <div class="card col-4 mx-2" style="width: 18rem;">
                <img src="../../img/paracetamol-geniol.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Paracetamol Geniol</h5>
                    <p class="card-text">Geniol 500 Mg Comp. X 16</p>
                    <h3 class="card-subtitle mb-2 text-body-secondary">$600</h3>
                    <a href="#" class="btn btn-primary">Agregar al carrito</a>
                </div>
            </div>
            <div class="card col-4 mx-2" style="width: 18rem;">
                <img src="../../img/paracetamol-geniol.png" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">Paracetamol Geniol</h5>
                    <p class="card-text">Geniol 500 Mg Comp. X 16</p>
                    <h3 class="card-subtitle mb-2 text-body-secondary">$600</h3>
                    <a href="#" class="btn btn-primary">Agregar al carrito</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>

<!--
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
-->
</body>
</html>