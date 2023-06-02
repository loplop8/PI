
$(document).ready(function() {
    $('#LicenciasTable').DataTable({
        columns: [
            { data: 'nombre_usuario' },
            { data: 'tipo_licencia' },
            { data: 'fecha_expedición' },
            { data: 'válida_desde' },
            { data: 'válida_hasta' },
            { data: 'observaciones' },
            { data: 'restricciones' },
            { data: 'imagen_anverso' },
            { data: 'imagen_reverso' },
            { data: 'validada_admin' },
            undefined // Acción 1
            
        ],
        language: {
        url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
    }
    
    }
            );
});