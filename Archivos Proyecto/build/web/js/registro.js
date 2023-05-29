// Obtener provincias
fetch('./Rest/Provincias')
  .then(response => response.json())
  .then(data => {
    // Agregar cada provincia al select
    const selectProvincias = document.getElementById('provincias');
    data.forEach(provincia => {
      const option = document.createElement('option');
      option.text = provincia.nombre;
      option.value = provincia.id_provincia;
      selectProvincias.add(option);
    });
  });

// Obtener municipios de la provincia seleccionada
document.getElementById('provincias').addEventListener('change', () => {
  const provincia = document.getElementById('provincias').value;
  fetch(`./Rest/Municipios/${provincia}`)
    .then(response => response.json())
    .then(data => {
      // Limpiar select de municipios y agregar el predeterminado
      const selectMunicipios = document.getElementById('municipios');
      selectMunicipios.innerHTML="";
      const optionPred= document.createElement('option');
      optionPred.text="Seleccione un municipio";
      optionPred.value="";
      selectMunicipios.add(optionPred);
      
        // Agregar cada municipio al select
      data.forEach(municipio => {
        const option = document.createElement('option');
        option.text = municipio.nombre;
        option.value = municipio.id_municipio;
        selectMunicipios.add(option);
      });
    });
});


var usuarios = [];

fetch('./Rest/Usuarios')
  .then(response => response.json())
  .then(data => {
    usuarios = data;
  })
  .catch(error => console.error(error));










const municipio=document.getElementById("municipios");
const form = document.querySelector('.requires-validation');
const nombre = form.elements.nombre;
const apellidos = form.elements.apellidos;
const nif = form.elements.nif;
const email = form.elements.email;
const telefono = form.elements.telefono;
const nickname = form.elements.nickname;
const direccion = form.elements.direccion;
const fecha_nacimiento = form.elements.fecha_nacimiento;
const contrase�a = form.elements.contrase�a;
const contrase�a_rep = form.elements.contrase�a_rep;
const politica_privacidad = form.elements.privacidad;
const verifica_son_datosDni=form.elements.verifica_dni;
const submit =document.getElementById("submit");

