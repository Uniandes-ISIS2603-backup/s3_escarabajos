(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.constant("carritoContext", "api/carrito/1/items/");
    mod.controller('deleteCarritoCtrl', ['$scope', '$http', 'carritoContext', '$state',
        function ($scope, $http, carritoContext, $state) {
            
            var itemId = $state.params.itemId;

                
                $http.delete(carritoContext + itemId).then(function (response) {
                    $state.go('carrito', {itemId: response.data.id}, {reload: true});
                });


        }
    ]);
}
)(window.angular);