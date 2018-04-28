(function (ng) {
    var mod = ng.module("modeloContext");
    mod.constant("modeloContext", "api/modelos");
    mod.controller("modeloDetailCtrl", ['$scope', 'modeloContext', '$http', '$state',
        function ($scope, modeloContext, $http, $state) {
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                $http.get(modeloContext + '/' + $state.params.modeloId).then(function (response) {
                    $scope.modeloActual = response.data;
                    if ($scope.modeloActual.items !== undefined && $scope.modeloActual.items !== null && $scope.modeloActual.items.length > 0) {
                        $scope.categoria = $scope.modeloActual.items[0].categoria;
                    }
                });
            }
        }]);

})(window.angular);