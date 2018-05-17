(function (ng) {
    var mod = ng.module("modelosModule");
    mod.constant("modelosContext", "api/modelos");
    mod.controller("modeloDetailCtrl", ['$scope', 'modelosContext', '$http', '$state',
        function ($scope, modeloContext, $http, $state) {
            if ($state.params.modeloId !== undefined && $state.params.modeloId !== null) {
                $http.get(modeloContext + '/' + $state.params.modeloId).then(function (response) {
                    $scope.modeloActual = response.data;
                })
            }
        }
    ]);
})(window.angular);