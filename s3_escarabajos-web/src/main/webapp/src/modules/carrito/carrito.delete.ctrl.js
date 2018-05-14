(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('vaciarCarritoCtrl', ['$scope', '$http', 'carritoContext', '$state',
         function ($scope, $http, $state) {
            
              // cambiar el 1 de la ruta por el id del cliente que este logeado
            $http.get('api/clientes/' + 1 + '/carrito').then(function (response) {

                ruta = 'api/carrito/' + response.data.id + '/items';
                
                $http.delete(ruta).then(function (response) {
                    $state.go('carrito', {reload: true});
                });

            });
        }
    ]);
}
)(window.angular);