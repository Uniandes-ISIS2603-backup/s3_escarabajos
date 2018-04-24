(function (ng) {
    var mod = ng.module("clientesModule");

    mod.constant("clientesContext", "api/clientes");

    mod.controller("clienteDetailCtrl", ['$scope', 'clientesContext', '$http', '$state',
        function ($scope, clientesContext, $http, $state) {
            
            if ($state.params.clienteId !== undefined && $state.params.clienteId !== null) {
                
                $http.get(clientesContext + '/119').then(function (response) {
                    $scope.clienteActual = response.data;
                });
            }
        }]);
    
})(window.angular);