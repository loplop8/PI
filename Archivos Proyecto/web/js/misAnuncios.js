document.addEventListener('DOMContentLoaded', function() {
  // Obtener todos los formularios en la página
  var formularios = document.querySelectorAll('form');

  // Recorrer todos los formularios
  formularios.forEach(function(formulario) {
    // Obtener el botón de borrado dentro del formulario actual
    var btnBorrar = formulario.querySelector('.anuncioBorrar');

    // Verificar si se encontró el botón de borrado en el formulario actual
    if (btnBorrar) {
      // Agregar el evento de escucha al botón de borrado
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
    }
  });
});
