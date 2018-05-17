(function (ng) {
    /**
         * @ngdoc controller
         * @name bicisUsada.create.controller:biciUsadaCreateCtrl
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

    mod.controller('biciUsadaCreateCtrl', ['$scope', '$http', 'biciUsadaContext', '$state',
        function ($scope, $http, biciUsadaContext, $state) {

            $scope.data = {};

            /**
             * @ngdoc function
             * @name createBiciUsada
             * @methodOf bicisUsada.controller:biciUsadaCreateCtrl
             * @description
             * Esta funci�n utiliza el protocolo HTTP para crear la bicicleta usada.
             * @param {Object} biciUsada Objeto con la nueva de la bicicleta usada.
             */
            $scope.createBiciUsada = function () {
                var input = {};

                input.id = 7777;

                input.precio = parseInt($scope.data.precio);

                input.modeloId = parseInt($scope.data.modeloId);

                input.color = $scope.data.color;

                input.categoria = $scope.data.categoria;

                input.album = $scope.data.album;

                input.usada = true;

                input.estado = "En proceso"
                
                input.disponible = true;

                input.facturaOriginal = $scope.data.facturaOriginal;
                //Debia ser: biciUsadaContext + '/' + $state.params.vendedorId + '/bicis' pero aun no esta funcionando vendedor completamente.
                //por ende lo hago con un vendedor especifico.
                $http.post(biciUsadaContext + '/' + $state.params.clienteId + '/bicis', input).then(function (response) {
                    $state.go('bicisUsadaList', {biciUsadaId: response.data.id}, {reload: true});
                });
            };

            $scope.volver = function () {
                $state.go('bicisUsadaList', {reload: true});
            };
        }
    ]);
}
)(window.angular);