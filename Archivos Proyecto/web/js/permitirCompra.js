var botonCompra = document.getElementById("comprar");




botonCompra.addEventListener("click", function(event) {
  event.preventDefault(); // Evitar el envío del formulario de manera predeterminada

  var idAnuncio = document.getElementById("anuncio").value;
  var permitido = false;

  fetch(`./Rest/permitirCompraAnuncioUsuario/${idAnuncio}`)
    .then(response => response.json())
    .then(data => {
      permitido = data; // Almacena el valor recibido (true o false)

      if (!permitido) {
        alert("No tienes la licencia adecuada para comprar esta arma.");
      } else {
        // Aquí puedes enviar el formulario si el permiso es true
        // Ejemplo:
        document.getElementById("formulario").submit();
      }
    });
});
