(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('addCarritoCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {
            
            // cambiar el 1 de la ruta por el id del cliente que este logeado
            $http.get('api/clientes/' + 1 + '/carrito').then(function (response) {

                ruta = 'api/carrito/' + response.data.id + '/items/';
            
                var itemId = $state.params.itemId;
                
                $http.post(ruta + itemId).then(function (response) {
                    $state.go('carrito', {itemId: response.data.id}, {reload: true});
                });


            });
        }
    ]);
}
)(window.angular);