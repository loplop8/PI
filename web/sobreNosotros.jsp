<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="boostrap/bootstrap-5.0.2-dist/css/bootstrap.min.css">
    
    <script src="boostrap/bootstrap-5.0.2-dist/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>  
    <link rel="stylesheet" href="./css/style3.css">

    <title>Sobre nosotros</title>
</head>
<body>
    
    
        <header class=" text-white" >
            <div>




                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start w-100 "id="cabecera" id="cabecera" >


                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        <li><a href="./Inicio" class="nav-link px-2 text-secondary"><img src="img/LogoSWL.png" alt="LogoSWL" width="100px" height="40px" ></a></li>
                        <li><a href="./SobreNosotros" class="nav-link px-2 text-white mt-2 elementosNoResponsive">Sobre Nosotros</a></li>
                        <li><a href="./ComoTrabajamos" class="nav-link px-2 text-white mt-2 elementosNoResponsive" >Como Trabajamos</a></li>
                        <li><a href="./Foro" class="nav-link px-2 text-white mt-2 elementosNoResponsive">Foro</a></li>
                        <li><a href="./Encuentranos" class="nav-link px-2 text-white mt-2 elementosNoResponsive" >Encuentranos</a></li>
                    </ul>

                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-5 elementosNoResponsive" >
                        <input type="search" class="form-control form-control-dark" placeholder="Buscar..." aria-label="Search">
                    </form>

                    <div class="text-end me-5">

                        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                            <div class="container-fluid" id="Usuario">

                                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                                    <span class="navbar-toggler-icon"></span>
                                </button>

                                <div class="collapse navbar-collapse" id="navbarNav">
                                    <li class="nav-item dropdown " id="puntoNegro" >


                                        <a href="./SobreNosotros" class="nav-link px-2 text-white mt-2 elementosResponsive">Sobre Nosotros</a>

                                        <a href="./ComoTrabajamos" class="nav-link px-2 text-white mt-2 elementosResponsive" >Como Trabajamos</a>
                                        <a href="./Foro" class="nav-link px-2 text-white mt-2 elementosResponsive">Foro</a>
                                        </a>

                                        <a href="./Encuentranos" class="nav-link px-2 text-white mt-2 elementosResponsive">Encuentranos</a>
                                        </a>
                                        <c:if test="${usuario.id_usuario==null}">

                                            <a href="#" class="nav-link px-2 text-white mt-2 elementosResponsive"><button type="button" class="btn btn-outline-warning  me-2"><a href="./Login" id="login"> Iniciar sesi�n</a></button></a>

                                            </a>

                                            <a href="#" class="nav-link px-2 text-white mt-2 elementosResponsive"><button type="button" class="btn btn-outline-warning me-2" > <a href="./Registro" id="login"> Registrate</a></button>
                                            </a>

                                            </a>
                                        </c:if>
                                        <c:if test="${usuario.id_usuario!=null}">
                                            <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">

                                                <img src="${usuario.url_img_perfil}" width="50">   ${usuario.nickname}
                                            </a>



                                            <ul class="dropdown-menu-dark  dropdown-menu  "  aria-labelledby="navbarDarkDropdownMenuLink " >

                                                <c:if test="${usuario.rol=='admin'}">
                                                    <li><a class="dropdown-item" href="admin/PanelAdministracion"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pc-display-horizontal" viewBox="0 0 16 16">
                                                            <path d="M1.5 0A1.5 1.5 0 0 0 0 1.5v7A1.5 1.5 0 0 0 1.5 10H6v1H1a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h14a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1h-5v-1h4.5A1.5 1.5 0 0 0 16 8.5v-7A1.5 1.5 0 0 0 14.5 0h-13Zm0 1h13a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .5-.5ZM12 12.5a.5.5 0 1 1 1 0 .5.5 0 0 1-1 0Zm2 0a.5.5 0 1 1 1 0 .5.5 0 0 1-1 0ZM1.5 12h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1ZM1 14.25a.25.25 0 0 1 .25-.25h5.5a.25.25 0 1 1 0 .5h-5.5a.25.25 0 0 1-.25-.25Z"/>
                                                            </svg> Panel de Administraci�n</a></li>
                                                        </c:if>

                                                <c:if test="${usuario.rol=='moderador'}">
                                                    <li><a class="dropdown-item" href="moderador/PanelModeracion"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pc-display-horizontal" viewBox="0 0 16 16">
                                                            <path d="M1.5 0A1.5 1.5 0 0 0 0 1.5v7A1.5 1.5 0 0 0 1.5 10H6v1H1a1 1 0 0 0-1 1v3a1 1 0 0 0 1 1h14a1 1 0 0 0 1-1v-3a1 1 0 0 0-1-1h-5v-1h4.5A1.5 1.5 0 0 0 16 8.5v-7A1.5 1.5 0 0 0 14.5 0h-13Zm0 1h13a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-7a.5.5 0 0 1 .5-.5ZM12 12.5a.5.5 0 1 1 1 0 .5.5 0 0 1-1 0Zm2 0a.5.5 0 1 1 1 0 .5.5 0 0 1-1 0ZM1.5 12h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1ZM1 14.25a.25.25 0 0 1 .25-.25h5.5a.25.25 0 1 1 0 .5h-5.5a.25.25 0 0 1-.25-.25Z"/>
                                                            </svg> Panel de Moderaci�n</a></li>
                                                        </c:if>


                                                <li><a class="dropdown-item" href="./usuario/CrearAnuncio"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-newspaper" viewBox="0 0 16 16">
                                                        <path d="M0 2.5A1.5 1.5 0 0 1 1.5 1h11A1.5 1.5 0 0 1 14 2.5v10.528c0 .3-.05.654-.238.972h.738a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 1 1 0v9a1.5 1.5 0 0 1-1.5 1.5H1.497A1.497 1.497 0 0 1 0 13.5v-11zM12 14c.37 0 .654-.211.853-.441.092-.106.147-.279.147-.531V2.5a.5.5 0 0 0-.5-.5h-11a.5.5 0 0 0-.5.5v11c0 .278.223.5.497.5H12z"/>
                                                        <path d="M2 3h10v2H2V3zm0 3h4v3H2V6zm0 4h4v1H2v-1zm0 2h4v1H2v-1zm5-6h2v1H7V6zm3 0h2v1h-2V6zM7 8h2v1H7V8zm3 0h2v1h-2V8zm-3 2h2v1H7v-1zm3 0h2v1h-2v-1zm-3 2h2v1H7v-1zm3 0h2v1h-2v-1z"/>
                                                        </svg> Crear Anuncio</a></li>                                          


                                                <li><a class="dropdown-item" href="./usuario/Compras"> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-bag-fill" viewBox="0 0 16 16">
                                                        <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5z"/>
                                                        </svg> Compras</a></li>
                                                <li><a class="dropdown-item" href="./usuario/Ventas"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-seam-fill" viewBox="0 0 16 16">
                                                        <path fill-rule="evenodd" d="M15.528 2.973a.75.75 0 0 1 .472.696v8.662a.75.75 0 0 1-.472.696l-7.25 2.9a.75.75 0 0 1-.557 0l-7.25-2.9A.75.75 0 0 1 0 12.331V3.669a.75.75 0 0 1 .471-.696L7.443.184l.01-.003.268-.108a.75.75 0 0 1 .558 0l.269.108.01.003 6.97 2.789ZM10.404 2 4.25 4.461 1.846 3.5 1 3.839v.4l6.5 2.6v7.922l.5.2.5-.2V6.84l6.5-2.6v-.4l-.846-.339L8 5.961 5.596 5l6.154-2.461L10.404 2Z"/>
                                                        </svg> Ventas</a></li>

                                                <li><a class="dropdown-item" href="./usuario/MisAnuncios"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar4" viewBox="0 0 16 16">
                                                        <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM2 2a1 1 0 0 0-1 1v1h14V3a1 1 0 0 0-1-1H2zm13 3H1v9a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V5z"/>
                                                        </svg> Mis anuncios</a></li>

                                                <li><a class="dropdown-item" href="./usuario/GestionarPerfil"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                                                        <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                                        <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                                                        </svg> Gestionar Perfil</a></li>
                                                <li><a class="dropdown-item" href="./CerrarSesion"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                                                        <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0v-2z"/>
                                                        <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
                                                        </svg> Cerrar Sesi�n </a></li>
                                            </ul>

                                        </c:if>    
                                </div>
                            </div>
                    </div>
                    </header>
  <main>

            <div class="container-fluid d-flex  flex-column" id="video">

          
              <div class="container-fluid  justify-content-center  text-center align-items-center pt-5 flex-column mb-5  " >
    
                <div class="container-fluid row">
                  <h1>�Quieres saber mas sobre nostros?</h1>
                </div>
                  
                
                
                   <div class="row container-fluid">
                    <iframe width="560" height="315" src="https://www.youtube.com/embed/CsKV4fBdjC4" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
                   </div>
                
                </div>
              </div>
              
  <div class="container-fluid  flex-sm-row  " id="aprender">

    <div class="container-fluid  flex-sm-row text-center pt-5">  
        <h2>�Quieres saber m�s acerca de nuestros servicios?</h2>    
        
    <p>Somos una empresa dedicada a la gesti�n de la compra-venta de armas entre particulares.</p>
    
    </div>
    

    <div class="container-fluid flex-column mt-2">
      <div class="row text-center ">
        <div class="col-sm-4">
          <img src="img/varios/calidad.png" alt="img1" class="rounded-circle" >
          <h5> Servicio de Gestion</h5>
          <small>Gestionamos todo por ti desde la recepcion del arma hasta el papeleo</small>


        </div>
        <div class="col-sm-4">
          <img src="img/varios/calidad.png" alt="img1" class="rounded-circle" >          
          <h5>Control de calidad </h5>
          <small>Gracias a nuestro control de calidad revisamos cada arma que sea vendida por nuestra plataforma para que puedas disfrutar de ella lo antes posible</small>


        </div>
        <div class="col-sm-4">
          <img src="img/varios/calidad.png" alt="img1" class="rounded-circle" >          
          <h5>Garantia </h5>
          <small>Todas las armas que vendemos tienen un periodo de prueba de 15 dias, si no estas satisfecho con ella lo gestionaremos todo para que esta vuelva al vendedor</small>
        </div>


      </div>

    </div>
  </div>

  <div class="container-fluid pt-5" id="quien">
    <div class="text-center mb-5 mt-5 mb-5" >
      <h1>�A qui�n queremos llegar?</h1>
    </div>
    
    <div class="row">
      <div class="col-md-6 order-md-1 order-2">
        <div class="card bg-dark text-white">
          <div class="card-body">
            <h3 class="card-title">Amantes del tiro ol�mpico</h3>
            <p class="card-text">�Hola a todos los entusiastas del tiro ol�mpico! En Second Weapon Life, nos apasiona ayudarte a alcanzar tus objetivos en este emocionante deporte!</p>
          </div>
        </div>
        <img class="img-fluid mt-3" src="img/varios/Irlanda1-1024x768.jpeg" style="max-width: 100%; height: auto;">
      </div>
      
      <div class="col-md-6 order-md-2 order-1">
        <div class="card bg-dark text-white">
          <div class="card-body">
            <h3 class="card-title">Amantes de la caza</h3>
            <p class="card-text">Saludos cazadores! En Second Weapon Life, estamos dedicados a ayudarte a encontrar las mejores armas de caza para que puedas disfrutar de tu pasi�n al m�ximo!</p>
          </div>
        </div>
          <img class="img-fluid mt-3" src="img/varios/cazadores.jpeg" alt="Cazadores" style="max-width: 100%; height: auto;">
      </div>
    </div>
  </div>
        

      
      
  
  
  
  

  
  
      
      

  
    


  

  <div class="container-fluid text-center mt-5">
    
    <div class="rounded-circle">
      <img src="img/varios/" alt="">
      <h1>Caracteristicas de nuestra plataforma</h1>
    </div>  
    
      <div class="container-fluid row text-center m-5" >
        <div class="col-sm-3">
          <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-globe2" viewBox="0 0 16 16">
            <path d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm7.5-6.923c-.67.204-1.335.82-1.887 1.855-.143.268-.276.56-.395.872.705.157 1.472.257 2.282.287V1.077zM4.249 3.539c.142-.384.304-.744.481-1.078a6.7 6.7 0 0 1 .597-.933A7.01 7.01 0 0 0 3.051 3.05c.362.184.763.349 1.198.49zM3.509 7.5c.036-1.07.188-2.087.436-3.008a9.124 9.124 0 0 1-1.565-.667A6.964 6.964 0 0 0 1.018 7.5h2.49zm1.4-2.741a12.344 12.344 0 0 0-.4 2.741H7.5V5.091c-.91-.03-1.783-.145-2.591-.332zM8.5 5.09V7.5h2.99a12.342 12.342 0 0 0-.399-2.741c-.808.187-1.681.301-2.591.332zM4.51 8.5c.035.987.176 1.914.399 2.741A13.612 13.612 0 0 1 7.5 10.91V8.5H4.51zm3.99 0v2.409c.91.03 1.783.145 2.591.332.223-.827.364-1.754.4-2.741H8.5zm-3.282 3.696c.12.312.252.604.395.872.552 1.035 1.218 1.65 1.887 1.855V11.91c-.81.03-1.577.13-2.282.287zm.11 2.276a6.696 6.696 0 0 1-.598-.933 8.853 8.853 0 0 1-.481-1.079 8.38 8.38 0 0 0-1.198.49 7.01 7.01 0 0 0 2.276 1.522zm-1.383-2.964A13.36 13.36 0 0 1 3.508 8.5h-2.49a6.963 6.963 0 0 0 1.362 3.675c.47-.258.995-.482 1.565-.667zm6.728 2.964a7.009 7.009 0 0 0 2.275-1.521 8.376 8.376 0 0 0-1.197-.49 8.853 8.853 0 0 1-.481 1.078 6.688 6.688 0 0 1-.597.933zM8.5 11.909v3.014c.67-.204 1.335-.82 1.887-1.855.143-.268.276-.56.395-.872A12.63 12.63 0 0 0 8.5 11.91zm3.555-.401c.57.185 1.095.409 1.565.667A6.963 6.963 0 0 0 14.982 8.5h-2.49a13.36 13.36 0 0 1-.437 3.008zM14.982 7.5a6.963 6.963 0 0 0-1.362-3.675c-.47.258-.995.482-1.565.667.248.92.4 1.938.437 3.008h2.49zM11.27 2.461c.177.334.339.694.482 1.078a8.368 8.368 0 0 0 1.196-.49 7.01 7.01 0 0 0-2.275-1.52c.218.283.418.597.597.932zm-.488 1.343a7.765 7.765 0 0 0-.395-.872C9.835 1.897 9.17 1.282 8.5 1.077V4.09c.81-.03 1.577-.13 2.282-.287z"/>
          </svg>
          <p>Gestiones 100% de forma OnLine</p>
        </div>
        <div class="col-sm-3">
          <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-clock" viewBox="0 0 16 16">
            <path d="M8 3.5a.5.5 0 0 0-1 0V9a.5.5 0 0 0 .252.434l3.5 2a.5.5 0 0 0 .496-.868L8 8.71V3.5z"/>
            <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm7-8A7 7 0 1 1 1 8a7 7 0 0 1 14 0z"/>
          </svg>
          <p>Publicaci�n de anuncios de armas totalmente gratuitos en el momento</p>
        </div>
        <div class="col-sm-3">
          <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-car-front-fill" viewBox="0 0 16 16">
            <path d="M2.52 3.515A2.5 2.5 0 0 1 4.82 2h6.362c1 0 1.904.596 2.298 1.515l.792 1.848c.075.175.21.319.38.404.5.25.855.715.965 1.262l.335 1.679c.033.161.049.325.049.49v.413c0 .814-.39 1.543-1 1.997V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.338c-1.292.048-2.745.088-4 .088s-2.708-.04-4-.088V13.5a.5.5 0 0 1-.5.5h-2a.5.5 0 0 1-.5-.5v-1.892c-.61-.454-1-1.183-1-1.997v-.413a2.5 2.5 0 0 1 .049-.49l.335-1.68c.11-.546.465-1.012.964-1.261a.807.807 0 0 0 .381-.404l.792-1.848ZM3 10a1 1 0 1 0 0-2 1 1 0 0 0 0 2Zm10 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2ZM6 8a1 1 0 0 0 0 2h4a1 1 0 1 0 0-2H6ZM2.906 5.189a.51.51 0 0 0 .497.731c.91-.073 3.35-.17 4.597-.17 1.247 0 3.688.097 4.597.17a.51.51 0 0 0 .497-.731l-.956-1.913A.5.5 0 0 0 11.691 3H4.309a.5.5 0 0 0-.447.276L2.906 5.19Z"/>
          </svg>
          <p>Envio de armas a domicilio</p>
        </div>
        <div class="col-sm-3">
          <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-fast-forward-fill" viewBox="0 0 16 16">
            <path d="M7.596 7.304a.802.802 0 0 1 0 1.392l-6.363 3.692C.713 12.69 0 12.345 0 11.692V4.308c0-.653.713-.998 1.233-.696l6.363 3.692Z"/>
            <path d="M15.596 7.304a.802.802 0 0 1 0 1.392l-6.363 3.692C8.713 12.69 8 12.345 8 11.692V4.308c0-.653.713-.998 1.233-.696l6.363 3.692Z"/>
          </svg>
          <p>Envio rapido y de forma accesible </p>
        </div>  
      </div>
    </div>
    

    

        <div class="bloque7">
          <div class="container">
            <div class="row">
              <div class="col-sm-4 left-side text-center">
                <h2>Contamos con los mejores profesionales</h2>
                <img class="card-img" src="img/varios/armero .webp" width="500" alt="armero">
                <p><h4>Nuestros profesionales</h4></p>
                <a href="https://twitter.com/" class="m-4"><svg  xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-twitter" viewBox="0 0 16 16">
                  <path d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"/>
                </svg></a>
                <a href="https://facebook.com/ " class="m-4" ><svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-linkedin" viewBox="0 0 16 16">
                  <path d="M0 1.146C0 .513.526 0 1.175 0h13.65C15.474 0 16 .513 16 1.146v13.708c0 .633-.526 1.146-1.175 1.146H1.175C.526 16 0 15.487 0 14.854V1.146zm4.943 12.248V6.169H2.542v7.225h2.401zm-1.2-8.212c.837 0 1.358-.554 1.358-1.248-.015-.709-.52-1.248-1.342-1.248-.822 0-1.359.54-1.359 1.248 0 .694.521 1.248 1.327 1.248h.016zm4.908 8.212V9.359c0-.216.016-.432.08-.586.173-.431.568-.878 1.232-.878.869 0 1.216.662 1.216 1.634v3.865h2.401V9.25c0-2.22-1.184-3.252-2.764-3.252-1.274 0-1.845.7-2.165 1.193v.025h-.016a5.54 5.54 0 0 1 .016-.025V6.169h-2.4c.03.678 0 7.225 0 7.225h2.4z"/>
                </svg></a>
                <a href="https://instagram.com/" class="m-4"><svg xmlns="http://www.w3.org/2000/svg" width="50" height="50" fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
                  <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
                </svg></a>



              </div>
              <div class="col-sm-8">
                <p class="lead">Nuestros profesionales totalmente cualificados ser�n los que decidan que armas son aptas para poder ser enviadas a traves de nuestra pagina web. Por ello ofrecemos una garnatia de 15 d�as para la prueba y ver que todo esta en regla.Adem�s, entendemos la importancia de la seguridad en la compra y venta de armas, por lo que nos aseguramos de seguir todas las regulaciones y leyes aplicables en cada pa�s donde ofrecemos nuestros servicios.

                  Tambi�n ofrecemos una amplia gama de opciones de env�o, desde el env�o est�ndar hasta el env�o urgente, para garantizar que los clientes reciban sus armas de manera r�pida y segura. Nos aseguramos de que cada env�o est� asegurado y empaquetado de manera segura para minimizar cualquier riesgo durante el transporte.
                  
                  Nos enorgullece proporcionar un servicio excepcional y de alta calidad a nuestros clientes, y nos comprometemos a brindarles la mejor experiencia posible en su compra o venta de armas. Si tiene alguna pregunta o inquietud, no dude en comunicarse con nuestro equipo de soporte al cliente, quienes estar�n encantados de ayudarlo en todo lo que necesite.</p>
              </div>
            </div>
          </div>
        </div>
      
      
          
          
                
  
