(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('carritoCtrl', ['$scope', '$http',
        function ($scope, $http) {

            var ruta = 'api/carrito/';
            
            /*$scope.carritoR = {};
            $http.get(context).then(function (response) {
                $scope.carritoR = response.data[0];
                
            }); */
            
             this.crearFactura = function(idCliente) {
                $http.get('api/clientes/' + 1 + '/carrito/factura').then(function () {
                             $state.go('carritoList');
            });
            }  
            
            
            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/carrito').then(function (response) {

                ruta = ruta + response.data.id + '/items';
                
                $http.get(ruta).then(function (response) {
                    $scope.carrito = response.data;

                    var lista2 = [];
                    
                    var lista = $scope.carrito;

                    var total = 0;

                    for (var i = 0; i < lista.length; i++) {

                        total = total + lista[i].precio;

                        var repetido = false;

                        for (var j = 0; j < lista2.length; j++) {

                            if (lista[i].color === lista[j].color && lista[i].modelo === lista[j].modelo) {

                                if ( lista[i].precio === lista[j].precio && lista[i].tipo === lista[j].tipo && lista[i].referencia === lista[j].referencia ){

                                    lista2[j].cantidad = lista2[j].cantidad + 1;

                                    repetido = true;
                                }
                            }
                        }

                        if (repetido === false) {

                            lista[i].cantidad = 1;

                            lista2.push(lista[i]);
                        }
                    }

                    $scope.total = total;

                    $scope.carrito = lista2;

                });

            });

        }
    ]);
}
)(window.angular);