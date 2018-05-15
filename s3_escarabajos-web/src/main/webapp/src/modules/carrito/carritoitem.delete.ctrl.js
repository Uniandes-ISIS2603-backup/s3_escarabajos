(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('deleteCarritoCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            
            
            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/carrito').then(function (response) {

                ruta = 'api/carrito/' + response.data.id + '/items/';
            
                var itemId = $state.params.itemId;

                
                $http.delete(ruta + itemId).then(function (response) {
                    $state.go('carrito', {itemId: response.data.id}, {reload: true});
                });


            });
        }
    ]);
}
)(window.angular);