(function (ng) {
    var mod = ng.module("moduloComprar");
    mod.controller('comprarCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {


            $http.post('api/clientes/' + sessionStorage.getItem("id") + '/carrito/factura').then(function (response) {
                
                $scope.facturaCreada = response.data;
            });

        }
    ])
}
)(window.angular);