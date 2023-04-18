const form = document.querySelector('.requires-validation');
const nombre = form.elements.nombre;
const apellidos = form.elements.apellidos;
const nif = form.elements.nif;
const email = form.elements.email;
const telefono = form.elements.telefono;
const nickname = form.elements.nickname;
const localidad = form.elements.localidad;
const provincia = form.elements.provincia;
const direccion = form.elements.direccion;
const fecha_nacimiento = form.elements.fecha_nacimiento;
const contraseña = form.elements.contraseña;
const contraseña_rep = form.elements.contraseña_rep;
const politica_privacidad = form.elements.privacidad;
const submit =document.getElementById("submit");
 let contadorContraseña=0; 

// Expresiones regulares para validación de campos con patrón
const regexNIF = /^\d{8}[A-Z]$/;
const regexEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const regexTelefono = /^(6|7)\d{8}$/;
const regexNickname = /^[A-Za-z][A-Za-z0-9]{0,9}$/;
const regexProvinciaLocalidadNombreApellidos = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{2,}$/;
const regexContraseña= /^(?=.*\d)(?=.*[\.\u0021-\u002b\u003c-\u0040])(?=.*[A-Z])(?=.*[a-z]).{8,16}$/;
const regexDireccion = /^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\s#,./-]+$/;





// Función para calcular la edad a partir de una fecha de nacimiento
function calcularEdad(fecha) {
  const hoy = new Date();
  const fecha_nacimiento = new Date(fecha);
  let edad = hoy.getFullYear() - fecha_nacimiento.getFullYear();
  const mes = hoy.getMonth() - fecha_nacimiento.getMonth();
  if (mes < 0 || (mes === 0 && hoy.getDate() < fecha_nacimiento.getDate())) {
    edad--;
  }
  return edad;
}
//Si el campo no es valido le añadimos la clase is-invalid de Boostrap que hace que se resalte el campo 
function compruebaNombre(inputNombre) {
  if (!regexProvinciaLocalidadNombreApellidos.test(inputNombre.value)) {
    
    inputNombre.classList.add('is-invalid');
    alert("El nombre debe tener al menos 2 letras y solo pueden ser letras");
  }else{
    inputNombre.classList.remove('is-invalid');  
        }
}

nombre.addEventListener('change', () => {
  compruebaNombre(nombre);
});
function compruebaApellidos(inputApellidos) {
  if (!regexProvinciaLocalidadNombreApellidos.test(inputApellidos.value)) {
    
    inputApellidos.classList.add('is-invalid');
    alert("Los apellidos deben tener al menos 2 caracteres y solo pueden ser letras");
    
  } else {
    inputApellidos.classList.remove('is-invalid');
      }
}

apellidos.addEventListener('change', () => {
  compruebaApellidos(apellidos);
});
function comprobarLetraNIF(nif) {
  var letras = "TRWAGMYFPDXBNJZSQVHLCKE";
  var dni = nif.substring(0, 8);
  var letra = nif.charAt(8).toUpperCase();
  var letraCorrecta = letras.charAt(dni % 23);
  
  if (letra == letraCorrecta) {
    return true;
  } else {
    return false;
  }
}

function compruebaNIF(inputNIF) {
  if (!regexNIF.test(inputNIF.value)) {
    inputNIF.classList.add('is-invalid');
    alert("El formato del NIF no es valido");
  } else {
    
    var valido = comprobarLetraNIF(inputNIF.value);
    if (valido) {
      inputNIF.classList.remove('is-invalid');
          } else {
      inputNIF.classList.add('is-invalid');
      alert("La letra del NIF no es la correcta");
      
    }
  }
}

nif.addEventListener('change', () => {
  compruebaNIF(nif);
});

function compruebaEmail(inputEmail) {
  if (!regexEmail.test(inputEmail.value)) {
    
    inputEmail.classList.add('is-invalid');
    alert("El email introducido no es valido");
  } else {
    inputEmail.classList.remove('is-invalid');
      }
}

