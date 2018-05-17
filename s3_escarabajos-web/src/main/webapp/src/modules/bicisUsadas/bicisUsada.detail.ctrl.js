(function (ng) {
        /**
         * @ngdoc controller
         * @name bicisUsada.detail.ctrl:biciUsadaDetailCtrl
         * @description
         * Definición del controlador de Angular del módulo bicicletaUsada. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} biciUsadaContext Constante injectada que contiene la ruta
         * donde se encuentra el API de BicicletaUsada en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
    var mod = ng.module("moduloBicisUsadas");

    mod.constant("biciUsadaContext", "api/clientes");

    mod.controller("biciUsadaDetailCtrl", ['$scope', 'biciUsadaContext', '$http', '$state',
        function ($scope, biciUsadaContext, $http, $state) {
            
            if ($state.params.biciUsadaId !== undefined && $state.params.biciUsadaId !== null) {
                
            //el get deberia tener lo siguiente: biciUsadaContext + '/' + $state.params.vendedorId + '/bicis/' + $state.params.biciUsadaId
            //pero como no esta completa la parte de vendedor lo puse para un vendedor especifico para mostrar 
            //que si sirve la union del back con el front
                $http.get(biciUsadaContext + '/' + $state.params.clienteId + '/bicis/' + $state.params.biciUsadaId).then(function (response) {
                    $scope.biciUsadaActual = response.data;
                    
                });
            }
        }]);
    
})(window.angular);