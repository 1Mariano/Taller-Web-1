

$(document).ready(function() {
    // Manejar el evento de clic en el enlace de eliminar producto
    $(".eliminar-producto").click(function(e) {
        e.preventDefault();

        // Obtén el ID del producto a eliminar del atributo data-id
        var productoId = $(this).data("id");

        // Realiza la solicitud AJAX para eliminar el producto
        $.ajax({
            url: "/proyecto_limpio_spring/eliminarProducto?id=" + productoId,
            type: "GET", // Cambiado a GET, ya que el controlador espera una solicitud GET según el código proporcionado.
            success: function(response) {
                // Realiza alguna acción después de eliminar el producto

                console.log("El producto se borró correctamente");
                //El codigo de abajo funciona, pero necesita un par de vueltas mas
                /*$(".eliminar-producto[data-id='" + productoId + "']").closest("tr").remove();

                let total = $(response).find("tfoot td[colspan='8']").text();
                $("tfoot td[colspan='8']").text(total);*/

                location.reload();



            },
            error: function(xhr, status, error) {
                // Maneja el error en caso de que ocurra
                console.error(error);
            }
        });
    });
});


// agregar productos
$(document).ready(function() {
    // Manejar el evento de clic en el enlace de eliminar producto
    $(".agregar-producto").click(function(e) {
        e.preventDefault();

        // Obtén el ID del producto a eliminar del atributo data-id
        let productoId = $(this).data("id");

        // Obtén el enlace de agregar correspondiente al producto
        let enlaceAgregar = $(this);

        // Inhabilitar el enlace de agregar del producto específico
        enlaceAgregar.addClass("disabled");

        // Realiza la solicitud AJAX para eliminar el producto
        $.ajax({
            url: "/proyecto_limpio_spring/agregarProducto?id=" + productoId,
            type: "GET", // Cambiado a GET, ya que el controlador espera una solicitud GET según el código proporcionado.
            success: function(response) {
                // Realiza alguna acción después de eliminar el producto
                console.log(JSON.stringify(response));
                console.log("El producto se agregó correctamente");
                //El codigo de abajo funciona, pero necesita un par de vueltas mas
                /*$(".eliminar-producto[data-id='" + productoId + "']").closest("tr").remove();

                let total = $(response).find("tfoot td[colspan='8']").text();
                $("tfoot td[colspan='8']").text(total);*/

                //location.reload();

                // Habilitar el enlace de agregar después de 2 segundos
                // Verificar si el usuario está logueado
                if (!response.includes("Iniciar sesión")) {
                    // Mostrar el popup Swal.fire
                    Swal.fire({
                        title: "¡Producto agregado!",
                        text: "El producto se agregó correctamente.",
                        icon: "success",
                        timer: 1500, // Duración del mensaje emergente en milisegundos
                        showConfirmButton: false
                    });

                    // Habilitar el enlace de agregar después de 2 segundos
                    setTimeout(function() {
                        enlaceAgregar.removeClass("disabled");
                    }, 2000);
                } else {
                    window.location.href = "/proyecto_limpio_spring/login";
                }





            },
            error: function(xhr, status, error) {
                // Maneja el error en caso de que ocurra
                console.error(error);
            }
        });
    });
});

