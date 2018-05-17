(function (ng) {
    /**
     * @ngdoc controller
     * @name bicicleta.detail.controller:bicicsDetailCtrl
     * @description
     * Definición del controlador de Angular del módulo del detalle de un bicicleta. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object}bicicletaContext Constante injectada que contiene la ruta
     * donde se encuentra el API de bicicletas en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("moduloBicis");

    mod.constant("biciContext", "api/bicis");

    mod.controller("biciDetailCtrl", ['$scope', 'biciContext', '$http', '$state',
        function ($scope, biciContext, $http, $state) {
            
            if ($state.params.biciId !== undefined && $state.params.biciId !== null) {
                
                $http.get(biciContext + '/' + $state.params.biciId).then(function (response) {
                    $scope.biciActual = response.data;
                    console.log($scope.biciActual);
                });
            }
        }]);
    
})(window.angular);