(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.constant("carritoContext", "api/carrito/1/items");
    mod.controller('vaciarCarritoCtrl', ['$scope', '$http', 'carritoContext', '$state',
        function ($scope, $http, carritoContext, $state) {


                
                $http.delete(carritoContext).then(function (response) {
                    $state.go('carrito', {reload: true});
                });


        }
    ]);
}
)(window.angular);