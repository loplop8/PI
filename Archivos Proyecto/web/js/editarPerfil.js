
const form = document.querySelector('.requires-validation');
const email = document.querySelector("#email");
const valorEmail=email.value; //Primer valor que se encuentra al entrar en la pagina, por si se equivoca y lo quiere volver a poner que no le de error
const telefono = document.querySelector("#telefono");
const valorTelefono=telefono.value;
const nickname = document.querySelector("#nickname");
const valorNickName=nickname.value;
const submit =document.getElementById("submit");

// Expresiones regulares para validación de campos con patrón
const regexEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const regexTelefono = /^(6|7)\d{8}$/;
const regexNickname = /^[A-Za-z0-9][A-Za-z0-9]{4,9}$/;




// Función para calcular la edad a partir de una fecha de nacimiento

function compruebaEmail(inputEmail) {
  if (!regexEmail.test(inputEmail.value)) {
    
        
    
    
    inputEmail.classList.add('is-invalid');
    alert("El email introducido no es valido");
    return false;
  } else {fetch(`./../Rest/Usuarios`)
      .then(response => response.json())
      .then(data => {
        const existeUsuario = data.some(usuario => usuario.email === inputEmail.value);
        if (existeUsuario) {
            
            if(valorEmail===inputEmail.value){
          inputEmail.classList.remove('is-invalid');
          return true;
          
    }else{
            
          inputEmail.classList.add('is-invalid');
          alert("Ese email ya esta en uso");
          return false;
      }
  } else {
          inputEmail.classList.remove('is-invalid');
          return true;
        }
      })
      .catch(error => console.error(error));
  }
  
}

email.addEventListener('change', () => {
  compruebaEmail(email);
});

function compruebaTelefono(inputTelefono) {
  if (!regexTelefono.test(inputTelefono.value)) {
    
    inputTelefono.classList.add('is-invalid');
    alert("El número de telefono no es valido, debe tener 9 digitos y comenzar por 6 o 7");
    return false;
    } else {
        fetch(`./../Rest/Usuarios`)
      .then(response => response.json())
      .then(data => {
        const existeUsuario = data.some(usuario => usuario.telefono === inputTelefono.value);
        if (existeUsuario) {
            
            
            if(valorTelefono===inputTelefono.value){
          inputTelefono.classList.remove('is-invalid');
          return true;
          
    }else{
          inputTelefono.classList.add('is-invalid');
          alert("Ese télefono ya esta en uso");
          return false;
        }
    } else {
          inputTelefono.classList.remove('is-invalid');
          return true;
        }
      })
      .catch(error => console.error(error));
    }
  }
        
        
        
    


telefono.addEventListener('change', () => {
  compruebaTelefono(telefono);
});

function compruebaNickname(inputNickname) {
  if (!regexNickname.test(inputNickname.value)) {
    inputNickname.classList.add('is-invalid');
    alert("El nombre debe tener al menos 4 caracteres, máximo 9 y no contener caracteres especiales");
    return false;
  } else {
    fetch(`./../Rest/Usuarios`)
      .then(response => response.json())
      .then(data => {
        const existeUsuario = data.some(usuario => usuario.nickname === inputNickname.value);
        if (existeUsuario) {
            
            
            if(valorNickName===inputNickname.value){
          inputNickname.classList.remove('is-invalid');
          return true;
          
    }else{
        
    
          inputNickname.classList.add('is-invalid');
          alert("Ese nombre de usuario ya esta en uso")
          return false;
        }
    }
        else {
          inputNickname.classList.remove('is-invalid');
          return true;
        }
      })
      .catch(error => console.error(error));
  }
}

nickname.addEventListener('change', () => {
  compruebaNickname(nickname);
});










function validarFormulario(event) {
  
  
  
  // Validamos el campo "nombre"
  
  // Validamos el campo "email"
  if (!compruebaEmail(email)) {
    return;
  }
  
  // Validamos el campo "telefono"
  if (!compruebaTelefono(telefono)) {
    return;
  }
  
  // Validamos el campo "nickname"
  if (!compruebaNickname(nickname)) {
    return;
  }
  
  
  
  
  // Validamos el campo "fecha de nacimiento"
  
  
  // Validamos el campo "contraseña"
   
  // Validamos el campo "política de privacidad"
  
  
  // Si llegamos aquí, todos los campos son válidos y podemos  enviar el formulario al servidor
    alert("Estamos revisando sus datos,pulse en la notificación para poder continuar");
    
}


form.addEventListener('submit', (event) => {
  
  validarFormulario(event);
});



