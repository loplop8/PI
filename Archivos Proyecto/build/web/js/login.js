/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const regexEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;


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







function validarFormulario(event) {


if(!compruebaEmail(email)){
        return ;
}

}


form.addEventListener('submit', (event) => {
  
  validarFormulario(event);
});