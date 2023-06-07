
$(document).ready(function() {
    $('#VentasTable').DataTable({
        columns: [
            { data:'Número_del_Pedido'},
            { data:'Comprador'},
            { data:'Arma_Vendida'},
            { data:'Fecha_Venta'},
            { data:'Fecha_Entrega_Comprador'},
            { data:'Estado_del_Pedido'},
            { data:'Ganancias'},
            { data:'Contrato_Compra_Venta'}
        ],
        
        
                            
        language: {
        url: '//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json'
    } 
    }
            );
});