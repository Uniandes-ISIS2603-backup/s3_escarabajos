(function (ng) {
    /**
     * @ngdoc controller
     * @name bicicleta.usada.update.controller:biciUsadaEditCtrl
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
    var mod = ng.module("moduloBicisUsadas");

    mod.constant("biciUsadaContext", "api/clientes");

    mod.controller('biciUsadaEditCtrl', ['$scope', '$http', 'biciUsadaContext', '$state',
        function ($scope, $http, biciUsadaContext, $state) {


            if ($state.params.biciUsadaId !== undefined && $state.params.biciUsadaId !== null) {

                $http.get(biciUsadaContext + '/' + $state.params.clienteId + '/bicis/' + $state.params.biciUsadaId).then(function (response) {

                    $scope.biciUsadaActual = response.data;

                    $scope.updateBiciUsada = function () {

                        var input = {};

                        input.id = $scope.biciUsadaActual.id;

                        input.precio = $scope.biciUsadaActual.precio;

                        input.modeloId = $scope.biciUsadaActual.modeloId;

                        input.color = $scope.biciUsadaActual.color;

                        input.categoria = $scope.biciUsadaActual.categoria;

                        input.album =$scope.biciUsadaActual.album;

                        input.usada = true;

                        input.estado = $scope.biciUsadaActual.estado;
                        
                        input.disponible = true;

                        input.facturaOriginal = $scope.biciUsadaActual.facturaOriginal;
                        
                        $http.put(biciUsadaContext + '/' + $state.params.clienteId + '/bicis/' + $state.params.biciUsadaId, input).then(function (response) {
                            $state.go('bicisUsadaList', {biciUsadaId: response.data.id}, {reload: true});
                        });
                    };
                });
            }
            ;


        }
    ]);
}
)(window.angular);