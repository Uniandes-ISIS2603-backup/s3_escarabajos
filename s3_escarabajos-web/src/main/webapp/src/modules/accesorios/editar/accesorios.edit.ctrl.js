(function (ng) {
    var mod = ng.module("AccesoriosMod");

    mod.constant("accesorioContext", "api/accesorios");

    mod.controller('accesorioEditCtrl', ['$scope', '$http', 'accesorioContext', '$state',
        function ($scope, $http, accesorioContext, $state) {

        console.log($state.params.accesorioId);


            if ($state.params.accesorioId !== undefined && $state.params.accesorioId !== null) {

                $http.get(accesorioContext + '/' + $state.params.accesorioId).then(function (response) {
                    $scope.accesorioActual = response.data;

                    $scope.updateAccesorio = function () {

                        var input = {};

                        input.id = $scope.accesorioActual.id;
                        
                        input.precio = $scope.accesorioActual.precio;

                        input.modeloId = $scope.accesorioActual.modeloId;

                        input.color = $scope.accesorioActual.color;

                        input.categoria = $scope.accesorioActual.categoria;

                        input.album = $scope.accesorioActual.album;
                        
                        $http.put(accesorioContext + '/' + $state.params.accesorioId, input).then(function (response) {
                            $state.go('accesoriosList', {accesorioId: response.data.id}, {reload: true});
                        });
                    };
                });
            }
            ;


        }
    ]);
}
)(window.angular);