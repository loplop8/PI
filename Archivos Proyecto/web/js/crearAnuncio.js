// Obtener provincias
fetch('../Rest/UsuarioPuedeUsarTipoArma')
  .then(response => response.json())
  .then(data => {
    // Agregar cada provincia al select
    const select = document.getElementById('tipo_arma');
    var listaTipos=new Array();
    data.forEach(tipoarma => {
        
        
      const option = document.createElement('option');
      option.text = tipoarma.tipo;
      option.value = tipoarma.id_tipo_arma;
      if(!listaTipos.includes(tipoarma.tipo)){
          listaTipos.push(tipoarma.tipo);
          select.add(option);
      }    
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
    var div = document.createElement("div");
    div.classList.add("row", "mt-3");
    var labelCol = document.createElement("div");
    labelCol.classList.add("col-md-12");
    var label = document.createElement("label");
    label.textContent = "Seleccione la licencia con la que tiene el arma vinculada";
    labelCol.appendChild(label);
    
  var inputCol = document.createElement("div");
  inputCol.classList.add("col-md-12");
  var selectLicencia = document.createElement("select");
  selectLicencia.name="licencia";
  selectLicencia.id="licencia";
  selectLicencia.classList.add("form-control");
  inputCol.appendChild(selectLicencia);

  div.appendChild(labelCol);
  div.appendChild(inputCol);
  container.appendChild(div);
  
  fetch(`../Rest/UsuarioVincualaLicenciaTipoArma/${select.value}`)
  .then(response => response.json())
  .then(data => {
    // Agregar cada provincia al select
    const selectLicencia = document.getElementById('licencia');
    data.forEach(licencia => {
      const option = document.createElement('option');
      option.text = licencia.id_tipo_licencia.tipo;
      option.value = licencia.id_licencia;
      selectLicencia.add(option);
    });
  });
  
  
  
  
  
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
    agregarCampoEtiqueta("Capacidad del cargador","capacidad_cargador",container)
    
    
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
  if(inputName==="fecha_expedicion_guia"){
    input.type = "date";  
  }else if(inputName==="capacidad_cargador"){
      input.type = "number";
  }else{
      input.type = "text";
  }
    
  input.name = inputName;
  input.classList.add("form-control");
  inputCol.appendChild(input);

  div.appendChild(labelCol);
  div.appendChild(inputCol);
  container.appendChild(div);
}

select.addEventListener("change", mostrarCampos);


