(function (ng) {
    /**
         * @ngdoc controller
         * @name carritoitem.add.controller:addCarritoCtrl
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
    mod.controller('addCarritoCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {

            /**
            * @ngdoc function
            * @name getCarrito
            * @methodOf carritoItem.add.controller:addCarritoCtrl
            * @description
            * Esta función utiliza el protocolo HTTP para realizar un POST del recurso 
            * creado a partir de la informaciòn ingresada. El recurso
            * puede ser un archivo o un API Rest. La función se ejecuta
            * automáticamente cuando el controlador es accedido desde el
            * navegador.
            * @param {String} URL Id del cliente donde se encuentra el recurso
            * del accesorio especificado o API donde se puede consultar. Se utiliza el
            * contexto definido anteriormente.
            */
            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/carrito').then(function (response) {


                //realmente es modeloId
                var modeloId = $state.params.itemId;

                var color = $state.params.color;

                var modelo = {};
                /**
                * @ngdoc function
                * @name getCarrito
                * @methodOf carritoItem.add.controller:addCarritoCtrl
                * @description
                * Esta función utiliza el protocolo HTTP para realizar un POST del recurso 
                * creado a partir de la informaciòn ingresada. El recurso
                * puede ser un archivo o un API Rest. La función se ejecuta
                * automáticamente cuando el controlador es accedido desde el
                * navegador.
                * @param {String} URL Id del cliente donde se encuentra el recurso
                * del accesorio especificado o API donde se puede consultar. Se utiliza el
                * contexto definido anteriormente.
                */
                $http.get('api/modelos/' + modeloId).then(function (response0) {

                    modelo = response0.data;

                    var modeloItems = modelo.items;

                    var encontro = false;

                    var itemId = modeloItems[0].id;

                    $http.get('api/carrito/' + response.data.id + '/items').then(function (response2) {

                        var items = response2.data;

                        for (var m = 0; m < modeloItems.length && !encontro; m++) {



                            var estaEnCarrito = false;

                            for (var a = 0; a < items.length && !estaEnCarrito; a++) {

                                if (items[a].id === modeloItems[m].id) {

                                    estaEnCarrito = true;
                                }
                            }

                            if (!estaEnCarrito) {


                                if ((color === null && !modeloItems[m].comprado) || (color === modeloItems[m].color && !modeloItems[m].comprado)) {

                                    itemId = modeloItems[m].id;

                                    encontro = true;
                                }
                            }

                        }

                        if (encontro) {

                            var ruta = 'api/carrito/' + response.data.id + '/items/';

                            $http.post(ruta + itemId).then(function (response) {

                                $state.go('carrito', {itemId: response.data.id}, {reload: true});
                            }, function (response) {

                                if (response.status === 500) {
                                    $state.go('yaEsta', {itemId: response.data.id}, {reload: true});
                                }
                            });
                        } else {

                            $state.go('yaEsta', {itemId: response.data.id}, {reload: true});
                        }
                    });




                });
            });
        }
    ]);
}
)(window.angular);