email.addEventListener('change', () => {
  compruebaEmail(email);
});

function compruebaTelefono(inputTelefono) {
  if (!regexTelefono.test(inputTelefono.value)) {
    
    inputTelefono.classList.add('is-invalid');
    alert("El número de telefono no es valido, debe tener 9 digitos y comenzar por 6 o 7");
  } else {
    inputTelefono.classList.remove('is-invalid');
      }
}

telefono.addEventListener('change', () => {
  compruebaTelefono(telefono);
});

function compruebaNickname(inputNickname) {
  if (!regexProvinciaLocalidadNombreApellidos.test(inputNickname.value)) {
    
    inputNickname.classList.add('is-invalid');
    alert("El nombre debe tener al menos 4 caracteres");
  } else {
    inputNickname.classList.remove('is-invalid');
      }
}

nickname.addEventListener('change', () => {
  compruebaNickname(nickname);
});

function compruebaLocalidad(inputLocalidad) {
  if (!regexProvinciaLocalidadNombreApellidos.test(inputLocalidad.value)) {
    
    inputLocalidad.classList.add('is-invalid');
    alert("La localidad debe tener al menos 2 letras y solo pueden ser letras");
  } else {
    inputLocalidad.classList.remove('is-invalid');
      }
}

localidad.addEventListener('change', () => {
  compruebaLocalidad(localidad);
});

function compruebaProvincia(inputProvincia) {
  if (!regexProvinciaLocalidadNombreApellidos.test(inputProvincia.value)) {
    
    inputProvincia.classList.add('is-invalid');
    alert("La provincia debe tener al menos 2 letras y solo pueden ser letras");
  } else {
    inputProvincia.classList.remove('is-invalid');
      }
}

provincia.addEventListener('change', () => {
  compruebaProvincia(provincia);
});

function compruebaDireccion(inputDireccion) {
  if (!regexDireccion.test(inputDireccion.value)) {
    
    inputDireccion.classList.add('is-invalid');
    alert("La dirección tiene que tener al menos 2 caracteres");
  } else {
    inputDireccion.classList.remove('is-invalid');
      }
}

direccion.addEventListener('change', () => {
  compruebaDireccion(direccion);
});

function repiteContraseña(inputContrasena, inputContrasena_rep){
    if(inputContrasena_rep.value== ""){
        return;
    }
    
    if(inputContrasena.value === inputContrasena_rep.value){
        
        return true;
    }else{
        alert("Los campos contraseña y repetir contraseña no son identidcos");
        return false;
    }
}


function compruebaContraseña() {
  
  if (regexContraseña.test(contraseña)) {
     contraseña.classList.remove("is-invalid");
  } else {
    document.getElementById("contraseña").classList.add("is-invalid");
  }

  if (contraseña === contraseña_rep) {
    document.getElementById("contraseña_rep").classList.remove("is-invalid");
  } else {
    document.getElementById("contraseña_rep").classList.add("is-invalid");
  }
}




function compruebaFechaNacimiento(inputFechaNacimiento) {
  const edadMinima = 18; // Edad mínima requerida para registrarse
  const fecha = new Date(inputFechaNacimiento.value);
  const edad = calcularEdad(fecha);

  if (edad < edadMinima) {
    
    inputFechaNacimiento.classList.add('is-invalid');
        alert(" La edad minima para registrarse en nuestra pagina web es de 18 años  ");
  } else {
    inputFechaNacimiento.classList.remove('is-invalid');
      }
}

fecha_nacimiento.addEventListener('change', () => {
  compruebaFechaNacimiento(fecha_nacimiento);
});


contraseña.addEventListener('change', () => {
    compruebaContraseña(contraseña,contraseña_rep);
});

contraseña_rep.addEventListener('change', () => {
    compruebaContraseña(contraseña,contraseña_rep);
});



