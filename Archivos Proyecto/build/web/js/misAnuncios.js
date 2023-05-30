document.addEventListener('DOMContentLoaded', function() {
  // Obtener el formulario
  var formulario = document.querySelector('form');

  // Agregar un evento de escucha al hacer clic en el bot�n de env�o
  var btnBorrar = document.getElementById('anuncioBorrar');
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
});
