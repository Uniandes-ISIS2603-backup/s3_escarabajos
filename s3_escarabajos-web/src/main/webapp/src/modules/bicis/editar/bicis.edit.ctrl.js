(function (ng) {
    /**
     * @ngdoc controller
     * @name bicicleta.detail.update.controller:bicicletaEditCtrl
     * @description
     * Definición del controlador de Angular del módulo del update de un Bicicleta. 
     * Se crea el controlador con el cual se maneja el módulo.
     * En el controlador se definen los atributos y métodos que pueden
     * ser accedidos desde el HTML utilizando el $scope.
     * @param {Object} $scope Referencia injectada al Scope definida para este
     * controlador, el scope es el objeto que contiene las variables o 
     * funciones que se definen en este controlador y que son utilizadas 
     * desde el HTML.
     * @param {Object} $http Objeto injectado para la manejar consultas HTTP
     * @param {Object} bicicletaContext Constante injectada que contiene la ruta
     * donde se encuentra el API de Bicicletas en el Backend.
     * @param {Object} $state Dependencia injectada en la que se recibe el 
     * estado actual de la navegación definida en el módulo.
     */
    var mod = ng.module("moduloBicis");

    mod.constant("biciContext", "api/bicis");

    mod.controller('biciEditCtrl', ['$scope', '$http', 'biciContext', '$state',
        function ($scope, $http, biciContext, $state) {


            if ($state.params.biciId !== undefined && $state.params.biciId !== null) {

                $http.get(biciContext + '/' + $state.params.biciId).then(function (response) {
                    $scope.biciActual = response.data;

                    $scope.updateBici = function () {

                        var input = {};

                        input.id = $scope.biciActual.id;
                        
                        input.precio = $scope.biciActual.precio;

                        input.modeloId = $scope.biciActual.modeloId;

                        input.color = $scope.biciActual.color;

                        input.categoria = $scope.biciActual.categoria;

                        input.album = $scope.biciActual.album;
                        
                        input.usada = false;
                        
                        input.disponible = true;
                        
                        $http.put(biciContext + '/' + $state.params.biciId, input).then(function (response) {
                            $state.go('bicisList', {biciId: response.data.id}, {reload: true});
                        });
                    };
                });
            }
            ;


        }
    ]);
}
)(window.angular);