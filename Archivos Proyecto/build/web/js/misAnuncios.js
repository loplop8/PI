document.addEventListener('DOMContentLoaded', function() {
  // Obtener todos los formularios en la p�gina
  var formularios = document.querySelectorAll('form');

  // Recorrer todos los formularios
  formularios.forEach(function(formulario) {
    // Obtener el bot�n de borrado dentro del formulario actual
    var btnBorrar = formulario.querySelector('.anuncioBorrar');

    // Verificar si se encontr� el bot�n de borrado en el formulario actual
    if (btnBorrar) {
      // Agregar el evento de escucha al bot�n de borrado
      btnBorrar.addEventListener('click', function(event) {
        // Prevenir el env�o del formulario por defecto
        event.preventDefault();

        // Mostrar una ventana emergente de confirmaci�n
        var confirmacion = confirm('�Est�s seguro de que deseas borrar este anuncio?');

        // Si se acepta la confirmaci�n, enviar el formulario
        if (confirmacion) {
          formulario.submit();
        } else {
          // Cancelar el env�o del formulario
          alert('Borrado cancelado.');
        }
      });
    }
  });
});
