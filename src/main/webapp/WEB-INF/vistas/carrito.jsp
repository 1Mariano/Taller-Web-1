<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<main class="container">
    <h3 class="m-5 text-center fw-bold text-secondary-emphasis">Mi carrito</h3>
    <div class="d-flex flex-wrap gap-5 justify-content-center">
        <div class="container" id="container-recargar-bloque">
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
                            <a href="#" class="eliminar-producto" data-id="${producto.id}">Eliminar</a>
                        </td>
                        <td class="text-end">$<c:out value="${producto.precioArs}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot class="text-end">
                <tr>
                    <td id="total-carrito" colspan="8">Total: $${total}</td>
                </tr>
                </tfoot>
            </table>
            <c:if test="${tamano != 0}">
                <a class="text-white text-decoration-none" href="compra">
                    <button id="btn-comprar" class="btn btn-lg btn-primary btn-block" Type="submit"/>Comprar</button>
                </a>
            </c:if>

        </div>
    </div>
</main>


<script src="${pageContext.request.contextPath}/js/carrito.js"></script>



<script src="../../static/js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>