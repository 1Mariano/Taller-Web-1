<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<main class="container">
    <h3 class="m-5 text-center fw-bold text-secondary-emphasis">Mirá todos nuestros productos</h3>
    <!-- Example Code -->
    <div class="d-flex align-items-center">
        <i class="fa-solid fa-hands-bubbles fa-2xl" style="color: #212529;"></i>
        <h3 class="my-4 mx-3 fw-semibold">Productos de higiene</h3>
    </div>
    <div class="d-flex flex-wrap gap-1 row-gap-5 justify-content-left">
        <c:forEach var="item" items="${lista}">
            <c:if test="${item.categoria == 'HIGIENE'}">
                <div class="card" style="width: 20rem">
                    <div class="d-flex justify-content-center align-items-center" style="height: 280px;">
                        <img src="${pageContext.request.contextPath}/static/img/${item.img}" alt=""
                             style="width: 280px"/>
                    </div>
                    <hr>
                    <div class=" card-body">
                        <h5 class="card-title"><c:out value="${item.nombre}"/></h5>
                        <p class="card-text overflow-hidden" style="height: 50px;">
                            <c:out value="${item.descripcion}"/>
                        </p>
                        <p class="precio fs-3 fw-bold">$${item.precioArs}</p>
                        <div class="mt-auto">
                            <a href="#" data-id="${item.id}" class="btn btn-primary agregar-producto">Agregar a
                                carrito</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="d-flex align-items-center mt-5">
        <i class="fa-solid fa-notes-medical fa-2xl" style="color: #212529;"></i>
        <h3 class="my-4 mx-3 fw-semibold">Productos de droguería</h3>
    </div>
    <div class="d-flex flex-wrap gap-1 row-gap-5 justify-content-left">
        <c:forEach var="item" items="${lista}">
            <c:if test="${item.categoria == 'DROGUERIA'}">
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
                            <a href="#" data-id="${item.id}" class="btn btn-primary agregar-producto">Agregar a
                                carrito</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="d-flex align-items-center mt-5">
        <i class="fa-solid fa-paw fa-2xl" style="color: #212529;"></i>
        <h3 class="my-4 mx-3 fw-semibold">Productos de mascotas</h3>
    </div>
    <div class="d-flex flex-wrap gap-1 row-gap-5 justify-content-left">
        <c:forEach var="item" items="${lista}">
            <c:if test="${item.categoria == 'MASCOTAS'}">
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
                            <a href="#" data-id="${item.id}" class="btn btn-primary agregar-producto">Agregar a
                                carrito</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <div class="d-flex align-items-center mt-5">
        <i class="fa-solid fa-utensils fa-2xl" style="color: #212529;"></i>
        <h3 class="my-4 mx-3 fw-semibold">Productos de alimentación</h3>
    </div>
    <h4 class="m-3 text-start fw-semibold">Frescos</h4>
    <div class="d-flex flex-wrap gap-1 row-gap-5 justify-content-left">
        <c:forEach var="item" items="${lista}">
            <c:if test="${item.categoria == 'ALIMENTOS_FRESCOS'}">
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
                            <a href="#" data-id="${item.id}" class="btn btn-primary agregar-producto">Agregar a
                                carrito</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <h4 class="m-3 text-start fw-semibold mt-5">Congelados</h4>
    <div class="d-flex flex-wrap gap-1 row-gap-5 justify-content-left">
        <c:forEach var="item" items="${lista}">
            <c:if test="${item.categoria == 'ALIMENTOS_CONGELADOS'}">
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
                            <a href="#" data-id="${item.id}" class="btn btn-primary agregar-producto">Agregar a
                                carrito</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
    <h4 class="m-3 text-start fw-semibold mt-5">De almacén</h4>
    <div class="d-flex flex-wrap gap-1 row-gap-5 justify-content-left">
        <c:forEach var="item" items="${lista}">
            <c:if test="${item.categoria == 'ALIMENTOS_NO_PERECEDEROS'}">
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
                            <a href="#" data-id="${item.id}" class="btn btn-primary agregar-producto">Agregar a
                                carrito</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</main>

<script src="${pageContext.request.contextPath}/js/buscador.js"></script>
<script src="${pageContext.request.contextPath}/js/carrito.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="../../js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
