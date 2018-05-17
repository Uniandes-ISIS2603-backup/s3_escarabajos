(function (ng) {
    /**
         * @ngdoc controller
         * @name bicisUsada.delete.controller:biciUsadaDeleteCtrl
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

     mod.constant("biciUsadaContext","api/clientes");
    
    mod.controller('biciUsadaDeleteCtrl',['$scope','$http','biciUsadaContext','$state',
        function($scope,$http,biciUsadaContext,$state){

            $http.delete(biciUsadaContext + '/' + $state.params.clienteId + "/bicis/" + $state.params.biciUsadaId).then(function (response) {
                $state.go('bicisUsadaList',{biciUsadaId: response.data.id}, {reload: true});
            });
        }
    ]);
}
)(window.angular);