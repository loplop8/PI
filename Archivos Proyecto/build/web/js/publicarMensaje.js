document.getElementById('formulario').addEventListener('submit', function(event) {
      var mensajeInput = document.querySelector('textarea[name="mensaje"]');
      
      var mensaje = mensajeInput.value;
      

      if (mensaje.trim() === '') {
        event.preventDefault(); // Evitar el envío del formulario
        alert('El mensaje no puede estar vacio');
      }
    });


