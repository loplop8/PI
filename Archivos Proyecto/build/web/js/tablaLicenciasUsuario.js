$(document).ready(function () {
    $('#userLicencias').DataTable({
        columns: [
            {data: 'Tipo Licencia'},
            {data: 'Fecha Expedici�n'},
            {data: 'V�lida Desde'},
            {data: 'V�lida Hasta'},
            {data: 'Observaciones'},
            {data: 'Restricciones'},
            {data: ' Validada Admin'}


        ],
        language: {
            url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
        }
    });
});


