(function (ng) {
    
    /**
     * @ngdoc controller
     * @name facturas.detail.delete.controller:facturaDeleteCtrl
     * @description
     * Definición del controlador de Angular del módulo del delete de un Factura. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} facturasContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Facturas en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("moduloFacturas");

     mod.constant("facturaContext","api/facturas");
    
    mod.controller('facturaDeleteCtrl',['$scope','$http','facturaContext','$state',
        function($scope,$http,facturaContext,$state){

            $http.delete(facturaContext + '/'+ $state.params.facturaId).then(function (response) {
                $state.go('facturasList',{facturaId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);