
$(document).ready(function() {
    $('#PedidosTable').DataTable({
        columns: [
            { data: 'Número_del _Pedido' },
            { data: 'Comprador' },
            { data: 'Vendedor' },
            { data: 'Arma_Vendida' },
            { data: 'Fecha_Compra' },
            { data: 'Fecha_Entrega' },
            { data: 'Estado_del_Pedido' },
            { data: 'Factura' },
            { data: 'Contrato_Compra_Venta' },
            { data: 'Acciones' },
            
        ],
        language: {
        url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
    }
    
    }
            );
});