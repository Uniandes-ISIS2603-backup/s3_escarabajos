(function (ng) {
    /**
         * @ngdoc controller
         * @name carrito.controller:carritoCtrl
         * @description
         * Definición del controlador de Angular del módulo carrito. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
    var mod = ng.module("CarritoMod");
    mod.controller('carritoCtrl', ['$scope', '$http',
        function ($scope, $http) {

            var ruta = 'api/carrito/';
            
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