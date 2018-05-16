(function (ng) {
    var mod = ng.module("clientesModule");

    mod.constant("clientesContext", "api/clientes");

    mod.controller("clientesProfileCtrl", ['$scope','$http','clientesContext', '$state',
        function ($scope, $http, clientesContext, $state) {  
            
            if ((sessionStorage.getItem("id") !== undefined)&& (sessionStorage.getItem("id") !== null)) {
                
                $http.get(clientesContext + '/' + sessionStorage.getItem("id")).then(function (response) {
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
                        
                        $http.put(clientesContext + '/' + sessionStorage.getItem("id"), input).then(function (response) {
                            $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                        });
                    };
            }
        }]);
    
})(window.angular);