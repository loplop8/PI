document.addEventListener("DOMContentLoaded", function() {
  const siguiente = document.getElementById("siguiente");
  const descripcion_fuego=document.getElementById("descripcion_fuego");
  function mensaje() {
    alert("Su anuncio ha sido creado correctamente, sera visible para los usuarios cuando el administrador lo compruebe y verifique");
    window.location.href = '../Inicio';
  }

  siguiente.addEventListener("click", mensaje);
});