

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