// Expresiones regulares para validaci�n de campos con patr�n
const regexNIF = /^\d{8}[A-Z]$/;
const regexEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const regexTelefono = /^(6|7)\d{8}$/;
const regexNickname = /^[A-Za-z0-9][A-Za-z0-9]{4,9}$/;
const regexProvinciaLocalidadNombreApellidos = /^[a-zA-Z������������\s]{2,}$/;
const regexContrase�a= /^(?=.*\d)(?=.*[\.\u0021-\u002b\u003c-\u0040])(?=.*[A-Z])(?=.*[a-z]).{8,16}$/;
const regexDireccion = /^[a-zA-Z0-9������������\s#,./-]+$/;





// Funci�n para calcular la edad a partir de una fecha de nacimiento
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
//Si el campo no es valido le a�adimos la clase is-invalid de Boostrap que hace que se resalte el campo 
function compruebaNombre(inputNombre) {
  if (!regexProvinciaLocalidadNombreApellidos.test(inputNombre.value)) {
    
    inputNombre.classList.add('is-invalid');
    alert("El nombre debe tener al menos 2 letras y solo pueden ser letras");
        return false;
    }else{
    inputNombre.classList.remove('is-invalid');  
    return true;    
    }
}

nombre.addEventListener('change', () => {
  compruebaNombre(nombre);
});
function compruebaApellidos(inputApellidos) {
  if (!regexProvinciaLocalidadNombreApellidos.test(inputApellidos.value)) {
    
    inputApellidos.classList.add('is-invalid');
    alert("Los apellidos deben tener al menos 2 caracteres y solo pueden ser letras");
    return false;
    
  } else {
    inputApellidos.classList.remove('is-invalid');
    return true;
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
  var valor = false;

  if (!regexNIF.test(inputNIF.value)) {
    inputNIF.classList.add('is-invalid');
    alert("El formato del NIF no es v�lido");
    valor = false;
  } else {
    var valido = comprobarLetraNIF(inputNIF.value);

    if (valido) {
      const existeUsuario = usuarios.some(usuario => usuario.nif === inputNIF.value);

      if (existeUsuario) {
        inputNIF.classList.add('is-invalid');
        alert("Ese NIF ya est� en uso");
        valor = false;
      } else {
        inputNIF.classList.remove('is-invalid');
        valor = true;
      }
    } else {
      inputNIF.classList.add('is-invalid');
      alert("La letra del NIF no es la correcta");
      valor = false;
    }
  }

  return valor;
}

nif.addEventListener('change', () => {
  compruebaNIF(nif);
});

function compruebaEmail(inputEmail) {
  if (!regexEmail.test(inputEmail.value)) {
    inputEmail.classList.add('is-invalid');
    alert("El email introducido no es v�lido");
    return false;
  } else {
    const existeUsuario = usuarios.some(usuario => usuario.email === inputEmail.value);
    if (existeUsuario) {
      inputEmail.classList.add('is-invalid');
      alert("Ese email ya est� en uso");
      return false;
    } else {
      inputEmail.classList.remove('is-invalid');
      return true;
    }
  }
}

email.addEventListener('change', () => {
  compruebaEmail(email);
});

function compruebaTelefono(inputTelefono) {
  if (!regexTelefono.test(inputTelefono.value)) {
    inputTelefono.classList.add('is-invalid');
    alert("El n�mero de tel�fono no es v�lido, debe tener 9 d�gitos y comenzar por 6 o 7");
    return false;
  } else {
    const existeUsuario = usuarios.some(usuario => usuario.telefono === inputTelefono.value);
    if (existeUsuario) {
      inputTelefono.classList.add('is-invalid');
      alert("Ese tel�fono ya est� en uso");
      return false;
    } else {
      inputTelefono.classList.remove('is-invalid');
      return true;
    }
  }
}

telefono.addEventListener('change', () => {
  compruebaTelefono(telefono);
});

function compruebaNickname(inputNickname) {
  if (!regexNickname.test(inputNickname.value)) {
    inputNickname.classList.add('is-invalid');
    alert("El nombre debe tener al menos 4 caracteres, m�ximo 9 y no contener caracteres especiales");
    return false;
  } else {
    const existeUsuario = usuarios.some(usuario => usuario.nickname === inputNickname.value);
    if (existeUsuario) {
      inputNickname.classList.add('is-invalid');
      alert("Ese nickname ya est� en uso");
      return false;
    } else {
      inputNickname.classList.remove('is-invalid');
      return true;
    }
  }
}

nickname.addEventListener('change', () => {
  compruebaNickname(nickname);
});







function compruebaDireccion(inputDireccion) {
  if (!regexDireccion.test(inputDireccion.value)) {
    
    inputDireccion.classList.add('is-invalid');
    alert("La direcci�n tiene que tener al menos 2 caracteres, y los caracteres �,� no estan permitidos");
        return false;
    } else {
    inputDireccion.classList.remove('is-invalid');
        return true;
    }
}

direccion.addEventListener('change', () => {
  compruebaDireccion(direccion);
});

function repiteContrase�a(inputContrasena, inputContrasena_rep){
    if(inputContrasena_rep.value === ""){
        return;
    }
    
    if(inputContrasena.value === inputContrasena_rep.value){
        
        return true;
    }else{
        alert("Los campos contrase�a y repetir contrase�a no son identidcos");
        return false;
    }
}


function compruebaContrase�a() {
  
  if (repiteContrase�a(contrase�a,contrase�a_rep)) {
   contrase�a.classList.remove("is-invalid");
     contrase�a_rep.classList.remove("is-invalid");
     
     if (regexContrase�a.test(contrase�a.value) && regexContrase�a.test(contrase�a_rep.value) ) {
     contrase�a.classList.remove("is-invalid");
     contrase�a_rep.classList.remove("is-invalid");
        return true;
  } else {
      
    contrase�a.classList.add("is-invalid");
    contrase�a_rep.classList.add("is-invalid");
    alert("La contrase�a debe de constar como minimo de una letra mayuscula,una misnuscula,un numero y caracter especial\n Minimo 8 caracteres y maximo 16");
    return false;
        }

     
  } else {
    contrase�a.classList.add("is-invalid");
    contrase�a_rep.classList.add("is-invalid");
    return false;
  
  
    }
}



function compruebaFechaNacimiento(inputFechaNacimiento) {
  const edadMinima = 18; // Edad m�nima requerida para registrarse
  const fecha = new Date(inputFechaNacimiento.value);
  const edad = calcularEdad(fecha);

  if (edad < edadMinima) {
    
    inputFechaNacimiento.classList.add('is-invalid');
        alert(" La edad minima para registrarse en nuestra pagina web es de 18 a�os  ");
        return false;
    } else {
    inputFechaNacimiento.classList.remove('is-invalid');
      return true;
    }
}

fecha_nacimiento.addEventListener('change', () => {
  compruebaFechaNacimiento(fecha_nacimiento);
});


contrase�a.addEventListener('change', () => {
    compruebaContrase�a(contrase�a,contrase�a_rep);
});

contrase�a_rep.addEventListener('change', () => {
    compruebaContrase�a(contrase�a,contrase�a_rep);
});



function validarFormulario(event) {
  
  
  
  // Validamos el campo "nombre"
  if (!compruebaNombre(nombre)) {
    event.preventDefault();
    return;
  }
  
  // Validamos el campo "apellidos"
  if (!compruebaApellidos(apellidos)) {
    event.preventDefault();
        return;
  }
  
  // Validamos el campo "nif"
  if (!compruebaNIF(nif)) {
    event.preventDefault();
        return;
  }
  
  // Validamos el campo "email"
  if (!compruebaEmail(email)) {
    event.preventDefault();
        return;
  }
  
  // Validamos el campo "telefono"
  if (!compruebaTelefono(telefono)) {
    event.preventDefault();
        return;
  }
  
  // Validamos el campo "nickname"
  if (!compruebaNickname(nickname)) {
    event.preventDefault();
        return;
  }
  
  
  
  // Validamos el campo "direccion"
  if (!compruebaDireccion(direccion)) {
    event.preventDefault();
        return;
  }
  
  // Validamos el campo "fecha de nacimiento"
  
  if (!compruebaFechaNacimiento(fecha_nacimiento)) {
    event.preventDefault();
        return;
  }
  
  // Validamos el campo "contrase�a"
  if (!compruebaContrase�a()) {
    event.preventDefault();
        return;
  }
   
  // Validamos el campo "pol�tica de privacidad"
  if (!politica_privacidad.checked) {
    event.preventDefault();
        alert("Debes aceptar la pol�tica de privacidad para registrarte");
    return;
  }
  
  if (!verifica_son_datosDni.checked) {
    
        alert("Debes verificar que los datos son los de tu DNI ");
    event.preventDefault();
        return;
  }
  
  
    
    
}


form.addEventListener('submit', (event) => {
  
  validarFormulario(event);
});



