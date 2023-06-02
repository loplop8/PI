$(document).ready(function() {
    $('#AnunciosFuego').DataTable({
        columns: [
            { data: 'Usuario' },
            { data: 'Titulo' },
            { data: 'Fecha Publicaci�n' },
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
            undefined, // Acci�n 1
            undefined, // Acci�n 2
            undefined, // Acci�n 3
            undefined, // Acci�n 4
            undefined  // Acci�n 5
        ],
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
        }
    });
});
