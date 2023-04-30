/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const regexEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
const regexContraseña= /^(?=.*\d)(?=.*[\.\u0021-\u002b\u003c-\u0040])(?=.*[A-Z])(?=.*[a-z]).{8,16}$/;


const form=document.querySelector("#login");

const email = document.querySelector("#email");
const contraseña = document.querySelector("#contraseña");

function compruebaEmail(inputEmail) {
  if (!regexEmail.test(inputEmail.value)) {
    
    inputEmail.classList.add('is-invalid');
    alert("El email introducido no es valido");
    return false;
  } else {
    inputEmail.classList.remove('is-invalid');
    return true;
      }
}

email.addEventListener('change', () => {
    compruebaEmail(email);
});

function compruebaContraseña(inputContraseña) {
  
     
     if (regexContraseña.test(inputContraseña.value)) {
     inputContraseña.classList.remove("is-invalid");
     
        return true;
  } else {
      
    inputContraseña.classList.add("is-invalid");
    
    alert("Esa contraseña no puede ser valida en nuestra plataforma");
    return false;

  }
}

  contraseña.addEventListener('change', () => {
        compruebaContraseña(contraseña);
});



function validarFormulario(event) {

if(!compruebaContraseña(contraseña)){
            return ;
}

if(!compruebaEmail(email)){
        return ;
}

}


form.addEventListener('submit', (event) => {
  
  validarFormulario(event);
});