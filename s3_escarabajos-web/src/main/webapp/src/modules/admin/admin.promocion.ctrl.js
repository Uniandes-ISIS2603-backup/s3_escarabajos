(function (ng) {
    var mod = ng.module("adminModule");

    mod.constant("adminContext", "api/catalogo/promociones");

    mod.controller("adminPromocionesCtrl", ['$scope','$http','adminContext', '$state',
        function ($scope, $http, adminContext, $state) {  
            
            $scope.crearPromocion = function () {

                        var input = {};

                        input.usuario = $scope.clienteActual.usuario;
                        
                        input.nombre = $scope.clienteActual.nombre;
                        
                        $http.put(adminContext + '/' + $state.params.clienteId, input).then(function (response) {
                            $state.go('clientesList', {clienteId: response.data.id}, {reload: true});
                        });
                    };
        }]);
    
})(window.angular);


