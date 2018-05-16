(function (ng) {
    var mod = ng.module("CarritoMod");
    mod.controller('addCarritoCtrl', ['$scope', '$http', '$state',
        function ($scope, $http, $state) {


            $http.get('api/clientes/' + sessionStorage.getItem("id") + '/carrito').then(function (response) {


                //realmente es modeloId
                var modeloId = $state.params.itemId;

                var color = $state.params.color;

                var modelo = {};

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