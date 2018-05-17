(function (ng) {
    var mod = ng.module("modelosModule");
    mod.constant("modelosContext", "api/modelos");
    mod.controller("modeloEditCtrl", ['$scope', 'modelosContext', '$http', '$state',
        function ($scope, modeloContext, $http, $state) {
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                $http.get(modeloContext + '/' + $state.params.modeloId).then(function (response) {
                    $scope.modeloActual = response.data;

                    $scope.updateModelo = function () {

                        var input = {};

                        input.id = $scope.modeloActual.id;

                        input.precio = $scope.modeloActual.precio;

                        input.modeloId = $scope.modeloActual.modeloId;

                        input.color = $scope.modeloActual.color;

                        input.categoria = $scope.modeloActual.categoria;

                        input.album = $scope.modeloActual.album;

                        input.usada = false;

                        input.disponible = true;

                        $http.put(biciContext + '/' + $state.params.modeloId, input).then(function (response) {
                            $state.go('principal:pagina1', {biciId: response.data.id}, {reload: true});
                        });
                    };
                });
            }
        }
    ]);
})(window.angular);