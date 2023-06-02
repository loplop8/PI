$(document).ready(function () {
    $('#userAnuncios').DataTable({
        columns: [
           {data: 'titulo'},
            {data: 'fecha_publicacion'},
            {data: 'precio'},
            {data: 'marca_arma'},
            {data: 'estado_del_anuncio'},
            null,null,null
           
        ],
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
        }
    });
});