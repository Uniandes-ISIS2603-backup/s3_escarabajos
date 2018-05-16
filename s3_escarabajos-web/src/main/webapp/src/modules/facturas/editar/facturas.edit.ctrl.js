(function (ng) {
    var mod = ng.module("moduloFacturas");

    mod.constant("facturaContext", "api/facturas");

    mod.controller('facturaEditCtrl', ['$scope', '$http', 'facturaContext', '$state',
        function ($scope, $http, facturaContext, $state) {


            if ($state.params.facturaId !== undefined && $state.params.facturaId !== null) {

                $http.get(facturaContext + '/' + $state.params.facturaId).then(function (response) {

                    $scope.facturaActual = response.data;

                    $scope.updateFactura = function () {

                        var input = {};

                        input.id = $scope.facturaActual.id;

                        input.dinero = $scope.facturaActual.dinero;

                        console.log(input);
                        
                        $http.put(facturaContext + '/' + $state.params.facturaId, input).then(function (response) {
                            $state.go('facturasList', {facturaId: response.data.id}, {reload: true});
                        });
                    };
                });
            }
            ;


        }
    ]);
}
)(window.angular);