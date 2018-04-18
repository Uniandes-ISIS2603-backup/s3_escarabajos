(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('carritoCtrl', ['$scope', '$http',
        function ($scope, $http) {
            $http.get('data/items.carrito.json').then(function (response) {
                $scope.carrito = response.data;
            });
        }
    ]);
}
)(window.angular);