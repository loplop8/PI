
$(document).ready(function() {
    $('#ComprasTable').DataTable({
        columns: [
            { data:'Número_del _Pedido'},
            { data:'Vendedor'},
            { data:'Arma_Comprada'},
            { data:'Fecha_Compra'},
            { data:'Fecha_Entrega'},
            { data:'Estado_del_Pedido'},
            { data:'Factura'},
            { data:'Contrato_Compra_Venta'}
        ],
        language: {
        url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
    } 
    }
            );
});