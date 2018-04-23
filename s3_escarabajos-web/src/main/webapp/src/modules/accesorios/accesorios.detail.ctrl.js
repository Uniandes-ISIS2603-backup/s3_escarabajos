(function (ng) {
    var mod = ng.module("AccesoriosMod");

    mod.constant("accesorioContext", "api/accesorios");

    mod.controller("accesorioDetailCtrl", ['$scope', 'accesorioContext', '$http', '$state',
        function ($scope, accesorioContext, $http, $state) {
            
            if ($state.params.accesorioId !== undefined && $state.params.accesorioId !== null) {
                
                $http.get(accesorioContext + '/' + $state.params.accesorioId).then(function (response) {
                    $scope.accesorioActual = response.data;
                });
            }
        }]);
    
})(window.angular);