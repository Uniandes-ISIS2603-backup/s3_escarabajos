(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('comprarCtrl', ['$scope', '$http','$state',
        function ($scope, $http,$state) {
           
            this.crearFactura = function() {
                $http.get('api/clientes/' + sessionStorage.getItem("id") + '/carrito/factura').then(function (response) {
                             $state.go('carritoList');
            });
            } 
}
    ])}
)(window.angular);