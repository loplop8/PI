$(document).ready(function () {
    $('#HilosTable').DataTable({
        columns: [
            {data: 'Hora_de_Publicación_del_Hilo'},
            {data: 'Hilo'},
            {data: 'Tema'},    
            {data:'Entrar_en_el_Hilo'} 
        ],
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
        }
    });
});
