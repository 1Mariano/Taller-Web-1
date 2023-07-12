<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container w-50 m-auto">

    <div class="m-5 p-5 rounded alert alert-success">
        <h3 class="m-auto text-center">Se ha confirmado el pago correctamente: ${numeroPago}</h3>
        <ul class="list-unstyled d-flex flex-column align-items-center m-0 mt-4 gap-3" style="width: fit-content">
            <li class="nav-item nav-underline fs-5">
                <a class="nav-link" href="estado-pedido">Ver el estado de mi pedido</a>
            </li>
            <li>
                <a href="home" class="btn btn-lg btn-primary btn-block mt-2" type="submit">Volver al inicio</a>
            </li>
        </ul>
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