(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('vaciarCarritoCtrl', ['$scope', '$http', '$state',
         function ($scope, $http, $state) {
            
            
            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/carrito').then(function (response) {

                ruta = 'api/carrito/' + response.data.id + '/items';
                
                $http.delete(ruta).then(function (response) {
                    $state.go('carrito', {reload: true});
                });

            });
        }
    ]);
}
)(window.angular);