$(document).ready(function() {
    $('#AnunciosFuego').DataTable({
        columns: [
            { data: 'Usuario' },
            { data: 'Titulo' },
            { data: 'Fecha Publicación' },
            { data: 'Precio Neto' },
            { data: 'Marca Arma' },
            { data: 'Numero Guia' },
            { data: 'Calibre' },
            { data: 'Numero identificacion del Arma' },
            { data: 'Fecha Expedicion Guia' },
            { data: 'Anverso Guia' },
            { data: 'Reverso Guia' },
            { data: 'Estado Guia' },
            { data: 'Estado Anuncio' },
            undefined, // Acción 1
            undefined, // Acción 2
            undefined, // Acción 3
            undefined, // Acción 4
            undefined  // Acción 5
        ],
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
        }
    });
});
