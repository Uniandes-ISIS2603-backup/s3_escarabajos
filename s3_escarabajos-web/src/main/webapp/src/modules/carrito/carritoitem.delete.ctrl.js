(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.constant("carritoContext", "api/api/carrito/1/items/");
    mod.controller('deleteItemCarritoCtrl', ['$scope', '$http', 'carritoContext', '$state',
        function ($scope, $http, carritoContext, $state) {
            var itemId = $state.param.itemId;

            console.log(carritoContext);
            console.log(itemId);


                
                $http.delete(carritoContext + itemId).then(function (response) {
                });


        }
    ]);
}
)(window.angular);