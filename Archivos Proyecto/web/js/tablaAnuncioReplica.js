$(document).ready(function () {
    $('#anunciosReplica').DataTable({
        columns: [
            {data: 'nombre_usuario'},
            {data: 'titulo'},
            {data: 'fecha_publicacion'},
            {data: 'precio_neto'},
            {data: 'marca_arma'},
            {data: 'tipo_gas'},
            {data: 'capacidad_cargador'},
            {data: 'piezas_canon'},
            {data: 'estado_anuncio'},
            undefined, // Acción 1
            undefined, // Acción 2
            undefined, // Acción 3
            undefined // Acción 4
        ],
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
        }
    });
});