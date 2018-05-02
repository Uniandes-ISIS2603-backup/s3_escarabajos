(function (ng) {
    var mod = ng.module("clientesModule");

    mod.constant("clientesContext", "api/clientes");

    mod.controller("clientesDetailCtrl", ['$scope','$http','clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {  
            
            if (($state.params.clienteId !== undefined)&& ($state.params.clienteId !== null)) {
                
                $http.get(clientesContext + '/' + $state.params.clienteId).then(function (response) {
                    $scope.clienteActual = response.data;
                });
                
                $scope.updateCliente = function () {

                        var input = {};

                        input.usuario = $scope.clienteActual.usuario;
                        
                        input.nombre = $scope.clienteActual.nombre;

                        input.cedula = $scope.clienteActual.cedula;

                        input.correo = $scope.clienteActual.correo;
                        
                        input.direccion = $scope.clienteActual.direccion;
                        
                        input.telefono = $scope.clienteActual.telefono;
                        
                        $http.put(clientesContext + '/' + $state.params.clienteId, input).then(function (response) {
                            $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                        });
                    };
            }
        }]);
    
})(window.angular);