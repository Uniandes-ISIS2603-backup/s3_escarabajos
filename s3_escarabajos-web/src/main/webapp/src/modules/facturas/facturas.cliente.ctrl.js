(function(ng){
    
    /**
         * @ngdoc controller
         * @name factura.controller:accesorioCtrl
         * @description
         * Definición del controlador de Angular del módulo Factura. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} facturaContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Factura en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
    var mod = ng.module("moduloFacturas");
    
    mod.constant("facturaContext","api/facturas");
    
    mod.controller('facturaClienteCtrl',['$scope','$http','facturaContext',
        function($scope,$http,facturaContext){
            
            if ((sessionStorage.getItem("id") !== undefined)&& (sessionStorage.getItem("id") !== null)) {
                
                $http.get(facturaContext + '/cliente/' + sessionStorage.getItem("id")).then(function (response) {
                    $scope.facturas2 = response.data;
                });
            }
    }]);
})(window.angular)