</main>
    
        <footer
        class="text-center text-lg-start text-white"
        >
        
      <!-- Grid container -->
      <div class="container p-4 pb-0">
        <hr class="my-3">
        <!-- Section: Links -->
      <section class="">
      <!--Grid row-->
      <div class="row">
        <!-- Grid column -->
        <div class="col-md-3 col-lg-3 col-xl-3 mx-auto mt-3">
          <h6 class="text-uppercase mb-4 font-weight-bold">
            Second Weapaon Life
          </h6>
          <p>
            Somos una plataforma, dedicada a facilitar la venta de armas de segunda mano entre propietarios.
          </p>
        </div>
        <!-- Grid column -->
      
        <hr class="w-100 clearfix d-md-none" />
      
        <!-- Grid column -->
        <div class="col-md-2 col-lg-2 col-xl-2 mx-auto mt-3">
          <h6 class="text-uppercase mb-4 font-weight-bold">Tipos de armas</h6>
          <p>
            <a class="text-white">Escopetas</a>
          </p>
          <p>
            <a class="text-white">Carabinas</a>
          </p>
          <p>
            <a class="text-white">Aire comprimido</a>
          </p>
          <p>
            <a class="text-white">Pistolas</a>
          </p>
        </div>
        <!-- Grid column -->
      
        <hr class="w-100 clearfix d-md-none" />
      
        <!-- Grid column -->
        <div class="col-md-3 col-lg-2 col-xl-2 mx-auto mt-3">
          <h6 class="text-uppercase mb-4 font-weight-bold">
            Enlaces de Interes
          </h6>
          <p>
            <a class="text-white" href="GestionCuenta.html">Ir a tu cuenta</a>
          </p>
          <p>
            <a class="text-white" href="registro">Registrate</a>
          </p>
          <p>
            <a class="text-white">Anuncios</a>
          </p>
          <p>
            <a  href="sobreNosotros.html" class="text-white">Sobre nosotros</a>
          </p>
        </div>
      
        <!-- Grid column -->
        <hr class="w-100 clearfix d-md-none" />
      
        <!-- Grid column -->
        <div class="col-md-4 col-lg-3 col-xl-3 mx-auto mt-3">
          <h6 class="text-uppercase mb-4 font-weight-bold">Contacto</h6>
          <p><i class="fas fa-home mr-3"></i>Bollullos de la Mitaci�n, CP 41110 (Sevilla)</p>
          <p><i class="fas fa-envelope mr-3"></i> info@swl.es</p>
          <p><i class="fas fa-phone mr-3"></i><a class="text-white" href=tel:+34607450598>Llamanos </a> </p>
          <p><i class="fas fa-print mr-3"></i> <a href="8VR5+GX Bollullos de la Mitaci�n"></a></p>
        </div>
        <!-- Grid column -->
      </div>
      <!--Grid row-->
      </section>
      <!-- Section: Links -->
      
      <hr class="my-3">
      
      <!-- Section: Copyright -->
      <section class="p-3 pt-0">
      <div class="row d-flex align-items-center">
        <!-- Grid column -->
        <div class="col-md-7 col-lg-8 text-center text-md-start">
          <!-- Copyright -->
          <div class="p-3">
            � Jos� Antonio L�pez L�pez 
            
          </div>
          <!-- Copyright -->
        </div>
        
      
          <div class="col-md-5 col-lg-4 ml-lg-0 text-center text-md-end">
              <a href="https://facebook.com"
             class="btn btn-outline-light btn-floating m-1"
             class="text-white"
             role="button"
             ><i class="fab fa-facebook-f"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
              <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z"/>
            </svg></i
            ></a>
      
              <a href="https://twitter.com"
             class="btn btn-outline-light btn-floating m-1"
             class="text-white"
             role="button"
             ><i class="fab fa-twitter"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-twitter" viewBox="0 0 16 16">
              <path d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"/>
            </svg>  </i
            ></a>
      
          
          <a href="https://www.google.com/maps/place/C.+Granada,+13,+41110+Bollullos+de+la+Mitaci%C3%B3n,+Sevilla/@37.3413656,-6.142208,17z/data=!4m6!3m5!1s0xd121230798e25eb:0x59f66d5498776f7c!8m2!3d37.3413656!4d-6.1400193!16s%2Fg%2F11c227sx43"
             class="btn btn-outline-light btn-floating m-1"
             class="text-white"
             role="button"
             ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
              <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A31.493 31.493 0 0 1 8 14.58a31.481 31.481 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94zM8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10z"/>
              <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4zm0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
            </svg></a>
            <a href="https://wa.me/+34607450598"
             class="btn btn-outline-light btn-floating m-1"
             class="text-white"
             role="button"
             ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-whatsapp" viewBox="0 0 16 16">
              <path d="M13.601 2.326A7.854 7.854 0 0 0 7.994 0C3.627 0 .068 3.558.064 7.926c0 1.399.366 2.76 1.057 3.965L0 16l4.204-1.102a7.933 7.933 0 0 0 3.79.965h.004c4.368 0 7.926-3.558 7.93-7.93A7.898 7.898 0 0 0 13.6 2.326zM7.994 14.521a6.573 6.573 0 0 1-3.356-.92l-.24-.144-2.494.654.666-2.433-.156-.251a6.56 6.56 0 0 1-1.007-3.505c0-3.626 2.957-6.584 6.591-6.584a6.56 6.56 0 0 1 4.66 1.931 6.557 6.557 0 0 1 1.928 4.66c-.004 3.639-2.961 6.592-6.592 6.592zm3.615-4.934c-.197-.099-1.17-.578-1.353-.646-.182-.065-.315-.099-.445.099-.133.197-.513.646-.627.775-.114.133-.232.148-.43.05-.197-.1-.836-.308-1.592-.985-.59-.525-.985-1.175-1.103-1.372-.114-.198-.011-.304.088-.403.087-.088.197-.232.296-.346.1-.114.133-.198.198-.33.065-.134.034-.248-.015-.347-.05-.099-.445-1.076-.612-1.47-.16-.389-.323-.335-.445-.34-.114-.007-.247-.007-.38-.007a.729.729 0 0 0-.529.247c-.182.198-.691.677-.691 1.654 0 .977.71 1.916.81 2.049.098.133 1.394 2.132 3.383 2.992.47.205.84.326 1.129.418.475.152.904.129 1.246.08.38-.058 1.171-.48 1.338-.943.164-.464.164-.86.114-.943-.049-.084-.182-.133-.38-.232z"/>
            </svg>
            </a>
            
      
      
          
          <a href="https://Instagram.com"
             class="btn btn-outline-light btn-floating m-1"
             class="text-white"
             role="button"
             ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
              <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"/>
            </svg></a>
        </div>
        
      </div>
      </section>
      </div>
      </footer>
      
    
      
    
    
    
        
      
    
    </body>
</html>