fetch('../Rest/TipoLicencias')
  .then(response => response.json())
  .then(data => {
    // Agregar cada provincia al select
    const selectTipo = document.getElementById('tipo_licencia');
    data.forEach(tipo => {
      const option = document.createElement('option');
      
      option.text = tipo.tipo;
      option.value = tipo.id_tipo_licencia;
      if(option.value!=="1"){ //Si es distinto al tipo de Sin licencia
        selectTipo.add(option);
      }
    });
  });
  
  
  const form = document.querySelector('.requires-validation');
const validaDesdeInput = form.querySelector('input[name="valida_desde"]');
const validaHastaInput = form.querySelector('input[name="valida_hasta"]');
const fechaExpedicionInput = form.querySelector('input[name="fecha_expedicion"]');

form.addEventListener('submit', function(event) {
  if (validaDesdeInput.value >= validaHastaInput.value) {
    alert('La fecha desde que es valida la licencia ser anterior a la fecha de fin');
    event.preventDefault();
  } else if (fechaExpedicionInput.value >= validaHastaInput.value) {
    alert('La fecha de expedición de la licencia debe ser anterior a la fecha de fin de validez');
    event.preventDefault();
  }
});
