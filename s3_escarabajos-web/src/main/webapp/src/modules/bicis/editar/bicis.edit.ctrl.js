(function (ng) {
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

                        input.album = [];
                        
                        input.usada = false;
                        
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