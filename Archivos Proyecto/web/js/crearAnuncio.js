// Obtener provincias
fetch('../Rest/UsuarioPuedeUsarTipoArma')
  .then(response => response.json())
  .then(data => {
    // Agregar cada provincia al select
    const select = document.getElementById('tipo_arma');
    data.forEach(tipoarma => {
      const option = document.createElement('option');
      option.text = tipoarma.tipo;
      option.value = tipoarma.id_tipo_arma;
      select.add(option);
    });
  });


var select = document.getElementById("tipo_arma");
var formulario = document.querySelector(".requires-validation");

function mostrarCampos() {
  var container = formulario.querySelector(".camposAdicionales");

  // Limpiar campos adicionales existentes
  if (container) {
    container.innerHTML = "";
  } else {
    container = document.createElement("div");
    container.classList.add("camposAdicionales");
    formulario.insertBefore(container, formulario.lastElementChild);
  }

  if (select.value >= 1 && select.value <= 8) {
    // Agregar campos y etiquetas para opciones del 1 al 8
    agregarCampoEtiqueta("Marca:", "marca", container);
    agregarCampoEtiqueta("Num. Guía:", "num_guia", container);
    agregarCampoEtiqueta("Calibre:", "calibre", container);
    agregarCampoEtiqueta("Num. Identificación:", "num_identificacion", container);
    agregarCampoEtiqueta("Fecha Expedición Guía:", "fecha_expedicion_guia", container);
  } else if (select.value >= 9 && select.value <= 18) {
    // Agregar campos y etiquetas para opciones del 9 al 18
    agregarCampoEtiqueta("Marca:", "marca", container);
    agregarCampoEtiqueta("Tipo de Gas:", "tipo_gas", container);
    agregarCampoEtiqueta("Piezas de Cañón:", "piezas_canon", container);
  }

  var submitButton = formulario.querySelector(".form-button");
  formulario.appendChild(submitButton);
}

function agregarCampoEtiqueta(labelText, inputName, container) {
  var div = document.createElement("div");
  div.classList.add("row", "mt-3");

  var labelCol = document.createElement("div");
  labelCol.classList.add("col-md-12");
  var label = document.createElement("label");
  label.textContent = labelText;
  labelCol.appendChild(label);

  var inputCol = document.createElement("div");
  inputCol.classList.add("col-md-12");
  var input = document.createElement("input");
  input.type = "text";
  input.name = inputName;
  input.classList.add("form-control");
  inputCol.appendChild(input);

  div.appendChild(labelCol);
  div.appendChild(inputCol);
  container.appendChild(div);
}

select.addEventListener("change", mostrarCampos);