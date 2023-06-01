
$(document).ready(function() {
    $('#UsersTable').DataTable({
        columns: [
            { data: 'nombre_usuario' },
            { data: 'rol' },
            { data: 'nif' },
            { data: 'nombre' },
            { data: 'apellidos' },
            { data: 'email' },
            { data: 'telefono' },
            { data: 'anverso_dni' },
            { data: 'reverso_dni' },
            { data: 'dni_validado' },
            { data: 'esta_activo' },
            undefined, // Acci�n 1
            undefined, // Acci�n 2
            undefined  // Acci�n 3
        ],
        language: {
        url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
    }
    }
            );
});