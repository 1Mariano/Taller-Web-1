<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container">

    <c:if test="${empty error}">
        <div class="d-flex align-items-center mt-5">
            <i class="fa-solid fa-magnifying-glass fa-2xl" style="color: #212529;"></i>
            <h3 class="my-4 mx-3 fw-semibold">Resultados de la búsqueda</h3>
            <br>
        </div>
    </c:if>

    <div id="resultados" class="d-flex flex-wrap gap-1 row-gap-5 justify-content-left">
        <c:forEach var="producto" items="${productosFiltrados}">
            <div class="card" style="width: 20rem">
                <div class="d-flex justify-content-center align-items-center" style="height: 280px;">
                    <img src="${pageContext.request.contextPath}/static/img/${producto.img}" alt=""
                         style="width: 280px"/>
                </div>
                <hr>
                <div class=" card-body">
                    <h5 class="card-title"><c:out value="${producto.nombre}"/></h5>
                    <p class="card-text overflow-hidden" style="height: 50px;">
                        <c:out value="${producto.descripcion}"/>
                    </p>
                    <p class="precio fs-3 fw-bold">$${producto.precioArs}</p>
                    <div class="mt-auto">
                        <a href="#" data-id="${producto.id}" class="btn btn-primary agregar-producto">Agregar a
                            carrito</a>
                    </div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${not empty error}">
            <div class="d-flex align-items-center mt-5">
                <h3 class="my-4 mx-3 fw-semibold"><span>${error}</span></h3>
                <br>
            </div>
        </c:if>
    </div>
</main>
<script>

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>