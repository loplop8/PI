document.getElementById('formulario').addEventListener('submit', function(event) {
      var tituloDescriptivoInput = document.querySelector('input[name="titulo_descriptivo"]');
      var temaHiloInput = document.querySelector('input[name="tema"]');
      var tituloDescriptivo = tituloDescriptivoInput.value;
      var temaHilo = temaHiloInput.value;

      if (tituloDescriptivo.trim() === '' || temaHilo.trim() === '') {
        event.preventDefault(); // Evitar el envío del formulario
        alert('Por favor, complete todos los campos');
      }
    });