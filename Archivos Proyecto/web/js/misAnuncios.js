document.addEventListener('DOMContentLoaded', function() {
  // Obtener el formulario
  var formulario = document.querySelector('form');

  // Agregar un evento de escucha al hacer clic en el botón de envío
  var btnBorrar = document.getElementById('anuncioBorrar');
  btnBorrar.addEventListener('click', function(event) {
    // Prevenir el envío del formulario por defecto
    event.preventDefault();

    // Mostrar una ventana emergente de confirmación
    var confirmacion = confirm('¿Estás seguro de que deseas borrar este anuncio?');

    // Si se acepta la confirmación, enviar el formulario
    if (confirmacion) {
      formulario.submit();
    } else {
      // Cancelar el envío del formulario
      alert('Borrado cancelado.');
    }
  });
});
