(function (ng) {
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